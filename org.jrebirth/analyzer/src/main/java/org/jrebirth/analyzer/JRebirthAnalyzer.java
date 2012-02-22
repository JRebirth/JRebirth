package org.jrebirth.analyzer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jrebirth.analyzer.ui.workbench.WorkbenchModel;
import org.jrebirth.core.application.AbstractApplication;
import org.jrebirth.core.ui.Model;

/**
 * The class <strong>JRebirthAnalyzer</strong>.
 * 
 * The application that demonstrate how to use JRebirth Framework. It also allow to show all JRebirth events.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 85 $ $Author: sbordes $
 * @since $Date: 2011-10-19 19:16:15 +0200 (Wed, 19 Oct 2011) $
 */
public final class JRebirthAnalyzer extends AbstractApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(JRebirthAnalyzer.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends Model> getFirstModelClass() {
        return WorkbenchModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "JavaFX 2.0 - JRebirth Event Analyzer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {
        scene.getStylesheets().add("css/analyzer.css");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        // Customize the current stage
    }

}
