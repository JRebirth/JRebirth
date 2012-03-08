package org.jrebirth.analyzer.ui.editor;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;

/**
 * 
 * The class <strong>EditorView</strong>.
 * 
 * The view used to display nodes.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class EditorView extends DefaultView<EditorModel, Pane, EditorController> {

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public EditorView(final EditorModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        getRootNode().setPrefSize(600, 500);

        getRootNode().getStyleClass().add("editor");

        final Circle c = new Circle(30, Color.BEIGE);
        c.setCenterX(100);
        c.setCenterY(10);

    }

}
