package org.jrebirth.af.core.concurrent;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.af.api.application.Configuration;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;

/**
 * The class <strong>ThreadApplication</strong>.
 *
 * @author Sébastien Bordes
 */
@Configuration(value = ".*-jrebirth", extension = "properties", schedule = 60)
public class ThreadApplication extends DefaultApplication<Pane> {

    private static ThreadApplication instance;

    public static ThreadApplication getInstance() {
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
        return "Thread Application";
    }

}
