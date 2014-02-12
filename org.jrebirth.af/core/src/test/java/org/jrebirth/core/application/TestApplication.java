package org.jrebirth.core.application;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.af.core.application.Configuration;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.core.ui.Model;

/**
 * The class <strong>TestApplication</strong>.
 * 
 * @author Sébastien Bordes
 */
@Configuration(".-jrebirth")
public class TestApplication extends DefaultApplication<Pane> {

    private static TestApplication instance;

    public static TestApplication getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        instance = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "Test Application";
    }

}
