package org.jrebirth.core.resource.font;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The interface <strong>FamilyFont</strong>.
 * 
 * @author Sébastien Bordes
 */
public class FamilyFont extends AbstractBaseFont {

    /** the font size. */
    private final double size;

    /** the family name. */
    private final String family;

    /** the font posture. */
    private final FontPosture posture;

    /** the font weight. */
    private final FontWeight weight;

    /**
     * Default Constructor.
     * 
     * @param family
     * @param size
     * @param weight
     * @param posture
     */
    public FamilyFont(final FontName name, final String family, final double size, final FontWeight weight, final FontPosture posture) {
        super(name);
        this.family = family;
        this.size = size;
        this.weight = weight;
        this.posture = posture;
    }

    /**
     * Return the font size.
     * 
     * @return the font size
     */
    public double size() {
        return this.size;
    }

    /**
     * Return the family name.
     * 
     * @return the family name
     */
    public String family() {
        return this.family;
    }

    /**
     * Return the font posture.
     * 
     * @return the font posture
     */
    FontPosture posture() {
        return this.posture;
    }

    /**
     * Return the font weight.
     * 
     * @return the font weight
     */
    FontWeight weight() {
        return this.weight;
    }
}
