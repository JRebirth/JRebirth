package org.jrebirth.af.showcase.fxml;

import javafx.application.Application;
import javafx.scene.layout.StackPane;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.showcase.fxml.ui.main.FXMLShowCaseModel;

/**
 * The class <strong>FXMLApplication</strong>.
 */
public final class FXMLApplication extends DefaultApplication<StackPane> {

    /**
     * Application launcher.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(FXMLApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        return FXMLShowCaseModel.class;
    }

}
