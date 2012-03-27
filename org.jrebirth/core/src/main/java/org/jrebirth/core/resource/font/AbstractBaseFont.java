package org.jrebirth.core.resource.font;

/**
 * The interface <strong>AbstractBaseColor</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public abstract class AbstractBaseFont implements FontParams {

    /** The opacity of the color. */
    private final FontName name;

    /**
     * Default Constructor.
     * 
     * @param name the name to use.
     */
    public AbstractBaseFont(final FontName name) {
        super();
        this.name = name;
    }

    /**
     * @return Returns the font name.
     */
    protected FontName name() {
        return this.name;
    }

}
