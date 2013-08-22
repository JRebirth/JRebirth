package org.jrebirth.core.ui.fxform;

import com.dooapp.fxform.FXForm;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractView;
import org.jrebirth.core.ui.Controller;
import org.jrebirth.core.ui.object.ViewObject;

//M, AbstractFormView<M, B, C>, B
public abstract class AbstractFormView<M extends AbstractFormModel<M, ?, ?>, C extends Controller<M, ?>, B extends ViewObject> extends AbstractView<M, FXForm<B>, C> {

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
