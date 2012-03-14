package org.jrebirth.core.ui;

import javafx.scene.Node;

import org.jrebirth.core.exception.CoreException;

/**
 * The interface <strong>View</strong>.
 * 
 * The contract for the view layer.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> A class that implement the Model interface, this is the model of the view
 * @param <N> A class that extend the JavaFX node, this is the base component of the view
 * @param <C> The class type which will control this view, it must implements the controller interface
 */
public interface View<M extends Model, N extends Node, C extends Controller<?, ?>> {

    /**
     * Return the view root node.
     * 
     * @return the view base javafx node
     */
    N getRootNode();

    /**
     * Return the view model.
     * 
     * @return the view model
     */
    M getModel();

    /**
     * Return the view controller.
     * 
     * @return the view controller
     */
    C getController();

    /**
     * Prepare the view by creating all visual nodes.
     * 
     * @throws CoreException if the preparation fails
     */
    void prepare() throws CoreException;

    /**
     * Start the show animation of the view.
     */
    void show();

    /**
     * Start hide animation of the view.
     */
    void hide();

}
