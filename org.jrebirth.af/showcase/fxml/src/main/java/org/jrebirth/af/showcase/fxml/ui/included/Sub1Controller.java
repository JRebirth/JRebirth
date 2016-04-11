package org.jrebirth.af.showcase.fxml.ui.included;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.core.ui.fxml.AbstractFXMLController;
import org.jrebirth.af.showcase.fxml.beans.LoremIpsum;

public class Sub1Controller extends AbstractFXMLController<IncludedModel, View<IncludedModel, ?, ?>> {

    @FXML
    private TextArea textArea;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resource) {

        this.textArea.setText(((LoremIpsum) model().getFirstKeyPart()).getPart1());

    }
}
