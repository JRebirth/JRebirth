package org.jrebirth.analyzer.ui.controls;

import javafx.scene.input.MouseEvent;

import org.jrebirth.analyzer.command.OpenEventTrackerFileCommand;
import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.adapter.MouseAdapter;
import org.jrebirth.core.ui.impl.DefaultController;

/**
 * The class <strong>ControlsController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class ControlsController extends DefaultController<ControlsModel, ControlsView> implements MouseAdapter {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public ControlsController(final ControlsView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {

        // Register mouse clicked handler
        getView().getOpenButton().setOnMouseClicked(getMouseHandler());
        getView().getPlayPauseButton().setOnMouseClicked(getMouseHandler());
        getView().getForwardButton().setOnMouseClicked(getMouseHandler());
        getView().getBackwardButton().setOnMouseClicked(getMouseHandler());
        getView().getStopButton().setOnMouseClicked(getMouseHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(getView().getOpenButton())) {

            // Call the command synchronously
            getModel().callCommand(OpenEventTrackerFileCommand.class);

        } else if (mouseEvent.getSource().equals(getView().getPlayPauseButton())) {

            getModel().send(EditorWave.PLAY);

        } else if (mouseEvent.getSource().equals(getView().getForwardButton())) {

            getModel().send(EditorWave.NEXT);

        } else if (mouseEvent.getSource().equals(getView().getBackwardButton())) {

            getModel().send(EditorWave.PREVIOUS);

        } else if (mouseEvent.getSource().equals(getView().getStopButton())) {

            getModel().send(EditorWave.STOP);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouse(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragDetected(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEnteredTarget(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExitedTarget(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        // Nothing to do yet
    }

}
