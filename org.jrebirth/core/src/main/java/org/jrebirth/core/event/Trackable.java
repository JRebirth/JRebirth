package org.jrebirth.core.event;

/**
 * The class <strong>Trackable</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface Trackable {

    /**
     * Return the persistant string.
     * 
     * @return the string
     */
    String persist();

    /**
     * Load a string.
     * 
     * @param str the string to load
     */
    void load(String str);
}
