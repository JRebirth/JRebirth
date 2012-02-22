package org.jrebirth.core.ui.impl;

import javafx.scene.Node;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.Controller;
import org.jrebirth.core.ui.Model;

/**
 * 
 * The class <strong>DefaultView</strong>.
 * 
 * Default implementation of the view.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 199 $ $Author: sbordes $
 * @since $Date: 2011-11-27 21:30:11 +0100 (dim., 27 nov. 2011) $
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
    public void animate() {
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
