package org.jrebirth.core.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The class <strong>JRebirthMessageManager</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class JRebirthMessageManager {

    /** The singleton instance of the manager. */
    private static JRebirthMessageManager instance = new JRebirthMessageManager();

    /** The jrebirth bundle name. */
    private static final String BUNDLE_NAME = "org.jrebirth.core.messages";

    /** The Resources bundle built. */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    // FIX ME MANAGE BUNDLE NAME DYNAMICALLY

    /**
     * Default Constructor.
     */
    private JRebirthMessageManager() {
        // Nothing to do
    }

    /**
     * Get singleton instance.
     * 
     * @return the message manager instance
     */
    public static JRebirthMessageManager getInstance() {
        return instance;
    }

    /**
     * Return the internationalized message.
     * 
     * @param message the key of the message
     * @param parameter optional parameters used for format
     * 
     * @return the formatted internationalized message
     */
    public String get(final MessageReady message, final Object... parameter) {
        String res = null;
        try {
            res = RESOURCE_BUNDLE.getString(message.getSymbolicName());
        } catch (final MissingResourceException e) {
            res = '<' + message.getSymbolicName() + '>';
        }
        // TODO use string formatting
        return res;
    }
}
