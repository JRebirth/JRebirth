package org.jrebirth.core.resource;

import org.jrebirth.core.resource.color.ColorFactory;
import org.jrebirth.core.resource.factory.ResourceFactory;
import org.jrebirth.core.resource.font.FontFactory;

/**
 * The class <strong>ResourceFactories</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum ResourceFactories {

    /** The factory used to manage colors. */
    COLOR_FACTORY(new ColorFactory()),

    /** The factory used to manage fonts. */
    FONT_FACTORY(new FontFactory());

    /** The factory singleton. */
    private ResourceFactory<?, ?, ?> factory;

    /**
     * Private Constructor.
     * 
     * @param factory the singleton factory
     */
    private ResourceFactories(final ResourceFactory<?, ?, ?> factory) {
        this.factory = factory;
    }

    /**
     * @return the singleton of the factory
     */
    public ResourceFactory<?, ?, ?> use() {
        return this.factory;
    }
}
