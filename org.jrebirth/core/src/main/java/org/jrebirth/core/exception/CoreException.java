package org.jrebirth.core.exception;

/***
 * 
 * The class <strong>CoreException</strong>.
 * 
 * This is the exception that can be thrown by the JRebirth core module.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 63 $ $Author: sbordes $
 * @since $Date: 2011-10-14 23:21:04 +0200 (ven., 14 oct. 2011) $
 */
public class CoreException extends Exception {

    /**
     * The constant used for serialization.
     */
    private static final long serialVersionUID = 112036992331510469L;

    /**
     * Constructor with message and throwable.
     * 
     * @param message the message to display.
     * @param t the base exception thrown
     */
    public CoreException(final String message, final Throwable t) {
        super(message, t);
    }

    /**
     * Constructor without base exception.
     * 
     * @param message the message to display.
     */
    public CoreException(final String message) {
        super(message);
    }

    /**
     * Constructor without custom message.
     * 
     * @param t the base exception thrown
     */
    public CoreException(final Throwable t) {
        super(t);
    }

}
