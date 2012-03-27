package org.jrebirth.core.resource.color;

/**
 * The interface <strong>AbstractBaseColor</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public abstract class AbstractBaseColor implements ColorParams {

    /** The opacity of the color. */
    private final Double opacity;

    /**
     * Default Constructor.
     */
    public AbstractBaseColor() {
        this(1.0);
    }

    /**
     * Default Constructor.
     * 
     * @param opacity the opacity to use.
     */
    public AbstractBaseColor(final Double opacity) {
        super();
        this.opacity = opacity;
    }

    /**
     * @return Returns the opacity.
     */
    protected Double opacity() {
        return this.opacity;
    }

}
