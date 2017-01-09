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
import java.util.List;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.concurrent.JRebirthRunnable;
import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.core.component.basic.AbstractComponent;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.concurrent.JrbReferenceRunnable;
import org.jrebirth.af.core.exception.WaveException;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.util.CheckerUtility;
import org.jrebirth.af.core.util.ClassUtility;

/**
 * The class <strong>WaveHandler</strong> is used to define how to manage a Wave for wave type subscribers.
 *
 * @author Sébastien Bordes
 */
public class WaveHandler implements LinkMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(WaveHandler.class);

    /** The wave ready component that will handle the wave. */
    private final Component<?> waveReady;

    /** The default method to call, will been used when annotation is put on a method, otherwise could be null. */
    private final Method defaultMethod;

    /** The wave checker taht check if the wave could be handled, could be null. */
    private final WaveChecker waveChecker;

    /**
     * Instantiates a new wave handler.
     *
     * @param waveReady the wave ready component that will handle the wave
     * @param waveChecker the wave checker, could be null
     * @param defaultMethod the default method to call, could be null
     */
    public WaveHandler(final Component<?> waveReady, final WaveChecker waveChecker, final Method defaultMethod) {
        super();
        this.waveReady = waveReady;
        this.waveChecker = waveChecker;
        this.defaultMethod = defaultMethod;
    }

    /**
     * Check the wave if the wave checker is not null anf if it returns true.
     *
     * @param wave the wave to check
     *
     * @return true, if check succeeded or if wave checker is null
     */
    public boolean check(final Wave wave) {
        // Skip the check if there isn't any checker
        return this.waveChecker == null || this.waveChecker.call(wave);
    }

    /**
     * Gets the wave ready.
     *
     * @return Returns the waveReady.
     */
    public Component<?> getWaveReady() {
        return this.waveReady;
    }

    /**
     * Handle the wave into JAT for model component or into current thread for others.
     *
     * @param wave the wave to manage
     *
     * @throws WaveException if an error occurred while processing the wave
     */
    public void handle(final Wave wave) throws WaveException {

        Method customMethod = retrieveCustomMethod(wave);

        if (customMethod == null) {

            // If no custom method was provided, call the default method named 'processWave(wave)'
            try {
                customMethod = getWaveReady().getClass().getDeclaredMethod(AbstractComponent.PROCESS_WAVE_METHOD_NAME, Wave.class);
            } catch (IllegalArgumentException | NoSuchMethodException e) {
                LOGGER.error(WAVE_DISPATCH_ERROR, e);
                // Propagate the wave exception
                throw new WaveException(wave, e);
            }
        }

        // Grab the run type annotation (if exists)
        final RunInto runInto = customMethod.getAnnotation(RunInto.class);

        // Retrieve the annotation runnable priority (if any)
        final PriorityLevel priority = runInto == null ? PriorityLevel.Normal : runInto.priority();

        // Retrieve the annotation run type (if any)
        final RunType runType = runInto == null ? null : runInto.value();

        final JRebirthRunnable waveHandlerRunnable = buildWaveRunnable(wave, customMethod, priority);

        // If the notified class is part of the UI
        // We must perform this action into the JavaFX Application Thread
        // only if the run type hasn't been overridden
        if (runType != null && runType == RunType.JAT || runType == null && getWaveReady() instanceof Model) {

            JRebirth.runIntoJAT(waveHandlerRunnable);

        } else if (runType != null && runType == RunType.JTP) {
            // Launch the wave handling into JRebirth Thread Pool
            JRebirth.runIntoJTP(waveHandlerRunnable);
        } else {
            // Otherwise we can perform it right now into the current thread (JRebirthThread - JIT)
            waveHandlerRunnable.run();
        }

    }

    /**
     * Build the wave runnable handler that will handle the wave into the right thread.
     *
     * @param wave the wave to handle
     * @param customMethod the custom method to call (could be null)
     * @param priority the runnable priority to use
     *
     * @return the right JRebirth Runnable
     */
    private JRebirthRunnable buildWaveRunnable(final Wave wave, final Method customMethod, final PriorityLevel priority) {

        final WaveHandler waveHandler = this;

        return new JrbReferenceRunnable(
                                        getWaveReady().getClass().getSimpleName() + " handle wave " + wave.toString(),
                                        priority,
                                        () -> {
                                            try {
                                                performHandle(wave, customMethod);
                                            } catch (final WaveException e) {
                                                LOGGER.error(WAVE_HANDLING_ERROR, e);
                                            } finally {
                                                wave.removeWaveHandler(waveHandler);
                                            }
                                        });
    }

    /**
     * Retrieve the custom wave handler method.
     *
     * @param wave the wave to be handled
     *
     * @return the custom handler method or null if none exists
     */
    private Method retrieveCustomMethod(final Wave wave) {
        Method customMethod = null;
        // Search the wave handler method to call
        customMethod = this.defaultMethod == null
                // Method computed according to wave prefix and wave type action name
                ? ClassUtility.retrieveMethodList(getWaveReady().getClass(), wave.waveType().toString())
                              .stream()
                              .filter(m -> {
                                  return CheckerUtility.checkMethodSignature(m, wave.waveType().items());
                              }).findFirst().orElse(null)
                // Method defined with annotation
                : this.defaultMethod;

        if (customMethod == null) {
            LOGGER.info(CUSTOM_METHOD_NOT_FOUND);
        }
        return customMethod;
    }

    /**
     * Perform the handle independently of thread used.
     *
     * @param wave the wave to manage
     * @param method the handler method to call, could be null
     *
     * @throws WaveException if an error occurred while processing the wave
     */
    private void performHandle(final Wave wave, final Method method) throws WaveException {

        // Build parameter list of the searched method
        final List<Object> parameterValues = new ArrayList<>();

        // Don't add WaveType parameters if we us the default processWave method
        if (!AbstractComponent.PROCESS_WAVE_METHOD_NAME.equals(method.getName())) {
            for (final WaveData<?> wd : wave.waveDatas()) {
                // Add only wave items defined as parameter
                if (wd.key().isParameter()) {
                    parameterValues.add(wd.value());
                }
            }
        }

        // Add the current wave to process
        parameterValues.add(wave);

        try {

            ClassUtility.callMethod(method, getWaveReady(), parameterValues.toArray());

        } catch (final CoreException e) {
            LOGGER.error(WAVE_DISPATCH_ERROR, e);
            // Propagate the wave exception
            throw new WaveException(wave, e);
        }

    }
}
