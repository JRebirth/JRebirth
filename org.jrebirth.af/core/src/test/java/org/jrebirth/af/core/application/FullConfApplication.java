package org.jrebirth.af.core.application;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.af.api.application.Configuration;
import org.jrebirth.af.api.ui.Model;

/**
 * The class <strong>FullConfApplication</strong>.
 *
 * @author Sébastien Bordes
 */
@Configuration(value = ".*-jrebirth", extension = "properties", schedule = 60)
public class FullConfApplication extends DefaultApplication<Pane> {

    private static FullConfApplication instance;

    public static FullConfApplication getInstance() {
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
