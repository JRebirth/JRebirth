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
import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;

import org.jrebirth.core.exception.CoreException;
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
public final class ServiceTask<T> extends Task<T> {

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
    private final Service service;

    /**
     * The <code>sourceWave</code>.
     */
    private final Wave wave;

    /**
     * Default Constructor only visible by service package.
     * 
     * @param parameterValues the list of function parameter
     * @param method the method to call
     * @param service the service object
     * @param wave the wave to process
     */
    ServiceTask(final Service service, final Method method, final Object[] parameterValues, final Wave wave) {
        super();
        this.service = service;
        this.method = method;
        this.parameterValues = parameterValues.clone();
        this.wave = wave;
    }

    /**
     * Return the full service handler name.
     * 
     * ServiceName + method + ( parameters types )
     * 
     * @return the full service handler name
     */
    public String getServiceHandlerName() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.service.getClass().getSimpleName()).append(".");
        sb.append(this.method.getName()).append("(");
        for (final Class<?> parameterType : this.method.getParameterTypes()) {
            sb.append(parameterType.getSimpleName()).append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws CoreException if return WaveType has bad API
     */
    @SuppressWarnings("unchecked")
    @Override
    protected T call() throws CoreException {
        T res = null;
        try {

            // Build parameter list of the searched method
            final List<Object> params = new ArrayList<>();
            for (final Object o : this.parameterValues) {
                params.add(o);
            }
            // Add the current wave to process
            params.add(this.wave);

            // Call this method with right parameters
            res = (T) this.method.invoke(this.service, params.toArray());

            if (res == null) {
                // No return wave required
                LOGGER.trace(this.service.getClass().getSimpleName() + " Consumes wave (noreturn)" + this.wave.toString());
                this.wave.setStatus(Status.Consumed);

            } else {
                final WaveType responseWaveType = this.service.getReturnWaveType(this.wave.getWaveType());

                if (((WaveTypeBase) responseWaveType).getWaveItemList().isEmpty()) {
                    final String msg = "The Return WaveType must contain at least one WaveItem to wrap the service return";
                    LOGGER.error(msg);
                    throw new CoreException(msg);
                }

                final WaveItem<T> waveItem = (WaveItem<T>) ((WaveTypeBase) responseWaveType).getWaveItemList().get(0);

                final Wave returnWave = WaveBuilder.create()
                        .waveType(responseWaveType)
                        .relatedClass(this.getClass())
                        .data(WaveData.build(waveItem, res))
                        .build();
                returnWave.setRelatedWave(this.wave);
                returnWave.addWaveListener(new ServiceTaskReturnWaveListener());

                // Send the return wave to interested components
                this.service.sendWave(returnWave);

            }

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error("Unable to perform the Service Task " + getServiceHandlerName(), e);
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProgress(final long workDone, final long totalWork) {
        super.updateProgress(workDone, totalWork);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProgress(final double workDone, final double totalWork) {
        super.updateProgress(workDone, totalWork);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMessage(final String message) {
        super.updateMessage(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTitle(final String title) {
        super.updateTitle(title);
    }

    /**
     * The task has complete because the source wave was consumed.
     * 
     * Remove the task from the service pending list
     */
    public void taskDone() {
        // We can now remove the pending task (even if the return wave isn't processed TO CHECK)
        this.service.removePendingTask(this.wave.getWUID());
    }

}
