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
package org.jrebirth.core.command.basic;

import java.util.List;

import org.jrebirth.core.command.DefaultCommand;
import org.jrebirth.core.wave.JRebirthWaves;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>PrepareModelCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ChainWaveCommand extends DefaultCommand implements WaveListener {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ChainWaveCommand.class);

    /** The index of the wave to process. */
    private int index;

    /** The list of wave to be processed one after the other. */
    private List<Wave> waveList;

    /** The wave that launch this command. */
    private Wave sourceWave;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        // The first time we store the list of wave to run into the command field
        if (this.index == 0) {
            this.waveList = wave.get(JRebirthWaves.CHAINED_WAVES);
            this.sourceWave = wave;
        }

        unqueueWaves();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postExecute(final Wave wave) {
        // Nothing
    }

    /**
     * Recursive call.
     */
    private void unqueueWaves() {

        // Extract the next wave to run
        final Wave waveToRun = this.waveList.get(this.index);

        // The wave is null skip it and launch the next one
        if (waveToRun == null) {

            this.index++;
            // Run next command if any
            if (this.waveList.size() > this.index) {
                unqueueWaves();
            }
        } else {

            LOGGER.trace("Unqueue wave N° " + this.index + " >> " + waveToRun.toString());

            // Attach a listener to be informed when the wave will be consumed
            waveToRun.addWaveListener(this);
            // Send it to the queue in order to perform it
            sendWave(waveToRun);
        }

    }

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
    public void waveConsumed(final Wave wave) {
        this.index++;
        // Run next command if any
        if (this.waveList.size() > this.index) {
            unqueueWaves();
        } else {
            fireAchieve(this.sourceWave);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveFailed(final Wave wave) {
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
    public void waveDestroyed(final Wave wave) {
        // Nothing to do yet

    }
}
