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
package org.jrebirth.core.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.concurrent.Task;

import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.util.ClassUtility;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.Wave.Status;
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveListener;
import org.jrebirth.core.wave.WaveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The class <strong>ServiceBase</strong>.
 * 
 * Base implementation of the service.
 * 
 * @author Sébastien Bordes
 */
public class ServiceBase extends AbstractWaveReady<Service> implements Service {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBase.class);

    /** The wave type map. */
    private final Map<WaveType, WaveType> waveTypeMap = new HashMap<>();

    /** The wave item map. */
    private final Map<WaveType, WaveItem> waveItemMap = new HashMap<>();

    /**
     * Register a service contract.
     * 
     * @param callType the wave type mapped to this service.
     * @param responseType the wave type of the wave emitted in return
     * @param waveItem the lsit of wave item used as arguments
     */
    public void registerService(final WaveType callType, final WaveType responseType, final WaveItem waveItem) {

        listen(callType);

        this.waveTypeMap.put(callType, responseType);
        this.waveItemMap.put(responseType, waveItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Initialize Service with private method

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Must be overridden to manage action handling by service
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.DESTROY_SERVICE, null, this.getClass());
        super.finalize();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "unchecked", "unused" })
    @Override
    public <T extends Object> void returnData(final Wave sourceWave) {

        try {
            // Build parameter list of the searched method
            final List<Object> parameterValues = new ArrayList<>();
            for (final WaveData<?> wd : sourceWave.getWaveItems()) {
                parameterValues.add(wd.getValue());
            }
            // Add the current wave to process
            // parameterValues.add(wave);

            if (sourceWave.getWaveType() == null) {
                // log error
            }

            // Search the wave handler method
            final Method method = ClassUtility.getMethodByName(this.getClass(), ClassUtility.underscoreToCamelCase(sourceWave.getWaveType().toString()));
            if (method != null) {

                final Class<T> returnClass = (Class<T>) method.getReturnType();

                final ServiceBase localService = this;

                final Task<T> task = new Task<T>() {

                    @Override
                    protected T call() throws Exception {
                        T res = null;
                        try {
                            // Call this method with right parameters
                            res = (T) method.invoke(localService, parameterValues.toArray());

                            if (res != null) {
                                final WaveType responseWaveType = localService.waveTypeMap.get(sourceWave.getWaveType());
                                final WaveItem<T> waveItem = localService.waveItemMap.get(responseWaveType);

                                final Wave returnWave = WaveBuilder.create()
                                        .waveType(responseWaveType)
                                        .data(WaveData.build(waveItem, res))
                                        .build();

                                returnWave.addWaveListener(new WaveListener() {

                                    @Override
                                    public void waveSent(final Wave wave) {
                                    }

                                    @Override
                                    public void waveProcessed(final Wave wave) {
                                    }

                                    @Override
                                    public void waveCreated(final Wave wave) {
                                    }

                                    @Override
                                    public void waveConsumed(final Wave wave) {
                                        // Return wave has been consumed, so the triggered wave can be consumed too

                                        LOGGER.trace(localService.getClass().getSimpleName() + " Consumes wave " + sourceWave.toString());
                                        sourceWave.setStatus(Status.Consumed);
                                    }

                                    @Override
                                    public void waveCancelled(final Wave wave) {
                                        // Nothing to do yet

                                    }

                                    @Override
                                    public void waveDestroyed(final Wave wave) {
                                        // Nothing to do yet

                                    }
                                });

                                // Send the return wave to interested components
                                sendWave(returnWave);

                            } else {
                                // No return wave required
                                LOGGER.trace(localService.getClass().getSimpleName() + " Consumes wave (noreturn)" + sourceWave.toString());

                                sourceWave.setStatus(Status.Consumed);
                            }

                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            // Propagate the wave exception
                            LOGGER.error("Unable to perform the service", e);
                            // throw new WaveException(wave, e);
                        }
                        return res;
                    }

                };
                task.run();

            }
        } catch (final NoSuchMethodException e) {
            // If no method was found, call the default method
            processAction(sourceWave);

        }

    }
}
