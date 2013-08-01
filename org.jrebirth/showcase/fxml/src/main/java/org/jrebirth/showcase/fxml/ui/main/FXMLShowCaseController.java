package org.jrebirth.showcase.fxml.ui.main;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleController</strong>.
 * 
 * @author
 */
public final class FXMLShowCaseController extends DefaultController<FXMLShowCaseModel, FXMLShowCaseView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLShowCaseController.class);

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public FXMLShowCaseController(final FXMLShowCaseView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        // Manage Ui Command Button
        // linkCommand(getView().getUndoButton(), MouseEvent.MOUSE_CLICKED, UndoCommand.class, FXMLShowCaseModel.stackName);
        // linkCommand(getView().getRedoButton(), MouseEvent.MOUSE_CLICKED, RedoCommand.class, FXMLShowCaseModel.stackName);
        //
        // linkCommand(getView().getAddCircleButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.shapeType, ShapeType.Circle), FXMLShowCaseModel.stackName);
        // linkCommand(getView().getAddSquareButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.shapeType, ShapeType.Square), FXMLShowCaseModel.stackName);
        // linkCommand(getView().getAddRectangleButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.shapeType, ShapeType.Rectangle), FXMLShowCaseModel.stackName);

    }

}