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
package org.jrebirth.af.showcase.analyzer.service;

import java.io.File;
import java.util.List;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.concurrent.Priority;
import org.jrebirth.af.api.facade.JRebirthEvent;
import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.showcase.analyzer.ui.editor.EditorWaves;

/**
 * The class <strong>LoadEdtFileService</strong>.
 *
 * @author Sébastien Bordes
 */
@RegistrationPoint(exclusive = true)
public interface LoadEdtFileService extends Service {

    /** Wave type use to load events. */
    WaveType DO_LOAD_EVENTS = Builders.waveType("LOAD_EVENTS")
                                      .items(EditorWaves.EVENTS_FILE)
                                      .returnAction("EVENTS_LOADED")
                                      .returnItem(EditorWaves.EVENTS);

    /**
     * Parse the event file.
     *
     * @param selecteFile the event file selected
     * @param wave the wave that trigger the action
     *
     * @return the list of loaded events
     */
    @Priority(PriorityLevel.High)
    List<JRebirthEvent> doLoadEvents(final File selecteFile, final Wave wave);

}
