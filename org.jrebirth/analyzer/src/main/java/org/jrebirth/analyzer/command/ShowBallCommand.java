package org.jrebirth.analyzer.command;

import org.jrebirth.analyzer.ui.editor.EditorModel;
import org.jrebirth.analyzer.ui.editor.EditorWaveItem;
import org.jrebirth.analyzer.ui.editor.ball.BallModel;
import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.link.Wave;

/**
 * The class <strong>ShowBallCommand</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
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
