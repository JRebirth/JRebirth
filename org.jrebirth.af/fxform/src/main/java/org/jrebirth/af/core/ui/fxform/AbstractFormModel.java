package org.jrebirth.af.core.ui.fxform;

import org.jrebirth.af.core.ui.object.AbstractObjectModel;

/**
 * 
 * The class <strong>AbstractFormModel</strong>.
 * 
 * @param <M>
 * @param <V>
 * @param <B>
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractFormModel<M extends AbstractFormModel<?, ?, ?>, V extends AbstractFormView<?, ?, B>, B extends Object> extends AbstractObjectModel<M, V, B> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {
        // super.bind();
        getView().getRootNode().setSource(getObject());
    }
}
