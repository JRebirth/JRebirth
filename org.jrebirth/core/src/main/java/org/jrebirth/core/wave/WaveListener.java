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
     * The wave has just been destroyed.
     * 
     * @param wave the destroyed wave
     */
    void waveDestroyed(Wave wave);
}
