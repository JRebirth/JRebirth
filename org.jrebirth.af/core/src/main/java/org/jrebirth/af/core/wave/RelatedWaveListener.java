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
package org.jrebirth.af.core.wave;

import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.Wave.Status;
import org.jrebirth.af.api.wave.WaveListener;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.service.ServiceMessages;

/**
 * The class <strong>RelatedWaveListener</strong>.
 *
 * @author Sébastien Bordes
 */
public final class RelatedWaveListener implements WaveListener, WaveMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(RelatedWaveListener.class);

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
//        if (wave.relatedWave() != null) {
//            // Return wave has been consumed, so the triggered wave can be consumed too
//            LOGGER.trace(SERVICE_TASK_RETURN_CONSUMES, wave.fromClass().getSimpleName(), wave.relatedWave().toString());
//            wave.relatedWave().status(Status.Consumed);
//        }
    	// Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveHandled(final Wave wave) {
        if (wave.relatedWave() != null) {
            // Return wave has been handled, so the triggered wave can be handled too
            LOGGER.trace(RELATED_WAVE_HANDLES, wave.fromClass().getSimpleName(), wave.relatedWave().toString());
            wave.relatedWave().status(Status.Handled);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveFailed(final Wave wave) {
        if (wave.relatedWave() != null) {
            // Return wave has failed, so the triggered wave must be marked as failed too
            LOGGER.log(RELATED_WAVE_HAS_FAILED, wave.componentClass().getSimpleName(), wave.relatedWave().toString());
            wave.relatedWave().status(Status.Failed);
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
