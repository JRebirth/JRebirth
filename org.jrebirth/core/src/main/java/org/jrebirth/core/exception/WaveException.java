package org.jrebirth.core.exception;

import org.jrebirth.core.link.Wave;

/***
 * 
 * The class <strong>WaveException</strong>.
 * 
 * This is the exception that can be thrown by the JRebirth core module when a wave generate an error.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 63 $ $Author: sbordes $
 * @since $Date: 2011-10-14 23:21:04 +0200 (ven., 14 oct. 2011) $
 */
public class WaveException extends Exception {

    /**
     * The constant used for serialization.
     */
    private static final long serialVersionUID = 112036772331588469L;

    /**
     * Constructor with message and throwable.
     * 
     * @param wave the wave which fails.
     * @param t the base exception thrown
     */
    public WaveException(final Wave wave, final Throwable t) {
        super(wave.toString(), t);
    }

    /**
     * Constructor without base exception.
     * 
     * @param wave the wave which fails.
     */
    public WaveException(final Wave wave) {
        super(wave.toString());
    }

    /**
     * Constructor without custom message.
     * 
     * @param t the base exception thrown
     */
    public WaveException(final Throwable t) {
        super(t);
    }

}
