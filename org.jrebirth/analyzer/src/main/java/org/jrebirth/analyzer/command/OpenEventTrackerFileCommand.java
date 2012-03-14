package org.jrebirth.analyzer.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;

import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.analyzer.ui.editor.EditorWaveItem;
import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.event.EventImpl;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.link.WaveData;

/**
 * The class <strong>OpenEventTrackerFileCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class OpenEventTrackerFileCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Wave wave) {
        super.run(wave);

        final FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("(etd)", "*.etd")); // to filter
                                                                                         // only xml
                                                                                         // files
        final File selected = fc.showOpenDialog(null);

        if (selected != null) { // if nothing is selected
            // Process the file
            processEventFile(selected);
        }
    }

    /**
     * Parse the event file.
     * 
     * @param selecteFile the event file selected
     */
    private void processEventFile(final File selecteFile) {
        final List<Event> eventList = new ArrayList<>();

        try {
            final BufferedReader br = new BufferedReader(new FileReader(selecteFile));

            String strLine = br.readLine();
            // Read File Line By Line
            while (strLine != null) {
                addEvent(eventList, strLine);
                strLine = br.readLine();
            }

            send(EditorWave.EVENTS_LOADED, new WaveData(EditorWaveItem.EVENTS, eventList));

        } catch (final IOException e) {
            // TODO
            getLocalFacade().getGlobalFacade().getLogger().error("Erro while processing event file");
        }

    }

    /**
     * Add an event to the event list.
     * 
     * @param eventList the list of events
     * @param strLine the string to use
     */
    private void addEvent(final List<Event> eventList, final String strLine) {
        eventList.add(new EventImpl(strLine));
    }
}
