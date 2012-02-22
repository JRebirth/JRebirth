package org.jrebirth.core.resource.color;

/**
 * The interface <strong>RGB255Color</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface RGB255Color extends JRebirthColor {

    /**
     * Return the red value 0-255.
     * 
     * @return the red value
     */
    int red();

    /**
     * Return the green value 0-255.
     * 
     * @return the green value
     */
    int green();

    /**
     * Return the blue value 0-255.
     * 
     * @return the blue value
     */
    int blue();
}
