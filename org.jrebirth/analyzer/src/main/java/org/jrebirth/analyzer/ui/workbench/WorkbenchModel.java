package org.jrebirth.analyzer.ui.workbench;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.analyzer.ui.editor.EditorWaveItem;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.event.EventImpl;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.link.WaveData;
import org.jrebirth.core.ui.AbstractModel;

/**
 * The class <strong>WorkbenchModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 55 $ $Author: sbordes $
 * @since $Date: 2011-10-14 19:23:59 +0200 (Fri, 14 Oct 2011) $
 */
public final class WorkbenchModel extends AbstractModel<WorkbenchModel, WorkbenchView> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {

        // Do stuff on the model !
        getInnerModel(WorkbenchInnerModels.CONTROLS);
        getInnerModel(WorkbenchInnerModels.PROPERTIES);

        // MOCK
        final List<Event> eventList = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("load.etd"));
            String strLine = br.readLine();
            // Read File Line By Line
            while (strLine != null) {
                addEvent(eventList, strLine);
                strLine = br.readLine();
            }
        } catch (final IOException e) {

            getLocalFacade().getGlobalFacade().getLogger().error("Failed to lad the archived event file");
        }

        send(EditorWave.EVENTS_LOADED, new WaveData(EditorWaveItem.EVENTS, eventList));

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet
    }

}
