package org.jrebirth.core.ui;

import javafx.scene.Node;

/**
 * 
 * The interface <strong>View</strong>.
 * 
 * The contract for the view layer.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 199 $ $Author: sbordes $
 * @since $Date: 2011-11-27 21:30:11 +0100 (dim., 27 nov. 2011) $
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
     * Start the animation of the view..
     */
    void animate();

}
