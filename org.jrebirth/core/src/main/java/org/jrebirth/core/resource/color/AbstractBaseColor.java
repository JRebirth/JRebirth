package org.jrebirth.core.resource.color;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * The interface <strong>AbstractBaseColor</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public abstract class AbstractBaseColor implements ColorParams {

    /** The property used to store the opacity of the color. */
    private final SimpleDoubleProperty opacityProperty = new SimpleDoubleProperty();

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
        this.opacityProperty.set(opacity);
    }

    /**
     * @return Returns the opacity.
     */
    public Double opacity() {
        return this.opacityProperty.get();
    }

    /**
     * @return Returns the opacity.
     */
    public DoubleProperty opacityProperty() {
        return this.opacityProperty;
    }

}
