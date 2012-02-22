package org.jrebirth.core.resource.color;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceFactories;

/**
 * The class <strong>AppColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public enum AppColors implements RGB255Color {

    /** The stage background color. */
    STAGE_BG(240, 240, 255, 1.0);

    /** The red property. */
    private int red;

    /** The green property. */
    private int green;

    /** The blue property. */
    private int blue;

    /** The opacity property. */
    private double opacity;

    /**
     * Private Constructor.
     * 
     * @param red the red value
     * @param green the green value
     * @param blue the blue value
     * @param opacity the opacity
     */
    private AppColors(final int red, final int green, final int blue, final double opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double opacity() {
        return this.opacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color get() {
        return factory().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorFactory factory() {
        return (ColorFactory) ResourceFactories.COLOR_FACTORY.use();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int red() {
        return this.red;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int green() {
        return this.green;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int blue() {
        return this.blue;
    }
}
