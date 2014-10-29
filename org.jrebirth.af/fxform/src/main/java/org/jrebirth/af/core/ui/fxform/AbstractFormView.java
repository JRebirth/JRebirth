package org.jrebirth.af.core.ui.fxform;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.ui.Controller;
import org.jrebirth.af.core.ui.AbstractView;

import com.dooapp.fxform.FXForm;

//M, AbstractFormView<M, B, C>, B
public abstract class AbstractFormView<M extends AbstractFormModel<M, ?, ?>, C extends Controller<M, ?>, B extends Object> extends AbstractView<M, FXForm<B>, C> {

    /**
     * Default Constructor.
     * 
     * @param model the model of the view
     */
    public AbstractFormView(final M model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected FXForm<B> buildRootNode() throws CoreException {
        return new FXForm<>();
    }

}
