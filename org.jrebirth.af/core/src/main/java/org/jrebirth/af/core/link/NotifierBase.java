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
package org.jrebirth.af.core.link;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.exception.JRebirthThreadException;
import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.api.link.Notifier;
import org.jrebirth.af.api.link.UnprocessedWaveHandler;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.Wave.Status;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.command.basic.showmodel.DisplayModelWaveBean;
import org.jrebirth.af.core.command.basic.showmodel.ShowModelCommand;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.exception.WaveException;
import org.jrebirth.af.core.facade.AbstractGlobalReady;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.jrebirth.af.core.service.ServiceTaskBase;
import org.jrebirth.af.core.service.basic.TaskTrackerService;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.core.wave.JRebirthWaves;

/**
 *
 * The class <strong>NotifierImpl</strong>.
 *
 * An implementation that allow to send and to rpocess wave message.
 *
 * @author Sébastien Bordes
 */
public class NotifierBase extends AbstractGlobalReady implements Notifier, LinkMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(NotifierBase.class);

    /** The map that store link between wave type and objects interested. */
    private final Map<WaveType, WaveSubscription> notifierMap = new HashMap<>();

    /** The handler used to handle unprocessed waves. */
    private final UnprocessedWaveHandler unprocessedWaveHandler;

    /**
     * Default Constructor.
     *
     * @param globalFacade the global facade of the application
     */
    public NotifierBase(final GlobalFacade globalFacade) {
        super(globalFacade);

        UnprocessedWaveHandler waveHandler;
        try {
            waveHandler = (UnprocessedWaveHandler) JRebirthParameters.UNPROCESSED_WAVE_HANDLER.get().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {

            LOGGER.error(USE_DEFAULT_WAVE_HANDLER, e);
            waveHandler = new DefaultUnprocessedWaveHandler();
        }
        // Attach the right EnhancedComponent Factory
        this.unprocessedWaveHandler = waveHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendWave(final Wave wave) throws JRebirthThreadException {

        wave.status(Status.Processing);

        JRebirth.checkJIT();

        // Perform different action according to Wave Group used
        try {
            switch (wave.waveGroup()) {
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
                    processUndefinedWave(wave);
            }
        } catch (final WaveException e) {
            LOGGER.error(WAVE_SENDING_ERROR, e);
        }
    }

    /**
     * Call dynamically a command.
     *
     * According to its runIntoType the command will be run into JAT, JIT or a Thread Pool
     *
     * Each time a new fresh command will be retrieved.
     *
     * This method is called from the JIT (JRebirth Internal Thread)<br>
     *
     * @param wave the wave that contains all informations
     */
    @SuppressWarnings("unchecked")
    private void callCommand(final Wave wave) {

        // Use the Wave UID to guarantee that a new fresh command is built and used !
        final Command command = wave.contains(JRebirthWaves.REUSE_COMMAND) && wave.get(JRebirthWaves.REUSE_COMMAND)
                ? getGlobalFacade().getCommandFacade().retrieve((Class<Command>) wave.componentClass())
                : getGlobalFacade().getCommandFacade().retrieve((Class<Command>) wave.componentClass(), wave.getWUID());

        if (command == null) {
            LOGGER.error(COMMAND_NOT_FOUND_ERROR, wave.toString());
            if (JRebirthParameters.DEVELOPER_MODE.get()) {
                this.unprocessedWaveHandler.manageUnprocessedWave(COMMAND_NOT_FOUND_MESSAGE.getText(), wave);
            }
        } else {
            // Run the command into the predefined thread
            command.run(wave);
        }
    }

    /**
     * Call a service method by using a task worker.
     *
     * The same service will be retrieved each time this method is called.
     *
     * This method is called from the JIT (JRebirth Internal Thread)<br />
     *
     * @param wave the wave that contains all informations
     */
    @SuppressWarnings("unchecked")
    private void returnData(final Wave wave) {

        // Use only the Service class to retrieve the same instance each time
        final Service service = getGlobalFacade().getServiceFacade().retrieve((Class<Service>) wave.componentClass());

        if (service == null) {
            LOGGER.error(SERVICE_NOT_FOUND_ERROR, wave.toString());
            if (JRebirthParameters.DEVELOPER_MODE.get()) {
                this.unprocessedWaveHandler.manageUnprocessedWave(SERVICE_NOT_FOUND_MESSAGE.getText(), wave);
            }
        } else {
            // The inner task will be run into the JRebirth Thread Pool
            final ServiceTaskBase<?> task = (ServiceTaskBase<?>) service.returnData(wave);
            if (task != null && JRebirthParameters.FOLLOW_UP_SERVICE_TASKS.get()) {
                getGlobalFacade().getServiceFacade().retrieve(TaskTrackerService.class).trackTask(task);
            }

        }
    }

    /**
     * Display dynamically an Ui model.<br>
     *
     * This method is called from the JIT (JRebirth Internal Thread)<br>
     *
     * Creates the model and its root node.<br>
     * Then attach it according to the placeholder defined into the wave:<br>
     * <ul>
     * <li>JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER : to replace a property node by the model's root node</li>
     * <li>JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER : to add the model's root node into a children list</li>
     * </ul>
     *
     * @param wave the wave that contains all informations
     */
    @SuppressWarnings("unchecked")
    private void displayUi(final Wave wave) {

        if (wave.componentClass() != null) {
            LOGGER.error(MODEL_NOT_FOUND_ERROR, wave.toString());
            if (JRebirthParameters.DEVELOPER_MODE.get()) {
                this.unprocessedWaveHandler.manageUnprocessedWave(MODEL_NOT_FOUND_MESSAGE.getText(), wave);
            }
        }
        // This key method could be managed in another way (fully sync with JAT), to see if it could be useful

        // Build the wave used to call the required command
        final DisplayModelWaveBean waveBean = DisplayModelWaveBean.create();

        if (wave.contains(JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER)) {
            // Add the Ui view into the place holder provided
            waveBean.uniquePlaceHolder(wave.get(JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER));

        } else if (wave.contains(JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER)) {
            // Add the Ui view into the children list of its parent container
            waveBean.childrenPlaceHolder(wave.get(JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER));
        }

        // Call the command that manage the display UI in 2 steps
        // 1 - Create the model into the Thread Pool
        // 2 - Attach it to the graphical tree model according to their placeholder type
        callCommand(Builders.callCommand(ShowModelCommand.class).waveBean(waveBean));
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
    private void processUndefinedWave(final Wave wave) throws WaveException {
        LOGGER.info(NOTIFIER_CONSUMES, wave.toString());
        wave.status(Status.Consumed);

        // Retrieve all interested object from the map
        if (this.notifierMap.containsKey(wave.waveType())) {
            final WaveSubscription ws = this.notifierMap.get(wave.waveType());

            // Store all Wave handler into the wave in order to know if there is any handler left before
            wave.setWaveHandlers(new ArrayList<WaveHandler>(ws.getWaveHandlers()));

            // For each object interested in that wave type, process the action
            for (final WaveHandler waveHandler : ws.getWaveHandlers()) {

                if (waveHandler.check(wave)) {

                    waveHandler.handle(wave);

                    // // If the notified class is part of the UI
                    // // We must perform this action into the JavaFX Application Thread
                    // if (waveHandler.getWaveReady() instanceof Model) {
                    // JRebirth.runIntoJAT(LoopBuilder.newRunnable(waveHandler.getWaveReady(), wave));
                    // } else {
                    // // Otherwise can perform it right now into the current thread (JRebirthThread - JIT)
                    // waveHandler.getWaveReady().handle(wave);
                    // }
                }
            }
        } else {
            LOGGER.warn(NO_WAVE_LISTENER, wave.waveType().toString());
            if (JRebirthParameters.DEVELOPER_MODE.get()) {
                this.unprocessedWaveHandler.manageUnprocessedWave(NO_WAVE_LISTENER.getText(wave.waveType().toString()), wave);
            }
        }

        LOGGER.info(NOTIFIER_HANDLES, wave.toString());
        wave.status(Status.Handled);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void listen(final Component<?> linkedObject, final WaveChecker waveChecker, final Method method, final WaveType... waveTypes) throws JRebirthThreadException {

        JRebirth.checkJIT();

        // For each given wave type, add linked Object to call
        for (final WaveType waveType : waveTypes) {

            // IF this wave type isn't registered into the map, we add it with an empty list of LinkedObject
            if (!this.notifierMap.containsKey(waveType)) {
                this.notifierMap.put(waveType, LoopBuilder.newSubscription(waveType));
            }
            // Retrieve the list associated to this Wave Type
            final WaveSubscription ws = this.notifierMap.get(waveType);

            // Remove the linked object to unregister it
            boolean contains = false;
            for (int i = 0; !contains && i < ws.getWaveHandlers().size(); i++) {
                if (ws.getWaveHandlers().get(i).getWaveReady().equals(linkedObject)) {
                    contains = true;
                }
            }
            if (ws.getWaveHandlers().isEmpty() || !contains) {
                // Add the linked object if the list is empty or if the object isn't yet contained
                ws.getWaveHandlers().add(LoopBuilder.newHandler(linkedObject, waveChecker, method));
            }

            // // Manage return wave type registration
            // WaveType returnWaveType = waveType.returnWaveType();
            // if (returnWaveType != null) {
            // listen(linkedObject, waveChecker, method, returnWaveType);
            // }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlisten(final Component<?> linkedObject, final WaveType... waveTypes) throws JRebirthThreadException {

        JRebirth.checkJIT();

        // For each given wave type, remove linked Object to avoid calling them anymore
        for (final WaveType waveType : waveTypes) {
            WaveSubscription ws;
            if (this.notifierMap.containsKey(waveType)) {

                // Retrieve the list of linked object associated to this Wave Type
                ws = this.notifierMap.get(waveType);

                // When the linkedObject is removed stop the iteration
                boolean removed = false;
                // Remove the linked object to unregister it
                for (int i = ws.getWaveHandlers().size() - 1; !removed && i >= 0; i--) {
                    if (ws.getWaveHandlers().get(i).getWaveReady().equals(linkedObject)) {
                        ws.getWaveHandlers().remove(i);
                        removed = true;
                    }
                }

                // Remove the Wave Type from the map if there isn't any linked object left
                if (ws.getWaveHandlers().isEmpty()) {
                    this.notifierMap.remove(waveType);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlistenAll(final Component<?> linkedObject) throws JRebirthThreadException {

        // This method is called into JIT (all method call are serialized)
        // to avoid any thread concurrency trouble
        JRebirth.checkJIT();

        final List<WaveSubscription> toRemove = new ArrayList<WaveSubscription>();
        // Iterate over all WaveSubscription
        for (final WaveSubscription ws : this.notifierMap.values()) {

            for (int i = ws.getWaveHandlers().size() - 1; i >= 0; i--) {
                final WaveHandler wh = ws.getWaveHandlers().get(i);
                // If the WaveHandler concern the linked object
                if (wh.getWaveReady() == linkedObject) {
                    // Remove it from registration list
                    ws.getWaveHandlers().remove(i);
                }
            }

            // If this WaveSubscription doesn't contain any WaveHandler remove the entry.
            if (ws.getWaveHandlers().isEmpty()) {
                toRemove.add(ws);
            }
        }

        for (final WaveSubscription ws : toRemove) {
            this.notifierMap.remove(ws.getWaveType());
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
         * Build a new {@link WaveSubscription}.
         *
         * @param waveType the Wave Type listened
         *
         * @return a new {@link WaveSubscription} instance
         */
        public static WaveSubscription newSubscription(final WaveType waveType) {
            return new WaveSubscription(waveType, new ArrayList<WaveHandler>());
        }

        /**
         * Build a new empty component wrapper.
         *
         * @param linkedObject the object to wrap into an handler
         * @param waveChecker the wave checker
         * @param method the method used to handle the wave (could be null)
         *
         * @return a new instance of WaveHandler
         */
        public static WaveHandler newHandler(final Component<?> linkedObject, final WaveChecker waveChecker, final Method method) {
            return new WaveHandler(linkedObject, waveChecker, method);
        }

    }

}
