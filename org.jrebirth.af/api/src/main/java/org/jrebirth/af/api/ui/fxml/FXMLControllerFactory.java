package org.jrebirth.af.api.ui.fxml;

import javafx.util.Callback;

import org.jrebirth.af.api.ui.Model;

/**
 * The interface <strong>FXMLControllerFactory</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface FXMLControllerFactory extends Callback<Class<?>, Object> {

    /**
     * @return Returns the relatedModel.
     */
    Model getRelatedModel();

    /**
     * @param relatedModel The relatedModel to set.
     */
    void setRelatedModel(Model relatedModel);
}
