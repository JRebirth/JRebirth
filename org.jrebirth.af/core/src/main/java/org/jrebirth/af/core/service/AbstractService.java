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
package org.jrebirth.af.core.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.ProgressBar;

import org.jrebirth.af.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.exception.JRebirthThreadException;
import org.jrebirth.af.core.facade.JRebirthEventType;
import org.jrebirth.af.core.link.AbstractComponent;
import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.util.ClassUtility;
import org.jrebirth.af.core.wave.JRebirthWaves;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveData;

/**
 *
 * The class <strong>ServiceBase</strong>.
 *
 * Base implementation of the service.
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractService extends AbstractComponent<Service> implements Service, ServiceMessages {

    /** The string used to separate the workdone and total work of a service task. */
    private static final String RATIO_SEPARATOR = " / ";

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractService.class);

    /** The map that stores pending tasks, useful to access to Worker implementation. */
    private final ObservableMap<String, ServiceTask<?>> pendingTasks = FXCollections.observableMap(new HashMap<String, ServiceTask<?>>());

    /**
     * {@inheritDoc}
     */
    @Override
    public final void ready() throws CoreException {

        // Call the custom method
        initService();
    }

    /**
     * Custom method used to initialize the service.
     *
     * Called into JIT by ready method.
     */
    protected abstract void initService();

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
    public <T extends Object> ServiceTask<T> returnData(final Wave sourceWave) {

        ServiceTask<T> task = null;
        try {
            // Build parameter list of the searched method
            final List<Object> parameterValues = new ArrayList<>();
            for (final WaveData<?> wd : sourceWave.waveDatas()) {
                // Add only wave items defined as parameter
                if (wd.getKey().isParameter()) {
                    parameterValues.add(wd.getValue());
                }
            }
            // Add the current wave to process
            // parameterValues.add(wave);

            if (sourceWave.waveType() == null) {
                LOGGER.error(NO_WAVE_TYPE_DEFINED, this.getClass().getSimpleName());
            }

            // Search the wave handler method
            final Method method = ClassUtility.getMethodByName(this.getClass(), ClassUtility.underscoreToCamelCase(sourceWave.waveType().toString()));
            if (method != null) {

                // final Class<T> returnClass = (Class<T>) method.getReturnType();

                task = runTask(sourceWave, method, parameterValues.toArray());

            }
        } catch (final NoSuchMethodException e) {
            // If no method was found, call the default method
            processWave(sourceWave);
        }
        return task;
    }

    /**
     * Run the wave type method.
     *
     * @param sourceWave the source wave
     * @param parameterValues values to pass to the method
     * @param method method to call
     *
     * @param <T> the type of the returned type
     *
     * @return the service task created
     */
    private <T> ServiceTask<T> runTask(final Wave sourceWave, final Method method, final Object[] parameterValues) {

        // Allow to remove the pending task when the service is finished
        sourceWave.addWaveListener(new ServiceTaskWaveListener());

        // Create a new ServiceTask to handle this request and follow progression
        final ServiceTask<T> task = new ServiceTask<T>(this, method, parameterValues, sourceWave);

        // Store the task into the pending map
        this.pendingTasks.put(sourceWave.getWUID(), task);

        // Attach ServiceTask to the source wave
        sourceWave.addDatas(WaveData.build(JRebirthWaves.SERVICE_TASK, task));

        // Bind ProgressBar
        if (sourceWave.containsNotNull(JRebirthWaves.PROGRESS_BAR)) { // Check double call
            bindProgressBar(task, sourceWave.getData(JRebirthWaves.PROGRESS_BAR).getValue());
        }

        // Bind Title
        if (sourceWave.containsNotNull(JRebirthWaves.TASK_TITLE)) {
            bindTitle(task, sourceWave.getData(JRebirthWaves.TASK_TITLE).getValue());
        }

        // Bind ProgressBar
        if (sourceWave.containsNotNull(JRebirthWaves.TASK_MESSAGE)) {
            bindMessage(task, sourceWave.getData(JRebirthWaves.TASK_MESSAGE).getValue());
        }

        // Call the task into the JRebirth Thread Pool
        JRebirth.runIntoJTP(task);

        return task;
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
                // Avoid the progress bar to display 100% at start up
                task.updateProgress(0, 0);
                // Bind the progress bar
                progressBar.progressProperty().bind(task.workDoneProperty().divide(task.totalWorkProperty()));
            }
        });

    }

    /**
     * Bind a task to a string property that will display the task title.
     *
     * @param task the service task that we need to follow the progression
     * @param titleProperty the title presenter
     */
    private void bindTitle(final ServiceTask<?> task, final StringProperty titleProperty) {

        // Perform this binding into the JAT to respect widget and task API
        JRebirth.runIntoJAT(new AbstractJrbRunnable("Bind Title for " + task.getServiceHandlerName()) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void runInto() throws JRebirthThreadException {
                // Bind the task title
                titleProperty.bind(task.titleProperty());
            }
        });
    }

    /**
     * Bind a task to a string property that will display the task message.
     *
     * @param task the service task that we need to follow the progression
     * @param messageProperty the message presenter
     */
    private void bindMessage(final ServiceTask<?> task, final StringProperty messageProperty) {

        // Perform this binding into the JAT to respect widget and task API
        JRebirth.runIntoJAT(new AbstractJrbRunnable("Bind Message for " + task.getServiceHandlerName()) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void runInto() throws JRebirthThreadException {
                // Bind the task title
                messageProperty.bind(task.messageProperty());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableMap<String, ServiceTask<?>> pendingTasksProperty() {
        return this.pendingTasks;
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
     * This method will use a 1.0 block increment
     *
     * @param wave the wave that trigger the service task call
     * @param workDone the amount of overall work done
     * @param totalWork the amount of total work to do
     */
    public void updateProgress(final Wave wave, final long workDone, final long totalWork) {
        updateProgress(wave, workDone, totalWork, 1.0);
    }

    /**
     * Update the progress of the service task related to the given wave.
     *
     * @param wave the wave that trigger the service task call
     * @param workDone the amount of overall work done
     * @param totalWork the amount of total work to do
     * @param progressIncrement the value increment used to filter useless progress event
     */
    public void updateProgress(final Wave wave, final long workDone, final long totalWork, final double progressIncrement) {

        if (wave.get(JRebirthWaves.SERVICE_TASK).checkProgressRatio(workDone, totalWork, progressIncrement)) {

            // Increase the task progression
            JRebirth.runIntoJAT(new AbstractJrbRunnable("ServiceTask Workdone (lng) " + workDone + RATIO_SEPARATOR + totalWork + "[" + workDone * 100 / totalWork + "%]") {

                /**
                 * {@inheritDoc}
                 */
                @Override
                protected void runInto() throws JRebirthThreadException {

                    wave.get(JRebirthWaves.SERVICE_TASK).updateProgress(workDone, totalWork);
                }
            });
        }
    }

    /**
     * Update the progress of the service task related to the given wave.
     *
     * This method will use a 1.0 block increment
     *
     * @param wave the wave that trigger the service task call
     * @param workDone the amount of overall work done
     * @param totalWork the amount of total work to do
     */
    public void updateProgress(final Wave wave, final double workDone, final double totalWork) {
        updateProgress(wave, workDone, totalWork, 1.0);
    }

    /**
     * Update the progress of the service task related to the given wave.
     *
     * @param wave the wave that trigger the service task call
     * @param workDone the amount of overall work done
     * @param totalWork the amount of total work to do
     * @param progressIncrement the value increment used to filter useless progress event
     */
    public void updateProgress(final Wave wave, final double workDone, final double totalWork, final double progressIncrement) {

        if (wave.get(JRebirthWaves.SERVICE_TASK).checkProgressRatio(workDone, totalWork, progressIncrement)) {

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
