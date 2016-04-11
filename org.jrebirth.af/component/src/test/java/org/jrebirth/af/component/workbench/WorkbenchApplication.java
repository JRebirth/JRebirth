package org.jrebirth.af.component.workbench;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.resources.ComponentStyles;
import org.jrebirth.af.component.workbench.ui.TabDemoModel;
import org.jrebirth.af.core.application.DefaultApplication;

/**
 * The class <strong>WorkbenchApplication</strong>.
 *
 * @author
 */
public final class WorkbenchApplication extends DefaultApplication<StackPane> {

    /**
     * Application launcher.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        launch(WorkbenchApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        return TabDemoModel.class;
    }

    @Override
    protected void customizeScene(final Scene scene) {
        super.customizeScene(scene);

        addCSS(scene, ComponentStyles.DEFAULT);
    }

}
