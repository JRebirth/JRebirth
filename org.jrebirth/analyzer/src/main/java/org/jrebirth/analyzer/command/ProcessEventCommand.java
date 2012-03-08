package org.jrebirth.analyzer.command;

import org.jrebirth.analyzer.ui.editor.EditorModel;
import org.jrebirth.analyzer.ui.editor.EditorWaveItem;
import org.jrebirth.analyzer.ui.editor.ball.BallModel;
import org.jrebirth.core.command.AbstractUICommand;
import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.link.WaveData;

/**
 * The class <strong>ProcessEventCommand</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class ProcessEventCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Wave wave) {
        super.run(wave);

        final Event event = (Event) wave.get(EditorWaveItem.EVENT).getValue();

        if (event.getEventType().name().startsWith("CREATE")) {
            createBallModel(event);
        } else if (event.getEventType().name().startsWith("ACCESS")) {
            accessBallModel(event);
        } else if (event.getEventType().name().startsWith("DESTROY")) {
            destroyBallModel(event);
        }
    }

    /**
     * Create a ballModel instance.
     * 
     * @param event the create event
     */
    private void createBallModel(final Event event) {
        switch (event.getEventType()) {
            case CREATE_APPLICATION:
            case CREATE_NOTIFIER:
            case CREATE_GLOBAL_FACADE:
            case CREATE_COMMAND_FACADE:
            case CREATE_SERVICE_FACADE:
            case CREATE_UI_FACADE:
            case CREATE_COMMAND:
            case CREATE_SERVICE:
            case CREATE_MODEL:
            case CREATE_VIEW:
            case CREATE_CONTROLLER:
                final BallModel ballModel = getModel(BallModel.class, event);
                ballModel.setEventModel(event); // TODO do it automatically !!! see you track
                getModel(EditorModel.class).register(ballModel);
                callCommand(ShowBallCommand.class, new WaveData(EditorWaveItem.EVENT, event));
                break;
            case CREATE_WAVE:
            default:
        }

    }

    /**
     * Access to a ballModel instance.
     * 
     * @param event the access event
     */
    private void accessBallModel(final Event event) {
        event.toString();
    }

    /**
     * Destroy a ball model.
     * 
     * @param event the destroy event
     */
    private void destroyBallModel(final Event event) {
        switch (event.getEventType()) {
            case DESTROY_COMMAND:
            case DESTROY_SERVICE:
            case DESTROY_MODEL:
            case DESTROY_VIEW:
            case DESTROY_CONTROLLER:
                final BallModel ballModel = getModel(BallModel.class, event);
                callCommand(HideBallCommand.class, new WaveData(EditorWaveItem.EVENT, event));
                getModel(EditorModel.class).unregister(ballModel);
                break;
            case DESTROY_WAVE:
            default:
        }

    }

    /*
     * private void defineAction(final Event event, final BallModel ballModel) { switch (event.getEventType()) { case CREATE_APPLICATION: case CREATE_NOTIFIER: case CREATE_GLOBAL_FACADE: case
     * CREATE_COMMAND_FACADE: case CREATE_SERVICE_FACADE: case CREATE_UI_FACADE: case CREATE_COMMAND: case CREATE_SERVICE: case CREATE_MODEL: case CREATE_VIEW: case CREATE_CONTROLLER: case
     * CREATE_WAVE: ballModel.show(event.getSequence()); break; case ACCESS_COMMAND: case ACCESS_SERVICE: case ACCESS_MODEL: case ACCESS_VIEW: case ACCESS_CONTROLLER: break; case DESTROY_COMMAND: case
     * DESTROY_SERVICE: case DESTROY_MODEL: case DESTROY_VIEW: case DESTROY_CONTROLLER: case DESTROY_WAVE: ballModel.hide(); break; }
     * 
     * }
     */
}
