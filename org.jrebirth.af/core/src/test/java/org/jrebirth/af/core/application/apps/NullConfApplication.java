package org.jrebirth.af.core.application.apps;

import javafx.scene.layout.Pane;

import org.jrebirth.af.api.application.Configuration;
import org.jrebirth.af.api.application.Localized;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;

/**
 * The class <strong>NullConfApplication</strong>.
 *
 * @author Sébastien Bordes
 */
@Configuration
@Localized
public class NullConfApplication extends DefaultApplication<Pane> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        return null;
    }

}
