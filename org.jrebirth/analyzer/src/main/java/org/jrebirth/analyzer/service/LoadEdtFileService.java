package org.jrebirth.analyzer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.analyzer.ui.editor.EditorWaveItem;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.event.EventBase;
import org.jrebirth.core.service.ServiceImpl;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;

/**
 * The class <strong>LoadEdtFileService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class LoadEdtFileService extends ServiceImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public void returnData(final Wave wave) {
        super.returnData(wave);

        processEventFile((File) wave.get(EditorWaveItem.EVENTS_FILE).getValue());
    }

    /**
     * Parse the event file.
     * 
     * @param selecteFile the event file selected
     */
    public void processEventFile(final File selecteFile) {
        final List<Event> eventList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(selecteFile));)
        {

            String strLine = br.readLine();
            // Read File Line By Line
            while (strLine != null) {
                addEvent(eventList, strLine);
                strLine = br.readLine();
            }

            send(EditorWave.EVENTS_LOADED, new WaveData(EditorWaveItem.EVENTS, eventList));

        } catch (final IOException e) {
            // TODO
            getLocalFacade().getGlobalFacade().getLogger().error("Error while processing event file");
        }

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
