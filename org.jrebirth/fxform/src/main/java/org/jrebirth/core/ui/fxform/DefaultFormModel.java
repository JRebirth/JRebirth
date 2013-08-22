package org.jrebirth.core.ui.fxform;

import org.jrebirth.core.ui.object.ViewObject;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>DefaultFormModel</strong>.
 * 
 * @param <M>
 * @param <V>
 * @param <B>
 * 
 * @author Sébastien Bordes
 */
public class DefaultFormModel<M extends AbstractFormModel<?, ?, ?>, V extends AbstractFormView<?, ?, B>, B extends ViewObject> extends AbstractFormModel<M, V, B> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerModels() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do generic
    }

}
