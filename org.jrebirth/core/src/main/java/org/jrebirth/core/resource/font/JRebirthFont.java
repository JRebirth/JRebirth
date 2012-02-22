package org.jrebirth.core.resource.font;

import javafx.scene.text.Font;

import org.jrebirth.core.resource.Resource;

/**
 * The interface <strong>JRebirthFont</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface JRebirthFont extends Resource<Font, FontFactory> {

    /**
     * Return the name of the font.
     * 
     * @return the font name
     */
    FontName fontName();
}
