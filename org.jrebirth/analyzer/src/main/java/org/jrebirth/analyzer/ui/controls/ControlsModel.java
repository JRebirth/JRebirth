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
package org.jrebirth.analyzer.ui.controls;

import java.util.List;

import org.jrebirth.analyzer.service.LoadEdtFileService;
import org.jrebirth.analyzer.ui.editor.EditorWaves;
import org.jrebirth.core.facade.JRebirthEvent;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>ControlsModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ControlsModel extends DefaultModel<ControlsModel, ControlsView> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        listen(LoadEdtFileService.RE_EVENTS_LOADED);
        listen(EditorWaves.DO_UNLOAD);
    }

    /**
     * Call when event are loaded.
     * 
     * @param eventList the list of events loaded
     * 
     * @param wave the wave received
     */
    public void eventsLoaded(final List<JRebirthEvent> eventList, final Wave wave) {
        getView().activateButtons(!eventList.isEmpty());
    }

    /**
     * Call when event are loaded.
     * 
     * @param wave the wave received
     */
    public void unload(final Wave wave) {
        getView().activateButtons(false);
    }
}
