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

import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveListener;
import org.jrebirth.af.core.wave.Wave.Status;

/**
 * The class <strong>ServiceTaskReturnWaveListener</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ServiceTaskReturnWaveListener implements WaveListener, ServiceMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(ServiceTaskReturnWaveListener.class);

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
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveConsumed(final Wave wave) {
        if (wave.getRelatedWave() != null) {
            // Return wave has been consumed, so the triggered wave can be consumed too
            LOGGER.trace(SERVICE_TASK_RETURN_CONSUMES, wave.getFromClass().getSimpleName(), wave.getRelatedWave().toString());
            wave.getRelatedWave().setStatus(Status.Consumed);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveFailed(final Wave wave) {
        if (wave.getRelatedWave() != null) {
            // Return wave has failed, so the triggered wave must be marked as failed too
            LOGGER.log(SERVICE_TASK_HAS_FAILED, wave.getRelatedClass().getSimpleName(), wave.getRelatedWave().toString());
            wave.getRelatedWave().setStatus(Status.Failed);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveDestroyed(final Wave wave) {
        // Nothing to do yet
    }

}
