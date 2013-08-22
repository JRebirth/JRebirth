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
                if (wd.getKey() != JRebirthWaves.PROGRESS_BAR) {
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

        // Create a new ServiceTask to handle this request and follow progression
        final ServiceTask<T> task = new ServiceTask<T>(this, method, parameterValues, sourceWave);

        // Store the task into the pending map
        this.pendingTasks.put(sourceWave.getWUID(), task);

        // Bind ProgressBar
        if (sourceWave.containsNotNull(JRebirthWaves.PROGRESS_BAR)) { // Check double call
            bindProgressBar(task, sourceWave.getData(JRebirthWaves.PROGRESS_BAR).getValue());
        }

        // Call the task into the JRebirth Thread Pool
        JRebirth.runIntoJTP(task);
    }

    private void bindProgressBar(final ServiceTask<?> task, final ProgressBar progressBar) {

        JRebirth.runIntoJAT(new AbstractJrbRunnable("") {

            @Override
            protected void runInto() throws JRebirthThreadException {
                progressBar.progressProperty().bind(task.workDoneProperty().divide(task.totalWorkProperty()));

            }
        });

    }

    /**
     * .
     * 
     * @param taskKey
     */
    public void removePendingTask(final String taskKey) {
        this.pendingTasks.remove(taskKey);
    }

    /**
     * .
     * 
     * @param wave
     * 
     * @return the pending task or null if not found
     */
    protected ServiceTask<?> getPendingTask(final Wave wave) {
        return getPendingTask(wave.getWUID());
    }

    /**
     * .
     * 
     * @param taskKey
     * 
     * @return the pending task or null if not found
     */
    public ServiceTask<?> getPendingTask(final String taskKey) {
        return this.pendingTasks.get(taskKey);
    }

    public void updateProgress(final Wave wave, final long l, final long l2) {
        LOGGER.info("workdone {} total {}", l, l2);
        JRebirth.runIntoJAT(new AbstractJrbRunnable("") {

            @Override
            protected void runInto() throws JRebirthThreadException {
                getPendingTask(wave).updateProgress(l, l2);

            }
        });

    }

    public void updateProgress(final Wave wave, final double v, final double v2) {
        LOGGER.info("workdone {} total {}", v, v2);
        JRebirth.runIntoJAT(new AbstractJrbRunnable("") {

            @Override
            protected void runInto() throws JRebirthThreadException {
                getPendingTask(wave).updateProgress(v, v2);

            }
        });

    }

    public void updateMessage(final Wave wave, final String s) {
        JRebirth.runIntoJAT(new AbstractJrbRunnable("") {

            @Override
            protected void runInto() throws JRebirthThreadException {
                getPendingTask(wave).updateMessage(s);
            }
        });

    }

    public void updateTitle(final Wave wave, final String s) {

        JRebirth.runIntoJAT(new AbstractJrbRunnable("") {

            @Override
            protected void runInto() throws JRebirthThreadException {
                getPendingTask(wave).updateTitle(s);
            }
        });

    }
}
