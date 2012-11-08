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
}
