package org.jrebirth.af.core.application.apps;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.af.api.application.Configuration;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;

/**
 * The class <strong>EmptyTestApplication</strong>.
 *
 * @author Sébastien Bordes
 */
@Configuration(".-jrebirth")
public class EmptyTestApplication extends DefaultApplication<Pane> {

    private static EmptyTestApplication instance;

    public static EmptyTestApplication getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
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
    protected String applicationTitle() {
        return "Test Application";
    }

}
