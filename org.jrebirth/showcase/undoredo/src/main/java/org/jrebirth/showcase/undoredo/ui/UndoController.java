package org.jrebirth.showcase.undoredo.ui;

import javafx.scene.input.MouseEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.showcase.undoredo.beans.ShapeType;
import org.jrebirth.showcase.undoredo.beans.UndoAppWaves;
import org.jrebirth.showcase.undoredo.command.CreateShapeCommand;
import org.jrebirth.undo.command.RedoCommand;
import org.jrebirth.undo.command.UndoCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleController</strong>.
 * 
 * @author
 */
public final class UndoController extends DefaultController<UndoModel, UndoView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UndoController.class);

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public UndoController(final UndoView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        // Manage Ui Command Button
        linkCommand(getView().getUndoButton(), MouseEvent.MOUSE_CLICKED, UndoCommand.class, UndoModel.stackName);
        linkCommand(getView().getRedoButton(), MouseEvent.MOUSE_CLICKED, RedoCommand.class, UndoModel.stackName);

        linkCommand(getView().getAddCircleButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.shapeType, ShapeType.Circle), UndoModel.stackName);
        linkCommand(getView().getAddSquareButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.shapeType, ShapeType.Square), UndoModel.stackName);
        linkCommand(getView().getAddRectangleButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.shapeType, ShapeType.Rectangle), UndoModel.stackName);

    }

}