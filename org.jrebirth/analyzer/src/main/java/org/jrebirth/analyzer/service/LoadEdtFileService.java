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

/**
 * The class <strong>LoadEdtFileService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class LoadEdtFileService extends ServiceBase {

    // public enum Services implements WaveType {
    // LOAD_EVENTS,
    // EVENTS_LOADED;
    // }

    /** . */
    public static WaveTypeBase DO_LOAD_EVENTS = new WaveTypeBase("LOAD_EVENTS", EditorWaves.EVENTS_FILE);

    /** . */
    public static WaveTypeBase RE_EVENTS_LOADED = new WaveTypeBase("EVENTS_LOADED", EditorWaves.EVENTS);

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

            return eventList;
            // send(EditorWave.EVENTS_LOADED, new WaveData(EditorWaveItem.EVENTS, eventList));

        } catch (final IOException e) {
            getLocalFacade().getGlobalFacade().getLogger().error("Error while processing event file");
        }
        return new ArrayList<>();

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
