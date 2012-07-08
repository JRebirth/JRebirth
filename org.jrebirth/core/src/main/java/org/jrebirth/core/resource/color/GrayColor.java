package org.jrebirth.core.resource.color;

/**
 * The interface <strong>GrayColor</strong>.
 * 
 * @author Sébastien Bordes
 */
public class GrayColor extends AbstractBaseColor {

    /** The gray value. */
    private final int gray;

    /**
     * Default Constructor.
     * 
     * @param gray the gray component
     */
    public GrayColor(final int gray) {
        super();
        this.gray = gray;
    }

    /**
     * Default Constructor.
     * 
     * @param gray the gray component
     * @param opacity the color opacity
     */
    public GrayColor(final int gray, final double opacity) {
        super(opacity);
        this.gray = gray;
    }

    /**
     * Return the gray value.
     * 
     * @return Returns the gray.
     */
    public int gray() {
        return this.gray;
    }

}
