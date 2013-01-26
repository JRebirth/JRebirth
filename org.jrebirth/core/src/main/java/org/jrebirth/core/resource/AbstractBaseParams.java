package org.jrebirth.core.resource;

import org.jrebirth.core.resource.color.ResourceParams;

/**
 * The class <strong>AbstractBaseParams</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseParams implements ResourceParams {

    /** The flag that indicates if the resource params has changed. */
    private boolean changed;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChanged() {
        // Nothing to do yet
        return this.changed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hasChanged(final boolean changed) {
        this.changed = changed;
    }

}
