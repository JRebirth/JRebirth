package org.jrebirth.core.ui;

import javafx.scene.Node;

import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.facade.UniqueKey;

/**
 * The interface <strong>Model</strong>.
 * 
 * The contract for the model layer.
 * 
 * @author Sébastien Bordes
 */
public interface Model extends FacadeReady<Model> {

    /**
     * Return the view.
     * 
     * @return the view managed
     */
    View<?, ?, ?> getView();

    /**
     * Return the root node.
     * 
     * @return the root node of the managed view
     */
    Node getRootNode();

    /**
     * Return the root model (for inner model).
     * 
     * @return the root model or null
     */
    Model getRootModel();

    /**
     * Define the root model for an inner model.
     * 
     * @param rootModel The rootModel to set.
     */
    void setRootModel(Model rootModel);

    /**
     * Get an inner model.
     * 
     * If the model isn't registered create it
     * 
     * @param innerModel the enumeration entry that describe the inner model
     * @param innerModelKey key used to identify each innerModle of the same type
     * 
     * @return the inner model instance
     */
    Model getInnerModel(InnerModels innerModel, UniqueKey... innerModelKey);

}
