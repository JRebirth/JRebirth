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
public enum AppColors implements ColorEnum {

    /** The stage background color. */
    STAGE_BG(new RGB255Color(240, 240, 255, 1.0));

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private AppColors(final ColorParams colorParams) {
        factory().storeParams(this, colorParams);
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
}
