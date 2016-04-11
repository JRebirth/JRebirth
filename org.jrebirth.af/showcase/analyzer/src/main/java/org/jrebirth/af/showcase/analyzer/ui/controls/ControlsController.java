/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.showcase.analyzer.ui.controls;

import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.ui.LinkedCallback;
import org.jrebirth.af.showcase.analyzer.command.OpenEventTrackerFileCommand;
import org.jrebirth.af.showcase.analyzer.ui.editor.EditorWaves;

/**
 * The class <strong>ControlsController</strong>.
 *
 * @author Sébastien Bordes
 */
public final class ControlsController extends DefaultController<ControlsModel, ControlsView> {

    /* implements MouseAdapter */

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
    protected void initEventHandlers() throws CoreException {

        linkCommand(view().getOpenButton(), MouseEvent.MOUSE_CLICKED, OpenEventTrackerFileCommand.class, LinkedCallback.CHECK_MOUSE_SINGLE_CLICK);

        linkWave(view().getUnloadButton(), MouseEvent.MOUSE_CLICKED, EditorWaves.DO_UNLOAD);
        linkWave(view().getPlayPauseButton(), MouseEvent.MOUSE_CLICKED, EditorWaves.DO_PLAY);
        linkWave(view().getBackwardButton(), MouseEvent.MOUSE_CLICKED, EditorWaves.DO_PREVIOUS);
        linkWave(view().getForwardButton(), MouseEvent.MOUSE_CLICKED, EditorWaves.DO_NEXT);
        linkWave(view().getStopButton(), MouseEvent.MOUSE_CLICKED, EditorWaves.DO_STOP);

        // Register mouse clicked handler
        // getView().getOpenButton().setOnMouseClicked(getMouseHandler());
        // getView().getUnloadButton().setOnMouseClicked(getMouseHandler());
        // getView().getPlayPauseButton().setOnMouseClicked(getMouseHandler());
        // getView().getForwardButton().setOnMouseClicked(getMouseHandler());
        // getView().getBackwardButton().setOnMouseClicked(getMouseHandler());
        // getView().getStopButton().setOnMouseClicked(getMouseHandler());
    }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseClicked(final MouseEvent mouseEvent) {
    // if (mouseEvent.getSource().equals(getView().getOpenButton())) {
    //
    // // Call the command synchronously
    // getModel().callCommand(OpenEventTrackerFileCommand.class);
    //
    // } else if (mouseEvent.getSource().equals(getView().getUnloadButton())) {
    //
    // getModel().send(EditorWaves.DO_UNLOAD);
    //
    // } else if (mouseEvent.getSource().equals(getView().getPlayPauseButton())) {
    //
    // getModel().send(EditorWaves.DO_PLAY);
    //
    // } else if (mouseEvent.getSource().equals(getView().getForwardButton())) {
    //
    // getModel().send(EditorWaves.DO_NEXT);
    //
    // } else if (mouseEvent.getSource().equals(getView().getBackwardButton())) {
    //
    // getModel().send(EditorWaves.DO_PREVIOUS);
    //
    // } else if (mouseEvent.getSource().equals(getView().getStopButton())) {
    //
    // getModel().send(EditorWaves.DO_STOP);
    //
    // }
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouse(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseDragDetected(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseDragged(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseEntered(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseEnteredTarget(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseExited(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseExitedTarget(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseMoved(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mousePressed(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseReleased(final MouseEvent mouseEvent) {
    // // Nothing to do yet
    // }

}
