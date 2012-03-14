package org.jrebirth.core.exception;

/**
 * 
 * The class <strong>JRebirthThreadException</strong>.
 * 
 * This is the exception that can be thrown if JRebirth code is executed outside the JRebirth Thread.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 63 $ $Author: sbordes $
 * @since $Date: 2011-10-14 23:21:04 +0200 (ven., 14 oct. 2011) $
 */
public class JRebirthThreadException extends Exception {

    /**
     * The constant used for serialization.
     */
    private static final long serialVersionUID = 112036725331588469L;

    /**
     * Default Constructor.
     */
    public JRebirthThreadException() {
        super("Current code must be executed into the JRebirth Thread.");
    }

}
