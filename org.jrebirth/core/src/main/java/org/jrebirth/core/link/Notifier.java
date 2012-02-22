package org.jrebirth.core.link;

import org.jrebirth.core.facade.WaveReady;

/**
 * The interface <strong>Notifier</strong>.
 * 
 * Use to propagate waves through application layers.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface Notifier {

    /**
     * Send wave to all facade.
     * 
     * @param wave the object that information data
     */
    void sendWave(final Wave wave);

    /**
     * Start to listen a defined type of wave.
     * 
     * @param linkedObject an object that can process the content of a wave
     * @param waveType the type of wave that interests the object (one or many)
     */
    void listen(final WaveReady linkedObject, final WaveType... waveType);

    /**
     * Stop to listen a defined type of wave.
     * 
     * @param linkedObject an object that can process the content of a wave
     * @param waveType the type of wave that doesn't interest the object anymore (one or many)
     */
    void unlisten(final WaveReady linkedObject, final WaveType... waveType);

}
