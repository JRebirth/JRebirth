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
package org.jrebirth.analyzer.ui.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrebirth.analyzer.command.ProcessEventCommand;
import org.jrebirth.analyzer.service.LoadEdtFileService;
import org.jrebirth.analyzer.ui.editor.ball.BallModel;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;

/**
 * The class <strong>EditorModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class EditorModel extends DefaultModel<EditorModel, EditorView> {

    /** The current event. */
    private int timeFrame = -1;

    /** The map that stored events Nodes . */
    private final Map<Class<?>, BallModel> ballMap = new HashMap<>();

    /** The event list to display. */
    private List<Event> eventList;

    private boolean playing = false;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {

        listen(LoadEdtFileService.RE_EVENTS_LOADED);

        listen(EditorWaves.DO_UNLOAD);
        listen(EditorWaves.DO_PLAY);
        listen(EditorWaves.DO_NEXT);
        listen(EditorWaves.DO_PREVIOUS);
        listen(EditorWaves.DO_STOP);

        listen(EditorWaves.RE_EVENT_PROCESSED);
    }

    /**
     * Call when event are loaded.
     * 
     * @param eventList the lsit of events loaded
     * @param wave the wave received
     */
    public void eventsLoaded(final List<Event> eventList, final Wave wave) {
        this.eventList = eventList;
    }

    /**
     * Unload the event list.
     * 
     * @param wave the wave received
     */
    public void unload(final Wave wave) {
        this.eventList = new ArrayList<>();

        final Collection<BallModel> list = new ArrayList<>(this.ballMap.values());

        for (final BallModel ballModel : list) {
            unregisterBall(ballModel);
        }
    }

    /**
     * Call when event play button is pressed.
     * 
     * @param wave the wave received
     */
    public void play(final Wave wave) {
        if (!this.playing) {
            this.playing = true;
            this.timeFrame = 0;
        }
        if (this.timeFrame < this.eventList.size() - 1) {
            showNext(this.eventList.get(this.timeFrame++));
        } else {
            this.playing = false;
        }
    }

    public void eventProcessed(final Wave wave) {
        if (this.playing) {
            play(wave);
        }
    }

    /**
     * Show next element.
     * 
     * @param event the next event to show
     */
    private void showNext(final Event event) {
        this.timeFrame++;
        callCommand(ProcessEventCommand.class, WaveData.build(EditorWaves.EVENT, event));
    }

    /**
     * Call when event next button is pressed.
     * 
     * @param wave the wave received
     */
    public void next(final Wave wave) {
        if (this.eventList != null && this.timeFrame + 1 < this.eventList.size()) {
            showNext(this.eventList.get(this.timeFrame + 1));
        }

    }

    /**
     * Call when event previous button is pressed.
     * 
     * @param wave the wave received
     */
    public void previous(final Wave wave) {
        if (this.eventList != null && this.timeFrame > 0) {
            hideCurrent(this.eventList.get(this.timeFrame));
        }
    }

    /**
     * Hide the current.
     * 
     * @param event the event to hide
     */
    private void hideCurrent(final Event event) {
        this.timeFrame--;
        getModel(BallModel.class, event).hide();
    }

    /**
     * Call when event stop button is pressed.
     * 
     * @param wave the wave received
     */
    public void stop(final Wave wave) {
        for (int i = this.timeFrame; i >= 0; i--) {
            hideCurrent(this.eventList.get(this.timeFrame));
        }
    }

    /**
     * @return Returns the eventList.
     */
    public List<Event> getEventList() {
        return this.eventList;
    }

    /**
     * Register a ball model.
     * 
     * @param ballModel the ball model to register
     */
    public void registerBall(final BallModel ballModel) {
        this.ballMap.put(ballModel.getEventModel().getTarget(), ballModel);
        getView().getRootNode().getChildren().add(ballModel.getRootNode());
    }

    /**
     * Unregister a ball model.
     * 
     * @param ballModel the ball model to unregister
     */
    public void unregisterBall(final BallModel ballModel) {
        this.ballMap.remove(ballModel.getEventModel().getTarget());
        getView().getRootNode().getChildren().remove(ballModel.getRootNode());
    }

    /**
     * Return the right ball model for the given class.
     * 
     * @param source the class of the source event.
     * @return the right ball model
     */
    public BallModel retrieveBall(final Class<?> source) {
        return this.ballMap.get(source);
    }

}
