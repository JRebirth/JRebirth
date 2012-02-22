package org.jrebirth.core.ui.impl;

import org.jrebirth.core.link.Wave;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.ui.View;

/**
 * 
 * The interface <strong>DefaultModel</strong>.
 * 
 * Default implementation of the model.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 199 $ $Author: sbordes $
 * @since $Date: 2011-11-27 21:30:11 +0100 (dim., 27 nov. 2011) $
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
