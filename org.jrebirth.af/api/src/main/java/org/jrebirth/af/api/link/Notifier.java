/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.api.link;

import java.lang.reflect.Method;

import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.exception.JRebirthThreadException;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveType;
import org.jrebirth.af.api.wave.checker.WaveChecker;

/**
 * The interface <strong>Notifier</strong>.
 *
 * Use to propagate waves through application layers.
 *
 * @author Sébastien Bordes
 */
public interface Notifier {

    /**
     * Send wave to all facade.
     *
     * MUST BE CALLED into the JRebirthThread.
     *
     * @param wave the object that information data
     *
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void sendWave(final Wave wave) throws JRebirthThreadException;

    /**
     * Start to listen a defined type of wave.
     *
     * The WaveChecker allow to route wave only to component that are concerned.
     *
     * MUST BE CALLED into the JRebirthThread.
     *
     * @param linkedObject an object that can process the content of a wave
     * @param waveChecker the wave checker to filter unwanted wave to be processed by other components
     * @param method the default method to call
     * @param waveTypes the type(s) of wave that interests the object (one or many)
     *
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void listen(final Component<?> linkedObject, final WaveChecker waveChecker, final Method method, final WaveType... waveTypes) throws JRebirthThreadException;

    /**
     * Stop to listen a defined type of wave.
     *
     * MUST BE CALLED into the JRebirthThread.
     *
     * @param linkedObject an object that can process the content of a wave
     * @param waveType the type of wave that doesn't interest the object anymore (one or many)
     *
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void unlisten(final Component<?> linkedObject, final WaveType... waveType) throws JRebirthThreadException;

    /**
     * Stop to listen all waveType listened by the component.
     *
     * MUST BE CALLED into the JRebirthThread.
     *
     * @param linkedObject an object that can process the content of a wave
     *
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void unlistenAll(final Component<?> linkedObject) throws JRebirthThreadException;
}
