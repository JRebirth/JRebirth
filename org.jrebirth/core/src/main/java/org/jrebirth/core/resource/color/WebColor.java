package org.jrebirth.core.resource.color;

/**
 * The interface <strong>WebColor</strong>.
 * 
 * @author Sébastien Bordes
 */
public class WebColor extends AbstractBaseColor {

    /** The hexadecimal string value (#FFFFFF). */
    private final String hex;

    /**
     * Default Constructor.
     * 
     * @param hex the hexadecimal value
     */
    public WebColor(final String hex) {
        super();
        this.hex = hex;
    }

    /**
     * Default Constructor.
     * 
     * @param hex the hexadecimal value
     * @param opacity the color opacity
     */
    public WebColor(final String hex, final double opacity) {
        super(opacity);
        this.hex = hex;
    }

    /**
     * Return the hexadecimal string value.
     * 
     * @return Returns the hexadecimal value.
     */
    public String hex() {
        return this.hex;
    }

}
