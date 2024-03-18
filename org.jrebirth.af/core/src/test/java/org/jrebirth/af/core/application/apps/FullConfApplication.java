package org.jrebirth.af.core.application.apps;

import javafx.scene.layout.Pane;

import org.jrebirth.af.api.application.Configuration;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;

/**
 * The class <strong>FullConfApplication</strong>.
 *
 * @author Sébastien Bordes
 */
@Configuration(value = ".*-jrebirth", extension = "properties", schedule = 60)
public class FullConfApplication extends DefaultApplication<Pane> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        return null;
    }

}
