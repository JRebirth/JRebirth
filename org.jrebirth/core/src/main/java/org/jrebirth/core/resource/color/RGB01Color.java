package org.jrebirth.core.resource.color;

/**
 * The interface <strong>RGB01Color</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public class RGB01Color extends AbstractBaseColor {

    /** The green value 0-255. */
    private final double red;

    /** The green value 0-255. */
    private final double green;

    /** The blue value 0-255. */
    private final double blue;

    /**
     * Default Constructor.
     * 
     * @param red
     * @param green
     * @param blue
     */
    public RGB01Color(final double red, final double green, final double blue) {
        super();
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Default Constructor.
     * 
     * @param red
     * @param green
     * @param blue
     * @param opacity
     */
    public RGB01Color(final double red, final double green, final double blue, final double opacity) {
        super(opacity);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Return the red value 0.0-1.0.
     * 
     * @return Returns the red.
     */
    public double red() {
        return this.red;
    }

    /**
     * Return the green value 0.0-1.0.
     * 
     * @return Returns the green.
     */
    public double green() {
        return this.green;
    }

    /**
     * Return the blue value 0.0-1.0.
     * 
     * @return Returns the blue.
     */
    public double blue() {
        return this.blue;
    }

}
