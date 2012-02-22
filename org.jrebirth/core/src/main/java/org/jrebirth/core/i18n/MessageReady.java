package org.jrebirth.core.i18n;

/**
 * The class <strong>MessageReady</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface MessageReady {

    /**
     * @return Returns the symbolicName formerly returned by name() method.
     */
    String getSymbolicName();

    /**
     * Return the message translated with the right language.
     * 
     * @param parameter used by parameterized formatted string
     * 
     * @return Returns the translated language.
     */
    String get(Object... parameter);
}
