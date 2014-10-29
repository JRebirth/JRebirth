package org.jrebirth.af.api.ui.fxml;

import javafx.scene.Node;

/**
 * The class <strong>FXMLComponent</strong>.
 *
 * @author Sébastien Bordes
 */
public interface FXMLComponent {

    /**
     * @return Returns the node.
     */
    Node getNode();

    /**
     * Return the controller or null.
     *
     * @return Returns the controller.
     */
    FXMLController getController();

}
