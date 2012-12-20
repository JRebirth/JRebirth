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
package org.jrebirth.analyzer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.analyzer.ui.editor.EditorWaves;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.Event;
import org.jrebirth.core.facade.EventBase;
import org.jrebirth.core.facade.GlobalFacadeBase;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveTypeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>LoadEdtFileService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class LoadEdtFileService extends ServiceBase {

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_LOAD_EVENTS = WaveTypeBase.build("LOAD_EVENTS", EditorWaves.EVENTS_FILE);

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_EVENTS_LOADED = WaveTypeBase.build("EVENTS_LOADED", EditorWaves.EVENTS);

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadEdtFileService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        registerCallback(DO_LOAD_EVENTS, RE_EVENTS_LOADED/* , EditorWaves.EVENTS */);
    }

    /**
     * Parse the event file.
     * 
     * @param selecteFile the event file selected
     * @param wave the wave that trigger the action
     * 
     * @return the list of loaded events
     */
    public List<Event> loadEvents(final File selecteFile, final Wave wave) {
        final List<Event> eventList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(selecteFile));)
        {

            String strLine = br.readLine();
            // Read File Line By Line
            while (strLine != null) {
                if (strLine.contains(GlobalFacadeBase.EVENT_TRACKED)) {
                    addEvent(eventList, strLine.substring(strLine.indexOf(GlobalFacadeBase.EVENT_TRACKED) + GlobalFacadeBase.EVENT_TRACKED.length()));
                }
                strLine = br.readLine();
            }

        } catch (final IOException e) {
            LOGGER.error("Error while processing event file", e);
        }
        return eventList;

    }

    /**
     * Add an event to the event list.
     * 
     * @param eventList the list of events
     * @param strLine the string to use
     */
    private void addEvent(final List<Event> eventList, final String strLine) {
        eventList.add(new EventBase(strLine));
    }
}
