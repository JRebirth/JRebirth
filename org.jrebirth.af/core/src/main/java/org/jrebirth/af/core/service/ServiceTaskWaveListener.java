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

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveListener;
import org.jrebirth.af.core.wave.JRebirthWaves;

/**
 * The class <strong>ServiceTaskWaveListener</strong> is used to track Progression of ServiceTask.
 *
 * @author Sébastien Bordes
 */
public final class ServiceTaskWaveListener implements WaveListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveCreated(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveSent(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveProcessed(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveCancelled(final Wave wave) {
        // We can now remove the pending task (even if the return wave isn't processed TODO CHECK it)
        wave.get(JRebirthWaves.SERVICE_TASK).taskAchieved();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveConsumed(final Wave wave) {
    	// Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveHandled(final Wave wave) {

        // We can now remove the pending task (even if the return wave isn't processed TODO CHECK it)
        wave.get(JRebirthWaves.SERVICE_TASK).taskAchieved();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveFailed(final Wave wave) {
        // We can now remove the pending task (even if the return wave isn't processed TODO CHECK it)
        wave.get(JRebirthWaves.SERVICE_TASK).taskAchieved();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveDestroyed(final Wave wave) {
        // Nothing to do yet
    }

}
