package org.jrebirth.af.core.application.apps;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.af.api.application.Configuration;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;

/**
 * The class <strong>BorderPaneTestApplication</strong>.
 *
 * @author Sébastien Bordes
 */
@Configuration(".-jrebirth")
public class BorderPaneTestApplication extends DefaultApplication<Pane> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        return BorderPaneModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String applicationTitle() {
        return "Border Pane Test Application";
    }

}
