/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.analyzer.command;

import org.jrebirth.analyzer.ui.editor.EditorModel;
import org.jrebirth.analyzer.ui.editor.EditorWaveItem;
import org.jrebirth.analyzer.ui.editor.ball.BallModel;
import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>ShowBallCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ShowBallCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Wave wave) {
        super.run(wave);

        final Event event = (Event) wave.get(EditorWaveItem.EVENT).getValue();

        final EditorModel editorModel = getModel(EditorModel.class);

        final BallModel targetBallModel = getModel(BallModel.class, event);

        if (targetBallModel.getEventModel().getSource() == null) {

            // First Node center it !!!
            targetBallModel.getView().getRootNode().setCenterX(editorModel.getView().getRootNode().getWidth() / 2 - 70);
            targetBallModel.getView().getRootNode().setCenterY(editorModel.getView().getRootNode().getHeight() / 2);

        } else {

            final BallModel sourceBallModel = editorModel.retrieve(targetBallModel.getEventModel().getSource());

            /*
             * System.out.println("Create " + targetBallModel.getEventModel().getTarget().getSimpleName() + " From : " + targetBallModel.getEventModel().getSource().getSimpleName() + " x=" +
             * sourceBallModel.getView().getRootNode().getCenterX() + " trX=" + sourceBallModel.getView().getRootNode().getTranslateX() + " y=" + sourceBallModel.getView().getRootNode().getCenterY() +
             * " trY=" + sourceBallModel.getView().getRootNode().getTranslateY());
             */
            targetBallModel.getView().getRootNode()
                    .setCenterX(sourceBallModel.getView().getRootNode().getCenterX() + sourceBallModel.getView().getRootNode().getTranslateX());
            targetBallModel.getView().getRootNode()
                    .setCenterY(sourceBallModel.getView().getRootNode().getCenterY() + sourceBallModel.getView().getRootNode().getTranslateY());

        }

        targetBallModel.show();
        targetBallModel.getView().prout();
    }

}
