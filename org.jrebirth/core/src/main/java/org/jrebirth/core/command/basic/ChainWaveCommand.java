package org.jrebirth.core.command.basic;

import java.util.List;

import org.jrebirth.core.command.DefaultCommand;
import org.jrebirth.core.wave.JRebirthWaves;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveListener;

/**
 * The class <strong>PrepareModelCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ChainWaveCommand extends DefaultCommand implements WaveListener {

    private int index;
    private List<Wave> waveList;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        if (this.index == 0) {
            this.waveList = wave.get(JRebirthWaves.CHAINED_WAVES);
        }
        final Wave waveToRun = this.waveList.get(this.index);
        if (waveToRun != null) {

            wave.addWaveListener(this);
            sendWave(waveToRun);

        } else {
            this.index++;
            // Run next command if any
            if (this.waveList.size() > this.index) {
                execute(wave);
            }
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
            execute(wave);
        }
    }
}
