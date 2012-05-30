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
    void doPrepare() throws CoreException;

    /**
     * Handle custom tasks to do the fist time after creation of the view.
     * 
     * For example : you could start the show animation of the view.
     */
    void doStart();

    /**
     * Handle custom tasks to do at each rendering of the view.
     * 
     * For example : play from start the start animation.
     */
    void doReload();

    /**
     * Handle custom tasks to do when the view is closed.
     * 
     * For example : you could start the hide animation of the view.
     */
    void doHide();

}
