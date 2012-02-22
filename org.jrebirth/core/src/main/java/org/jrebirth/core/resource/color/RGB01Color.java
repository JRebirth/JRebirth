package org.jrebirth.core.resource.color;

/**
 * The interface <strong>RGB01Color</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface RGB01Color extends JRebirthColor {

    /**
     * Return the red value 0.0-1.0.
     * 
     * @return the red value
     */
    double red();

    /**
     * Return the green value 0.0-1.0.
     * 
     * @return the green value
     */
    double green();

    /**
     * Return the blue value 0.0-1.0.
     * 
     * @return the blue value
     */
    double blue();
}
