package org.jrebirth.showcase.undoredo;

import javafx.application.Application;
import javafx.scene.layout.StackPane;

import org.jrebirth.core.application.DefaultApplication;
import org.jrebirth.core.ui.Model;
import org.jrebirth.showcase.undoredo.ui.UndoModel;

/**
 * The class <strong>UndoApplication</strong>.
 * 
 * @author
 */
public final class UndoRedoApplication extends DefaultApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(UndoRedoApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return UndoModel.class;
    }

}
