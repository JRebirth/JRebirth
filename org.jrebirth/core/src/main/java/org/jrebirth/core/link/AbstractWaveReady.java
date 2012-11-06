/**
 * Copyright JRebirth.org © 2011-2012 
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.facade.WaveReady;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.util.ClassUtility;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveBase;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveGroup;
import org.jrebirth.core.wave.WaveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The class <strong>AbstractWaveReady</strong>.
 * 
 * This is the base class for all of each of JRebirth pattern subclasses.<br />
 * It allow to send waves.
 * 
 * All things related to wave management must be execute into the JRebirth Thread
 * 
 * @author Sébastien Bordes
 * 
 * @param <R> the class type of the subclass
 */
public abstract class AbstractWaveReady<R extends FacadeReady<R>> extends AbstractReady<R> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWaveReady.class);

    /**
     * Short cut method used to retrieve the notifier.
     * 
     * @return the notifier retrieved from global facade
     */
    private Notifier getNotifier() {
        return getLocalFacade().getGlobalFacade().getNotifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void listen(final WaveType waveType) {

        final WaveReady waveReady = this;

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJIT(new AbstractJrbRunnable("Listen " + waveType.toString()) {
            @Override
            public void runInto() throws JRebirthThreadException {
                getNotifier().listen(waveReady, waveType);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void unlisten(final WaveType waveType) {

        final WaveReady waveReady = this;

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJIT(new AbstractJrbRunnable("UnListen " + waveType.toString()) {

            @Override
            protected void runInto() throws JRebirthThreadException {
                getNotifier().unlisten(waveReady, waveType);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void sendWave(final Wave wave) {
        sendWaveIntoJit(wave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void sendWave(final WaveType waveType, final WaveData<?>... waveData) {
        sendWaveIntoJit(createWave(WaveGroup.UNDEFINED, waveType, null, waveData));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void callCommand(final Class<? extends Command> commandClass, final WaveData<?>... data) {
        sendWaveIntoJit(createWave(WaveGroup.CALL_COMMAND, null, commandClass, data));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void returnData(final Class<? extends Service> serviceClass, final WaveType waveType, final WaveData<?>... data) {
        sendWaveIntoJit(createWave(WaveGroup.RETURN_DATA, waveType, serviceClass, data));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void attachUi(final Class<? extends Model> modelClass, final WaveData<?>... data) {
        sendWaveIntoJit(createWave(WaveGroup.ATTACH_UI, null, modelClass, data));
    }

    /**
     * Send the given wave using the JRebirth Thread.
     * 
     * @param wave the wave to send
     */
    private void sendWaveIntoJit(final Wave wave) {

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJIT(new AbstractJrbRunnable("Send Wave") {
            @Override
            public void runInto() throws JRebirthThreadException {
                getNotifier().sendWave(wave);
            }
        });
    }

    /**
     * Build a wave object.
     * 
     * @param waveGroup the group of the wave
     * @param waveType the type of the wave
     * @param relatedClass the related class if any
     * @param waveData wave data to use
     * 
     * @return the wave built
     */
    private Wave createWave(final WaveGroup waveGroup, final WaveType waveType, final Class<?> relatedClass, final WaveData<?>... waveData) {
        final Wave wave = new WaveBase();
        wave.setWaveGroup(waveGroup);
        wave.setWaveType(waveType);
        wave.setRelatedClass(relatedClass);
        for (final WaveData<?> wd : waveData) {
            wave.addData(wd);
        }

        // Track wave creation
        getLocalFacade().getGlobalFacade().trackEvent(EventType.CREATE_WAVE, this.getClass(), wave.getClass());

        return wave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Wave wave) throws WaveException {
        try {
            // Build parameter list of the searched method
            final List<Object> parameterValues = new ArrayList<>();
            for (final WaveData<?> wd : wave.getWaveItems()) {
                parameterValues.add(wd.getValue());
            }
            // Add the current wave to process
            parameterValues.add(wave);

            // Search the wave handler method
            final Method method = ClassUtility.getMethodByName(this.getClass(), ClassUtility.underscoreToCamelCase(wave.getWaveType().toString()));
            if (method != null) {
                // Call this method with right parameters
                method.invoke(this, parameterValues.toArray());
            }
        } catch (final NoSuchMethodException e) {
            // If no method was found, call the default method
            processAction(wave);

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error("Error while dispatching a wave", e);
            // Propagate the wave exception
            throw new WaveException(wave, e);
        }
    }

    /**
     * Process the wave. Typically by using a switch on the waveType.
     * 
     * @param wave the wave received
     * 
     * @since org.jrebirth.core 1.0
     */
    protected abstract void processAction(final Wave wave);

}
