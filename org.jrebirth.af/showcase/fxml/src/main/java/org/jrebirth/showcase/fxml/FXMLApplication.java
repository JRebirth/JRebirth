package org.jrebirth.showcase.fxml;

import javafx.application.Application;
import javafx.scene.layout.StackPane;

import org.jrebirth.core.application.DefaultApplication;
import org.jrebirth.core.ui.Model;
import org.jrebirth.showcase.fxml.ui.main.FXMLShowCaseModel;

/**
 * The class <strong>FXMLApplication</strong>.
 * 
 * @author
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
    public Class<? extends Model> getFirstModelClass() {
        return FXMLShowCaseModel.class;
    }

}
