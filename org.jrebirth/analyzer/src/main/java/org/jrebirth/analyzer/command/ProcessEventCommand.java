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

import org.jrebirth.analyzer.ui.editor.EditorWaves;
import org.jrebirth.core.command.CommandListener;
import org.jrebirth.core.command.DefaultCommand;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;

/**
 * The class <strong>ProcessEventCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ProcessEventCommand extends DefaultCommand implements CommandListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();
        addCommandListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Wave wave) {

        final Event event = wave.get(EditorWaves.EVENT);

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
                // final BallModel ballModel = getModel(BallModel.class, event);
                callCommand(ShowBallCommand.class, WaveData.build(EditorWaves.EVENT, event));
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
                // final BallModel ballModel = getModel(BallModel.class, event);
                callCommand(HideBallCommand.class, WaveData.build(EditorWaves.EVENT, event));
                break;
            case DESTROY_WAVE:
            default:
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commandAchieved(final Wave wave) {
        send(EditorWaves.RE_EVENT_PROCESSED/* , WaveData.build(waveItem, value) */);

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
