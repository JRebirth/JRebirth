package org.jrebirth.core.link;

import org.jrebirth.core.exception.JRebirthThreadException;
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
     * MUST BE CALLED into the JRebirthThread.
     * 
     * @param wave the object that information data
     * 
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void sendWave(final Wave wave) throws JRebirthThreadException;

    /**
     * Start to listen a defined type of wave.
     * 
     * MUST BE CALLED into the JRebirthThread.
     * 
     * @param linkedObject an object that can process the content of a wave
     * @param waveType the type of wave that interests the object (one or many)
     * 
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void listen(final WaveReady linkedObject, final WaveType... waveType) throws JRebirthThreadException;

    /**
     * Stop to listen a defined type of wave.
     * 
     * MUST BE CALLED into the JRebirthThread.
     * 
     * @param linkedObject an object that can process the content of a wave
     * @param waveType the type of wave that doesn't interest the object anymore (one or many)
     * 
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void unlisten(final WaveReady linkedObject, final WaveType... waveType) throws JRebirthThreadException;

}
