package org.jrebirth.fxform;

import javafx.application.Application;
import javafx.scene.layout.StackPane;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;

/**
 * The class <strong>FXFormApplication</strong>.
 * 
 * @author Sébastien Bordes
 */
public class FXFormApplication extends DefaultApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(FXFormApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        return PersonFormModel.class;
    }

}
