package org.jrebirth.core.ui.fxml;

import javafx.fxml.Initializable;

import org.jrebirth.core.ui.View;

/**
 * The interface <strong>FXMLController</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface FXMLController extends Initializable {

    /**
     * Set the view that load this FXML component.
     * 
     * @param view the linked view
     */
    void setView(View<?, ?, ?> view);

    /**
     * Return the linked view that load this component.
     * 
     * @return the linked view
     */
    View<?, ?, ?> getView();

}
