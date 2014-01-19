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
package org.jrebirth.core.link;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.facade.WaveReady;
import org.jrebirth.core.log.JRLogger;
import org.jrebirth.core.log.JRLoggerFactory;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.util.ClassUtility;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.checker.WaveChecker;

/**
 * The class <strong>WaveHandler</strong> is used to define how to manage a Wave for wave type subscribers.
 * 
 * @author Sébastien Bordes
 */
public class WaveHandler implements LinkMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(WaveHandler.class);

    /** The wave ready component that will handle the wave. */
    private final WaveReady<?> waveReady;

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
    public WaveHandler(final WaveReady<?> waveReady, final WaveChecker waveChecker, final Method defaultMethod) {
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
    public WaveReady<?> getWaveReady() {
        return this.waveReady;
    }

    /**
     * Handle the wave into JAT for model component or into current thread for others.
     * 
     * @param wave the wave to manage
     * 
     * @throws WaveException if an error occurred whil processing the wave
     */
    public void handle(final Wave wave) throws WaveException {

        // If the notified class is part of the UI
        // We must perform this action into the JavaFX Application Thread
        if (getWaveReady() instanceof Model) {
            JRebirth.runIntoJAT(new AbstractJrbRunnable(getWaveReady().getClass().getSimpleName() + " handle wave " + wave.toString()) {

                /**
                 * {@inheritDoc}
                 */
                @Override
                protected void runInto() throws JRebirthThreadException {
                    try {
                        performHandle(wave);
                    } catch (final WaveException e) {
                        LOGGER.error(WAVE_HANDLING_ERROR, e);
                    }
                }
            });
        } else {
            // Otherwise can perform it right now into the current thread (JRebirthThread - JIT)
            performHandle(wave);
        }

    }

    /**
     * Perfom the handle independently of thread used.
     * 
     * @param wave the wave to manage
     * 
     * @throws WaveException if an error occurred whil processing the wave
     */
    private final void performHandle(final Wave wave) throws WaveException {

        try {
            // Build parameter list of the searched method
            final List<Object> parameterValues = new ArrayList<>();
            for (final WaveData<?> wd : wave.getWaveItems()) {
                // Add only wave items defined as parameter
                if (wd.getKey().isParameter()) {
                    parameterValues.add(wd.getValue());
                }
            }
            // Add the current wave to process
            parameterValues.add(wave);

            // Search the wave handler method to call
            final Method method = this.defaultMethod != null
                    // Method defined with annotation
                    ? this.defaultMethod
                    // Method computed according to wave prefix and wave type action name
                    : ClassUtility.getMethodByName(getWaveReady().getClass(), ClassUtility.underscoreToCamelCase(wave.getWaveType().toString()));

            if (method != null) {
                // store current visibility
                final boolean accessible = method.isAccessible();
                // let it accessible anyway
                method.setAccessible(true);
                // Call this method with right parameters
                method.invoke(getWaveReady(), parameterValues.toArray());
                // Reset default visibility
                method.setAccessible(accessible);
            }
        } catch (final NoSuchMethodException e) {

            LOGGER.info(CUSTOM_METHOD_NOT_FOUND, e.getMessage());
            // If no method was found, call the default method named 'processWave(wave)'
            try {
                ClassUtility.getMethodByName(getWaveReady().getClass(), AbstractWaveReady.PROCESS_WAVE_METHOD_NAME).invoke(getWaveReady(), wave);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ee) {
                // Propagate the wave exception
                throw new WaveException(wave, ee);
            }

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error(WAVE_DISPATCH_ERROR, e);
            // Propagate the wave exception
            throw new WaveException(wave, e);
        }
    }
}
