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
    Model relatedModel();

    /**
     * @param relatedModel The relatedModel to set.
     */
    void relatedModel(Model relatedModel);
}
