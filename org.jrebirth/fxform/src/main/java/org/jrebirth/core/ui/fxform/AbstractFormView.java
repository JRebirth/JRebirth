package org.jrebirth.core.ui.fxform;

import javafx.scene.Node;

import org.jrebirth.core.ui.AbstractController;
import org.jrebirth.core.ui.AbstractView;

//M, AbstractFormView<M, B, C>, B
public abstract class AbstractFormView<M extends AbstractFormModel<?, ?, ?>, N extends Node, C extends AbstractController<?, ?>, B extends Object> extends AbstractView<M, N, C> {

    /**
     * Default Constructor.
     * 
     * @param model the model of the view
     */
    public AbstractFormView(final M model) {
        super(model);
    }

}
