package org.jrebirth.core.i18n;

/**
 * The class <strong>JRebirthMessages</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public enum JRebirthMessages implements MessageReady {

    /** The Error message. */
    ERROR;

    /**
     * Default Constructor.
     */
    private JRebirthMessages() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(final Object... parameter) {
        return JRebirthMessageManager.getInstance().get(this, parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSymbolicName() {
        return name();
    }

}
