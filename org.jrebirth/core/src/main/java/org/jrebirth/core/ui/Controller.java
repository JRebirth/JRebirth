package org.jrebirth.core.ui;

import javafx.scene.Node;

import org.jrebirth.core.exception.CoreException;

/**
 * 
 * The interface <strong>Controller</strong>.
 * 
 * The contract for the controller layer.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> class type which will be the model of the view controlled, it must implements the #Model interface
 * @param <V> class type which will be control this controller, it must implements the #View interface
 */
public interface Controller<M extends Model, V extends View<M, ?, ?>> {

    /**
     * @return Returns the view.
     */
    V getView();

    /**
     * @return Returns the root node.
     */
    Node getRootNode();

    /**
     * @return Returns the model.
     */
    M getModel();

    /**
     * Activate the controller by calling the initializeEventHandler method.
     * 
     * Must be called after children initialization.
     * 
     * @throws CoreException if activation fails
     */
    void activate() throws CoreException;

}
