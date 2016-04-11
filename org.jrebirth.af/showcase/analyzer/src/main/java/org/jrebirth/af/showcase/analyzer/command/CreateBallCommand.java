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
package org.jrebirth.af.showcase.analyzer.command;

import org.jrebirth.af.api.facade.JRebirthEvent;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUICommand;
import org.jrebirth.af.showcase.analyzer.ui.editor.EditorModel;
import org.jrebirth.af.showcase.analyzer.ui.editor.EditorWaves;
import org.jrebirth.af.showcase.analyzer.ui.editor.ball.BallModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ShowBallCommand</strong>.
 *
 * @author Sébastien Bordes
 */
public final class CreateBallCommand extends DefaultUICommand {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBallCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Wave wave) {

        final JRebirthEvent event = wave.get(EditorWaves.EVENT);

        LOGGER.trace("Process " + event.eventType() + " of type " + event.target());

        final EditorModel editorModel = getModel(EditorModel.class);

        final BallModel targetBallModel = getModel(BallModel.class, event);

        editorModel.registerBall(targetBallModel);

        if (editorModel.retrieveBall(targetBallModel.getEventModel().source()) == null) {

            // it's the application node, we shall center it !!! minus 70 because it's the globalFacade node that resides in the center
            targetBallModel.view().node().layoutXProperty().bind(editorModel.view().node().widthProperty().divide(2).subtract(70).subtract(24));
            targetBallModel.view().node().layoutYProperty().bind(editorModel.view().node().heightProperty().divide(2).subtract(24));

        } else {

            final BallModel sourceBallModel = editorModel.retrieveBall(targetBallModel.getEventModel().source());

            // All other nodes shall be positionned relatively to their parent
            /*
             * System.out.println("Create " + targetBallModel.getEventModel().getTarget().getSimpleName() + " From : " + targetBallModel.getEventModel().getSource().getSimpleName() + " x=" +
             * sourceBallModel.getView().getRootNode().getCenterX() + " trX=" + sourceBallModel.getView().getRootNode().getTranslateX() + " y=" + sourceBallModel.getView().getRootNode().getCenterY() +
             * " trY=" + sourceBallModel.getView().getRootNode().getTranslateY());
             */
            targetBallModel.view().node()
                           .layoutXProperty().bind(sourceBallModel.view().node().layoutXProperty().add(sourceBallModel.view().node().translateXProperty()));
            targetBallModel.view().node()
                           .layoutYProperty().bind(sourceBallModel.view().node().layoutYProperty().add(sourceBallModel.view().node().translateYProperty()));

        }

        targetBallModel.show();
    }
}
