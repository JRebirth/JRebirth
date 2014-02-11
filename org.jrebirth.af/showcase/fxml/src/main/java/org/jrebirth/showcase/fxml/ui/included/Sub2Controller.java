package org.jrebirth.showcase.fxml.ui.included;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import org.jrebirth.core.ui.View;
import org.jrebirth.core.ui.fxml.AbstractFXMLController;
import org.jrebirth.showcase.fxml.beans.LoremIpsum;

public class Sub2Controller extends AbstractFXMLController<IncludedModel, View<IncludedModel, ?, ?>> {

    @FXML
    private TextArea textArea;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resource) {

        this.textArea.setText(((LoremIpsum) getModel().getFirstKeyPart()).getPart2());

    }
}
