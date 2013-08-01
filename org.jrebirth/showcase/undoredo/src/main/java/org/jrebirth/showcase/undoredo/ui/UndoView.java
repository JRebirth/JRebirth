package org.jrebirth.showcase.undoredo.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleView</strong>.
 * 
 * @author
 */
public final class UndoView extends AbstractView<UndoModel, BorderPane, UndoController> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UndoView.class);

    private Button undoButton;

    private Button redoButton;

    private Button addCircleButton;

    private Button addSquareButton;

    private Button addRectangleButton;

    private Pane editor;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public UndoView(final UndoModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        this.undoButton = new Button("Undo");
        this.redoButton = new Button("Redo");

        this.addCircleButton = new Button("Add Circle");
        this.addSquareButton = new Button("Add Square");
        this.addRectangleButton = new Button("Add Rectangle");

        getRootNode().setTop(FlowPaneBuilder.create()
                .children(undoButton, redoButton, addCircleButton, addSquareButton, addRectangleButton)
                .build());

        this.editor = PaneBuilder.create().style("-fx-background-color:beige").build();

        getRootNode().setCenter(editor);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        LOGGER.debug("Start the Sample View");
        // Custom code to process when the view is displayed the first time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        LOGGER.debug("Reload the Sample View");
        // Custom code to process when the view is displayed the 1+n time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        LOGGER.debug("Hide the Sample View");
        // Custom code to process when the view is hidden
    }

    /**
     * @return Returns the undoButton.
     */
    Button getUndoButton() {
        return undoButton;
    }

    /**
     * @return Returns the redoButton.
     */
    Button getRedoButton() {
        return redoButton;
    }

    /**
     * @return Returns the editor.
     */
    Pane getEditor() {
        return editor;
    }

    /**
     * @return Returns the addCircleButton.
     */
    Button getAddCircleButton() {
        return addCircleButton;
    }

    /**
     * @return Returns the addSquareButton.
     */
    Button getAddSquareButton() {
        return addSquareButton;
    }

    /**
     * @return Returns the addRectangleButton.
     */
    Button getAddRectangleButton() {
        return addRectangleButton;
    }

}