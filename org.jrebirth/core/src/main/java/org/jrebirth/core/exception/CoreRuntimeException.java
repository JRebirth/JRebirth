package org.jrebirth.core.exception;

/***
 * 
 * The class <strong>CoreRuntimeException</strong>.
 * 
 * This is the exception that can be thrown by the JRebirth core module.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 63 $ $Author: sbordes $
 * @since $Date: 2011-10-14 23:21:04 +0200 (ven., 14 oct. 2011) $
 */
public class CoreRuntimeException extends RuntimeException {

    /**
     * The constant used for serialization.
     */
    private static final long serialVersionUID = 1122356992331510469L;

    /**
     * Constructor with message and throwable.
     * 
     * @param message the message to display.
     * @param t the base exception thrown
     */
    public CoreRuntimeException(final String message, final Throwable t) {
        super(message, t);
    }

    /**
     * Constructor without base exception.
     * 
     * @param message the message to display.
     */
    public CoreRuntimeException(final String message) {
        super(message);
    }

    /**
     * Constructor without custom message.
     * 
     * @param t the base exception thrown
     */
    public CoreRuntimeException(final Throwable t) {
        super(t);
    }

}
