package org.jrebirth.af.core.application;

import javafx.scene.layout.Pane;

import org.jrebirth.af.api.application.Configuration;
import org.jrebirth.af.api.application.Localized;
import org.jrebirth.af.api.ui.Model;

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
    public Class<? extends Model> getFirstModelClass() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "Test Application";
    }

}
