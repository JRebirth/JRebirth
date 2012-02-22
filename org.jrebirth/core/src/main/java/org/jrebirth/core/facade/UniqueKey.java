package org.jrebirth.core.facade;

/**
 * The class <strong>UniqueKey</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface UniqueKey {

    /**
     * Return the unique key.
     * 
     * @return the unique key
     */
    String getUniqueKey();

    /**
     * Return the unique object if any or null.
     * 
     * @return the unique object or null if none
     */
    Object getValue();
}
