package org.jrebirth.core.wave;

/**
 * The class <strong>WaveListener</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface WaveListener {

    void waveCreated(Wave wave);

    void waveSent(Wave wave);

    void waveProcessed(Wave wave);

    void waveConsumed(Wave wave);
}
