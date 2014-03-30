package org.jrebirth.af.component.workbench;

import javafx.application.Application;
import javafx.scene.layout.StackPane;

import org.jrebirth.af.component.ui.workbench.WorkbenchModel;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.core.ui.Model;

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
        Application.launch(WorkbenchApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return WorkbenchModel.class;
    }

}
