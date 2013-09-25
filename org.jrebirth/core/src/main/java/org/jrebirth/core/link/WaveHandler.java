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

import org.jrebirth.core.facade.WaveReady;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.checker.WaveChecker;

/**
 * The Class WaveHandler.
 */
public class WaveHandler {

    /** The wave ready component. */
    private final WaveReady waveReady;

    /** The wave checker, can be null . */
    private final WaveChecker waveChecker;

    /**
     * Instantiates a new wave handler.
     * 
     * @param waveReady the wave ready component
     * @param waveChecker the wave checker, can be null
     */
    public WaveHandler(final WaveReady waveReady, final WaveChecker waveChecker) {
        super();
        this.waveReady = waveReady;
        this.waveChecker = waveChecker;
    }

    /**
     * Check the wave if the wave checker is not null.
     * 
     * @param wave the wave
     * @return true, if successful
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
    public WaveReady getWaveReady() {
        return this.waveReady;
    }
}
