package org.jrebirth.core.resource.color;

/**
 * The interface <strong>HSBColor</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface HSBColor extends JRebirthColor {

    /**
     * Return the hue value 0.0-1.0.
     * 
     * @return the hue value
     */
    double hue();

    /**
     * Return the saturation value 0.0-1.0.
     * 
     * @return the saturation value
     */
    double saturation();

    /**
     * Return the brightness value 0.0-1.0.
     * 
     * @return the brightness value
     */
    double brightness();
}
