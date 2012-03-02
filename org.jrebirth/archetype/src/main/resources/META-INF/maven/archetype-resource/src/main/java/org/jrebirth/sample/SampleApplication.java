package org.jrebirth.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jrebirth.core.application.AbstractApplication;
import org.jrebirth.core.ui.Model;
import org.jrebirth.sample.ui.SampleModel;

/**
 * The class <strong>SampleApplication</strong>.
 * 
 * @author 
 * 
 * @version 
 * @since 
 */
public final class SampleApplication extends AbstractApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(SampleApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return SampleModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "JavaFX 2.0 - Sample Application";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        stage.setFullScreen(false);
        stage.setWidth(1040);
        stage.setHeight(800);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {
        // TODO to override
    }
}
