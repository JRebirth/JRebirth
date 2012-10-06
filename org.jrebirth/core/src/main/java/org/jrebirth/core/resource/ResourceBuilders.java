package org.jrebirth.core.resource;

import org.jrebirth.core.resource.color.ColorBuilder;
import org.jrebirth.core.resource.factory.ResourceBuilder;
import org.jrebirth.core.resource.font.FontBuilder;

/**
 * The class <strong>ResourceBuilders</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum ResourceBuilders {

    /** The factory used to manage colors. */
    COLOR_BUILDER(new ColorBuilder()),

    /** The factory used to manage fonts. */
    FONT_BUILDER(new FontBuilder());

    /** The factory singleton. */
    private ResourceBuilder<?, ?, ?> builder;

    /**
     * Private Constructor.
     * 
     * @param builder the singleton factory
     */
    private ResourceBuilders(final ResourceBuilder<?, ?, ?> builder) {
        this.builder = builder;
    }

    /**
     * @return the singleton of the builder
     */
    public ResourceBuilder<?, ?, ?> use() {
        return this.builder;
    }
}
