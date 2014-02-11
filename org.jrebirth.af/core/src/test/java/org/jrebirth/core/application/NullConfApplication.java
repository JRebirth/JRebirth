package org.jrebirth.core.application;

import javafx.scene.layout.Pane;
import org.jrebirth.core.ui.Model;

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
