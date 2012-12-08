/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.core.link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.command.basic.ShowModelWaveBuilder;
import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.concurrent.JRebirthThread;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.facade.AbstractGlobalReady;
import org.jrebirth.core.facade.EventType;
import org.jrebirth.core.facade.GlobalFacade;
import org.jrebirth.core.facade.WaveReady;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.JRebirthWaves;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.Wave.Status;
import org.jrebirth.core.wave.WaveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The class <strong>NotifierImpl</strong>.
 * 
 * An implementation that allow to send and to rpocess wave message.
 * 
 * @author Sébastien Bordes
 */
public class NotifierBase extends AbstractGlobalReady implements Notifier {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifierBase.class);

    /** The map that store link between wave type and objects interested. */
    private final Map<WaveType, List<WaveReady>> notifierMap = new HashMap<>();

    /**
     * Default Constructor.
     * 
     * @param globalFacade the global facade of the application
     */
    public NotifierBase(final GlobalFacade globalFacade) {
        super(globalFacade);
        getGlobalFacade().trackEvent(EventType.CREATE_NOTIFIER, globalFacade.getClass(), this.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendWave(final Wave wave) throws JRebirthThreadException {

        wave.setStatus(Status.Processing);

        JRebirthThread.checkJRebirthThread();

        try {
            switch (wave.getWaveGroup()) {
                case CALL_COMMAND:
                    callCommand(wave);
                    break;
                case RETURN_DATA:
                    returnData(wave);
                    break;
                case ATTACH_UI:
                    displayUi(wave);
                    break;
                case UNDEFINED:
                default:
                    sendUndefinedWave(wave);
            }
        } catch (final WaveException e) {
            LOGGER.error("Failed to send Wave", e);
        }
    }

    /**
     * Call dynamically a command.
     * 
     * According to its runIntoType the command will be ru into JAT, JIT or a Thread Pool
     * 
     * This method is called from the JIT (JRebirth Internal Thread)<br>
     * 
     * @param wave the wave that contain all information
     */
    @SuppressWarnings("unchecked")
    private void callCommand(final Wave wave) {
        final Command command = getGlobalFacade().getCommandFacade().retrieve((Class<? extends Command>) wave.getRelatedClass(), wave.getWUID());
        command.run(wave);
    }

    /**
     * Call a service method by using a task worker.
     * 
     * This method is called from the JIT (JRebirth Internal Thread)<br>
     * 
     * @param wave the wave that contain all information
     */
    @SuppressWarnings("unchecked")
    private void returnData(final Wave wave) {
        final Service service = getGlobalFacade().getServiceFacade().retrieve((Class<? extends Service>) wave.getRelatedClass());
        // The inner task will be run into the JRebirth Thread Pool
        service.returnData(wave);
    }

    /**
     * Display dynamically an Ui model.<br>
     * 
     * This method is called from the JIT (JRebirth Internal Thread)<br>
     * 
     * Creates the model and its root node.<br>
     * Then attach it according to the placeholder defined into the wave<br>
     * <ul>
     * <li>JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER : to replace a property node by the model's root node</li>
     * <li>JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER : to add the model's root node into a children list</li>
     * </ul>
     * 
     * @param wave the wave that contain all information
     */
    @SuppressWarnings("unchecked")
    private void displayUi(final Wave wave) {

        // This key method could be managed in another way (fully sync with JAT), to see if it could be useful

        // Build the wave used to call the required command
        final ShowModelWaveBuilder smwb = ShowModelWaveBuilder.create().modelClass((Class<? extends Model>) wave.getRelatedClass());

        if (wave.contains(JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER)) {
            // Add the Ui view into the place holder provided
            smwb.uniquePlaceHolder(wave.get(JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER));

        } else if (wave.contains(JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER)) {
            // Add the Ui view into the children list of its parent container
            smwb.childrenPlaceHolder(wave.get(JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER));
        }

        // Call the command that manage the display UI in 2 steps
        // 1 - Create the model into the POol Thread
        // 2 - Attache it to the graphical tree model according to their place holder type
        callCommand(smwb.build());
    }

    /**
     * Dispatch a standard wave which could be handled by a custom method of the component.
     * 
     * This method is called from the JIT (JRebirth Internal Thread)<br>
     * 
     * @param wave the wave that contains all information
     * 
     * @throws WaveException if wave dispatching fails
     */
    private void sendUndefinedWave(final Wave wave) throws WaveException {

        // Retrieve all interested object from the map
        if (this.notifierMap.containsKey(wave.getWaveType())) {
            final List<WaveReady> list = this.notifierMap.get(wave.getWaveType());
            // For each object interested in that wave type, process the action
            for (final WaveReady linked : list) {

                // If the notified class is part of the UI
                // We must perform this action into the JavaFX Application Thread
                if (linked instanceof Model) {
                    JRebirth.runIntoJAT(LoopBuilder.newRunnable(linked, wave));
                } else {
                    // Otherwise can perform it right now into the current thread (JRebirthThread)
                    linked.handle(wave);
                }
            }
        } else {
            LOGGER.warn("No Listener attached for wave type : " + wave.getWaveType().toString());
        }
        LOGGER.warn("NB consumes : " + wave.toString());
        wave.setStatus(Status.Consumed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void listen(final WaveReady linkedObject, final WaveType... waveType) throws JRebirthThreadException {

        JRebirthThread.checkJRebirthThread();

        // For each wave type manage listeners
        for (final WaveType wt : waveType) {
            List<WaveReady> list;
            if (this.notifierMap.containsKey(wt)) {
                list = this.notifierMap.get(wt);
            } else {
                list = LoopBuilder.newList(linkedObject);
                this.notifierMap.put(wt, list);
            }
            if (list.isEmpty() || !list.contains(linkedObject)) {
                list.add(linkedObject);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlisten(final WaveReady linkedObject, final WaveType... waveType) throws JRebirthThreadException {

        JRebirthThread.checkJRebirthThread();

        for (final WaveType nt : waveType) {
            List<WaveReady> list;
            if (this.notifierMap.containsKey(nt)) {
                list = this.notifierMap.get(nt);
                list.remove(linkedObject);
                if (list.isEmpty()) {
                    this.notifierMap.remove(nt);
                }
            }
        }
    }

    /**
     * The class <strong>LoopBuilder</strong>.
     * 
     * Used to instantiate object into loop.
     * 
     * @author Sébastien Bordes
     */
    private static final class LoopBuilder {

        /**
         * Private Constructor.
         */
        private LoopBuilder() {
            // Nothing to do
        }

        /**
         * Build a new empty mutable array list.
         * 
         * @param item the object to define the generic type
         * 
         * @param <T> the generic type to use
         * 
         * @return a new instance of ArrayList
         */
        public static <T> List<T> newList(final T item) {
            return new ArrayList<T>();
        }

        /**
         * Build a new Runnable.
         * 
         * @param linked the linked object
         * @param wave the wave to handle
         * 
         * @return a new runnable
         */
        public static WaveRunnable newRunnable(final WaveReady linked, final Wave wave) {
            return new WaveRunnable(linked, wave);
        }
    }

    /**
     * The class <strong>WaveRunnable</strong>.
     * 
     * @author Sébastien Bordes
     */
    private static class WaveRunnable extends AbstractJrbRunnable {

        /** The linked component which will handle the wave. */
        private final WaveReady linked;

        /** The wave that handle. */
        private final Wave wave;

        /**
         * Default Constructor.
         * 
         * @param linked the linked object
         * @param wave the wave to handle
         */
        public WaveRunnable(final WaveReady linked, final Wave wave) {
            super(linked.getClass().getSimpleName() + " handle wave " + wave.toString());
            this.linked = linked;
            this.wave = wave;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void runInto() throws JRebirthThreadException {
            try {
                this.linked.handle(this.wave);
            } catch (final WaveException e) {
                LOGGER.error("Error while handling a wave", e);
            }
        }
    }
}
