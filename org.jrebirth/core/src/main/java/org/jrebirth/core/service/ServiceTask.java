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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.concurrent.Task;

import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.Wave.Status;
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveType;
import org.jrebirth.core.wave.WaveTypeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ServiceTask</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <T> the current Service Task type
 */
final class ServiceTask<T> extends Task<T> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTask.class);

    /**
     * The <code>parameterValues</code>.
     */
    private final Object[] parameterValues;
    /**
     * The <code>method</code>.
     */
    private final Method method;
    /**
     * The <code>localService</code>.
     */
    private final ServiceBase service;
    /**
     * The <code>sourceWave</code>.
     */
    private final Wave wave;

    /**
     * Default Constructor only visible by service package.
     * 
     * @param parameterValues the lsit of function parameter
     * @param method the method to call
     * @param service the service object
     * @param wave the wave to process
     */
    ServiceTask(final ServiceBase service, final Method method, final Object[] parameterValues, final Wave wave) {
        super();
        this.service = service;
        this.method = method;
        this.parameterValues = parameterValues.clone();
        this.wave = wave;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected T call() throws WaveException {
        T res = null;
        try {
            // Call this method with right parameters
            res = (T) this.method.invoke(this.service, this.parameterValues);

            if (res == null) {
                // No return wave required
                LOGGER.trace(this.service.getClass().getSimpleName() + " Consumes wave (noreturn)" + this.wave.toString());
                this.wave.setStatus(Status.Consumed);

            } else {
                final WaveType responseWaveType = this.service.getReturnWaveType(this.wave.getWaveType());
                final WaveItem<T> waveItem = (WaveItem<T>) ((WaveTypeBase) responseWaveType).getWaveItemList().get(0);// this.service.getWaveItem(responseWaveType);

                final Wave returnWave = WaveBuilder.create()
                        .waveType(responseWaveType)
                        .relatedClass(this.getClass())
                        .data(WaveData.build(waveItem, res))
                        .build();
                returnWave.setRelatedWave(this.wave);
                returnWave.addWaveListener(new ServiceWaveListener());

                // Send the return wave to interested components
                this.service.sendWave(returnWave);

            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error("Unable to perform the service task", e);
        }
        return res;
    }
}
