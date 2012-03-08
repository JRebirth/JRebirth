package org.jrebirth.core.ui;

import javafx.scene.Node;

import org.jrebirth.core.exception.CoreException;

/**
 * 
 * The class <strong>DefaultView</strong>.
 * 
 * Default implementation of the view.
 * 
 * @author Sébastien Bordes
 * 
 * 
 * @param <M> The class type of the model of the view
 * @param <N> Any object that is a JavaFx2 Node
 * @param <C> The class type of the controller of the view
 */
public class DefaultView<M extends Model, N extends Node, C extends Controller<?, ?>> extends AbstractView<M, N, C> {

    /**
     * Default Constructor.
     * 
     * @param model the model of the view
     * 
     * @throws CoreException if an error occurred while initializing
     */
    public DefaultView(final M model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {
        // Nothing to do generic
    }

}
