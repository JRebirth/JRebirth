package org.jrebirth.core.resource.font;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The interface <strong>FamilyFont</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface FamilyFont extends JRebirthFont {

    /**
     * Return the family name.
     * 
     * @return the family name
     */
    String family();

    /**
     * Return the font size.
     * 
     * @return the font size
     */
    double size();

    /**
     * Return the font posture.
     * 
     * @return the font posture
     */
    FontPosture posture();

    /**
     * Return the font weight.
     * 
     * @return the font weight
     */
    FontWeight weight();
}
