package org.jrebirth.core.resource.color;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.Resource;

/**
 * The interface <strong>JRebirthColor</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 */
public interface JRebirthColor extends Resource<Color, ColorFactory> {

    /**
     * Return the opacity of the color.
     * 
     * @return the opacity
     */
    Double opacity();
}
