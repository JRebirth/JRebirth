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
import java.util.Calendar;
import java.util.List;

import javafx.concurrent.Task;

import org.jrebirth.core.concurrent.JRebirthRunnable;
import org.jrebirth.core.concurrent.Priority;
import org.jrebirth.core.concurrent.RunnablePriority;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.log.JRLogger;
import org.jrebirth.core.log.JRLoggerFactory;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.Wave.Status;
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveType;
import org.jrebirth.core.wave.WaveTypeBase;

/**
 * The class <strong>ServiceTask</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <T> the current Service Task type
 */
public final class ServiceTask<T> extends Task<T> implements JRebirthRunnable, ServiceMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(ServiceTask.class);

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
     * The workdone copied property stored locally to allow access outside JAT.<br/>
     * It implies to synchronize each access/modification
     */
    private double localWorkDone;

    /** The runnable priority. */
    private final RunnablePriority priority;

    /** The creation timestamp in milliseconds. */
    private final long creationTime;

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

        this.creationTime = Calendar.getInstance().getTimeInMillis();

        this.service = service;
        this.method = method;
        this.parameterValues = parameterValues.clone();
        this.wave = wave;

        final Priority priorityA = method.getAnnotation(Priority.class);
        this.priority = priorityA == null ? RunnablePriority.Low : priorityA.value();

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

            if (Void.TYPE.equals(this.method.getReturnType())) {
                // No return wave required because the service method will return nothing (VOID)
                LOGGER.log(NO_RETURN_WAVE_CONSUMED, this.service.getClass().getSimpleName(), this.wave.toString());
                this.wave.setStatus(Status.Consumed);

                // Otherwise prepare the return wave
            } else {
                final WaveType responseWaveType = this.service.getReturnWaveType(this.wave.getWaveType());

                if (((WaveTypeBase) responseWaveType).getWaveItemList().isEmpty()) {
                    LOGGER.log(NO_RETURNED_WAVE_ITEM);
                    throw new CoreException(NO_RETURNED_WAVE_ITEM);
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
            LOGGER.log(SERVICE_TASK_ERROR, e, getServiceHandlerName());
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProgress(final long workDone, final long totalWork) {
        synchronized (this) {
            this.localWorkDone = workDone;
        }
        super.updateProgress(workDone, totalWork);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProgress(final double workDone, final double totalWork) {
        synchronized (this) {
            this.localWorkDone = workDone;
        }
        super.updateProgress(workDone, totalWork);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMessage(final String message) { // NOSONAR Override method visibility
        super.updateMessage(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTitle(final String title) { // NOSONAR Override method visibility
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

    /**
     * Check if the task has enough progressed according to the given threshold.
     * 
     * This method can be called outside the JAT, it's useful to filter useless call to JAT
     * 
     * @param newWorkDone the amount of work done
     * @param totalWork the total amount of work
     * @param amountThreshold the minimum threshold amount to return true; range is [0.0 - 100.0] (typically 1.0 for 1%)
     * 
     * @return true if the threshold is reached
     */
    public boolean checkProgressRatio(final double newWorkDone, final double totalWork, final double amountThreshold) {

        double currentRatio;
        synchronized (this) {
            // Compute the actual progression
            currentRatio = this.localWorkDone >= 0 ? 100 * this.localWorkDone / totalWork : 0.0;
        }

        // Compute the future progression
        final double newRatio = 100 * newWorkDone / totalWork;

        // return true if the task has progressed of at least block increment value
        return newRatio - currentRatio > amountThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RunnablePriority getPriority() {
        return this.priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getCreationTime() {
        return this.creationTime;
    }

}
