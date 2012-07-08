package org.jrebirth.core.resource.color;

/**
 * The interface <strong>HSBColor</strong>.
 * 
 * @author Sébastien Bordes
 */
public class HSBColor extends AbstractBaseColor {

    /** The saturation value 0-255. */
    private final double hue;

    /** The saturation value 0-255. */
    private final double saturation;

    /** The brightness value 0-255. */
    private final double brightness;

    /**
     * Default Constructor.
     * 
     * @param hue the color hue
     * @param saturation the color saturation
     * @param brightness the color brightness
     */
    public HSBColor(final double hue, final double saturation, final double brightness) {
        super();
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }

    /**
     * Default Constructor.
     * 
     * @param hue the color hue
     * @param saturation the color saturation
     * @param brightness the color brightness
     * @param opacity the color opacity
     */
    public HSBColor(final double hue, final double saturation, final double brightness, final double opacity) {
        super(opacity);
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }

    /**
     * Return the hue value 0.0-1.0.
     * 
     * @return Returns the hue.
     */
    public double hue() {
        return this.hue;
    }

    /**
     * Return the saturation value 0.0-1.0.
     * 
     * @return Returns the saturation.
     */
    public double saturation() {
        return this.saturation;
    }

    /**
     * Return the brightness value 0.0-1.0.
     * 
     * @return Returns the brightness.
     */
    public double brightness() {
        return this.brightness;
    }

}
