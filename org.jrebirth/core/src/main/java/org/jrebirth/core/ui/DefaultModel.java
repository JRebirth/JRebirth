package org.jrebirth.core.ui;

import org.jrebirth.core.link.Wave;

/**
 * 
 * The interface <strong>DefaultModel</strong>.
 * 
 * Default implementation of the model.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the current model
 * @param <V> the class type of the view managed by this model
 */
public class DefaultModel<M extends Model, V extends View<?, ?, ?>> extends AbstractModel<M, V> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do generic
    }

}
