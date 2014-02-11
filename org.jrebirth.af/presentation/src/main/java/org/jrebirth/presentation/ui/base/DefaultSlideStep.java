package org.jrebirth.presentation.ui.base;

/**
 * The class <strong>DefaultSlideStep</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class DefaultSlideStep implements SlideStep {

    /** The slide step name defined as slide content name. */
    private final String name;

    /**
     * @param name
     */
    public DefaultSlideStep(String name) {
        super();
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return name;
    }

}
