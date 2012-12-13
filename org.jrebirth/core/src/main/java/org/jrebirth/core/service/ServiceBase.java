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
package org.jrebirth.core.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.concurrent.Task;

import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.EventType;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.util.ClassUtility;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;
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
    static final Logger LOGGER = LoggerFactory.getLogger(ServiceBase.class);

    /** The wave type map. */
    private final Map<WaveType, WaveType> returnWaveTypeMap = new HashMap<>();

    /**
     * Register a service contract.
     * 
     * @param callType the wave type mapped to this service.
     * @param responseType the wave type of the wave emitted in return
     * @param waveItem the list of wave item used as arguments
     */
    public void registerService(final WaveType callType, final WaveType responseType/* , final WaveItem<?> waveItem */) {

        listen(callType);

        this.returnWaveTypeMap.put(callType, responseType);
    }

    /**
     * Return the return wave type.
     * 
     * @param waveType the source wave type
     * 
     * @return Returns the waveType for return wave.
     */
    public WaveType getReturnWaveType(final WaveType waveType) {
        return this.returnWaveTypeMap.get(waveType);
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
                LOGGER.error("Wave processed without any wave type for service  " + this.getClass().getSimpleName());
            }

            // Search the wave handler method
            final Method method = ClassUtility.getMethodByName(this.getClass(), ClassUtility.underscoreToCamelCase(sourceWave.getWaveType().toString()));
            if (method != null) {

                // final Class<T> returnClass = (Class<T>) method.getReturnType();

                runTask(sourceWave, method, parameterValues.toArray());

            }
        } catch (final NoSuchMethodException e) {
            // If no method was found, call the default method
            processAction(sourceWave);

        }

    }

    /**
     * Run the wave type method.
     * 
     * @param sourceWave the source wave
     * @param parameterValues values to pass to the method
     * @param method method to call
     * 
     * @param <T> the type of the returned type
     */
    private <T> void runTask(final Wave sourceWave, final Method method, final Object[] parameterValues) {

        final ServiceBase localService = this;

        final Task<T> task = new ServiceTask<T>(localService, method, parameterValues, sourceWave);

        // Call the task into the JRebirth Thread Pool
        JRebirth.runIntoJTP(task);
    }
}
