package org.jrebirth.analyzer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.analyzer.ui.editor.EditorWaves;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.event.EventBase;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
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

        registerService(DO_LOAD_EVENTS, RE_EVENTS_LOADED, EditorWaves.EVENTS);
    }

    /**
     * Parse the event file.
     * 
     * @param selecteFile the event file selected
     * 
     * @return the list of loaded events
     */
    public List<Event> loadEvents(final File selecteFile) {
        final List<Event> eventList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(selecteFile));)
        {

            String strLine = br.readLine();
            // Read File Line By Line
            while (strLine != null) {
                addEvent(eventList, strLine);
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
