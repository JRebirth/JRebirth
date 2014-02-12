package org.jrebirth.af.core.ui.fxform;

import org.jrebirth.af.core.ui.Controller;

/**
 * The class <strong>DefaultFormView</strong>.
 * 
 * @author Sébastien Bordes
 */
public class DefaultFormView<M extends AbstractFormModel<M, ?, B>, C extends Controller<M, AbstractFormView<M, C, B>>, B extends Object>
        extends AbstractFormView<M, C, B> {

    /**
     * Default Constructor.
     * 
     * @param model the model of the view
     */
    public DefaultFormView(final M model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        // Custom code used to update the root node and add some children
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        // Custom code to process when the view is displayed the first time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        // Custom code to process when the view is displayed the 1+n time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Custom code to process when the view is hidden
    }

}
