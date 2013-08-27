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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.ProgressBar;

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.facade.JRebirthEventType;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.util.ClassUtility;
import org.jrebirth.core.wave.JRebirthWaves;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;

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
public abstract class AbstractService extends AbstractWaveReady<Service> implements Service {

    private static final String RATIO_SEPARATOR = " / ";

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    /** The map that stores pending tasks, useful to access to Worker implementation. */
    private final Map<String, ServiceTask<?>> pendingTasks = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.DESTROY_SERVICE, null, this.getClass());
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
                // Add only wave items defined as parameter
                if (wd.getKey().isParameter()) {
                    parameterValues.add(wd.getValue());
                }
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
            processWave(sourceWave);
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

        // Allow to remove the pending task when the service is finished
        sourceWave.addWaveListener(new ServiceTaskWaveListener());

        // Create a new ServiceTask to handle this request and follow progression
        final ServiceTask<T> task = new ServiceTask<T>(this, method, parameterValues, sourceWave);

        // Store the task into the pending map
        this.pendingTasks.put(sourceWave.getWUID(), task);

        // Attach ServiceTask to the source wave
        sourceWave.addData(WaveData.build(JRebirthWaves.SERVICE_TASK, task));

        // Bind ProgressBar
        if (sourceWave.containsNotNull(JRebirthWaves.PROGRESS_BAR)) { // Check double call
            bindProgressBar(task, sourceWave.getData(JRebirthWaves.PROGRESS_BAR).getValue());
        }

        // Call the task into the JRebirth Thread Pool
        JRebirth.runIntoJTP(task);
    }

    /**
     * Bind a task to a progress bar widget to follow its progression.
     * 
     * @param task the service task that we need to follow the progression
     * @param progressBar graphical progress bar
     */
    private void bindProgressBar(final ServiceTask<?> task, final ProgressBar progressBar) {

        // Perform this binding into the JAT to respect widget and task API
        JRebirth.runIntoJAT(new AbstractJrbRunnable("Bind ProgressBar to " + task.getServiceHandlerName()) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void runInto() throws JRebirthThreadException {
                progressBar.progressProperty().bind(task.workDoneProperty().divide(task.totalWorkProperty()));
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ServiceTask<?>> getPendingTaskList() {
        return this.pendingTasks.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePendingTask(final String taskKey) {
        this.pendingTasks.remove(taskKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceTask<?> getPendingTask(final String taskKey) {
        return this.pendingTasks.get(taskKey);
    }

    /**
     * Update the progress of the service task related to the given wave.
     * 
     * @param wave the wave that trigger the service task call
     * @param workDone the amount of overall work done
     * @param totalWork the amount of total work todo
     */
    public void updateProgress(final Wave wave, final long workDone, final long totalWork) {

        // Increase the task progression
        JRebirth.runIntoJAT(new AbstractJrbRunnable("ServiceTask Workdone (lng) " + workDone + RATIO_SEPARATOR + totalWork) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void runInto() throws JRebirthThreadException {
                wave.get(JRebirthWaves.SERVICE_TASK).updateProgress(workDone, totalWork);
            }
        });

    }

    /**
     * Update the progress of the service task related to the given wave.
     * 
     * @param wave the wave that trigger the service task call
     * @param workDone the amount of overall work done
     * @param totalWork the amount of total work todo
     */
    public void updateProgress(final Wave wave, final double workDone, final double totalWork) {

        // Increase the task progression
        JRebirth.runIntoJAT(new AbstractJrbRunnable("ServiceTask Workdone (dbl) " + workDone + RATIO_SEPARATOR + totalWork) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void runInto() throws JRebirthThreadException {
                wave.get(JRebirthWaves.SERVICE_TASK).updateProgress(workDone, totalWork);
            }
        });

    }

    /**
     * Update the current message of the service task related to the given wave.
     * 
     * @param wave the wave that trigger the service task call
     * @param message the current message of the service task processed
     */
    public void updateMessage(final Wave wave, final String message) {

        // Update the current task message
        JRebirth.runIntoJAT(new AbstractJrbRunnable("Service Task Message => " + message) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void runInto() throws JRebirthThreadException {
                wave.get(JRebirthWaves.SERVICE_TASK).updateMessage(message);
            }
        });

    }

    /**
     * Update the current message of the service task related to the given wave.
     * 
     * @param wave the wave that trigger the service task call
     * @param title the title of the service task processed
     */
    public void updateTitle(final Wave wave, final String title) {

        // Update the task title
        JRebirth.runIntoJAT(new AbstractJrbRunnable("Service Task Title => " + title) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void runInto() throws JRebirthThreadException {
                wave.get(JRebirthWaves.SERVICE_TASK).updateTitle(title);
            }
        });

    }
}
