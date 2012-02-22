package org.jrebirth.core.resource.font;

/**
 * The interface <strong>FontName</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface FontName {

    /**
     * Return the unique name of the font. Must provide a mechanism to transform _ into space.
     * 
     * @return the system name of the font
     */
    String get();

}
