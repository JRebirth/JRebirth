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
package org.jrebirth.core.wave;

/**
 * The class <strong>WaveListener</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface WaveListener {

    /**
     * The wave has just been created.
     * 
     * @param wave the created wave
     */
    void waveCreated(Wave wave);

    /**
     * The wave has just been sent to the notifier.
     * 
     * @param wave the sent wave
     */
    void waveSent(Wave wave);

    /**
     * The wave is being processed.
     * 
     * @param wave the processed wave
     */
    void waveProcessed(Wave wave);

    /**
     * The wave has just been cancelled.
     * 
     * @param wave the cancelled wave
     */
    void waveCancelled(Wave wave);

    /**
     * The wave has just been consumed.
     * 
     * @param wave the consumed wave
     */
    void waveConsumed(Wave wave);

    /**
     * The wave processing has failed.
     * 
     * @param wave the failed wave
     */
    void waveFailed(Wave wave);

    /**
     * The wave has just been destroyed.
     * 
     * @param wave the destroyed wave
     */
    void waveDestroyed(Wave wave);
}
