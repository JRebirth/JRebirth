package org.jrebirth.af.core.ui.fxform;

import org.jrebirth.af.api.wave.Wave;

/**
 * The class <strong>DefaultFormModel</strong>.
 * 
 * @param <M>
 * @param <V>
 * @param <B>
 * 
 * @author Sébastien Bordes
 */
public class DefaultFormModel<M extends AbstractFormModel<?, ?, ?>, V extends AbstractFormView<?, ?, B>, B extends Object> extends AbstractFormModel<M, V, B> {

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
    protected void initInnerComponents() {
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
