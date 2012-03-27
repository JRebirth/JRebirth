package org.jrebirth.core.resource.font;

/**
 * The interface <strong>RealFont</strong>.
 * 
 * @author Sébastien Bordes
 */
public class RealFont extends AbstractBaseFont {

    /** the font size. */
    private final double size;

    /**
     * Default Constructor.
     * 
     * @param name
     * @param size
     */
    public RealFont(final FontName name, final double size) {
        super(name);
        this.size = size;
    }

    /**
     * Return the font size.
     * 
     * @return the font size
     */
    public double size() {
        return this.size;
    }
}
