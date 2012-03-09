package org.jrebirth.core.event;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The class <strong>EventTracker</strong>.
 * 
 * Used to track all events of JRebirth application to replay them into the analyzer tool.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public class EventTracker extends AbstractRecord {

    /** The list of events tracked. */
    private final List<Event> eventList;

    /**
     * Default Constructor.
     */
    public EventTracker() {
        super();
        // Create an empty list
        this.eventList = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected OutputStream buildOutputStream() {
        // Nothing to do yet
        return null;
    }

    /**
     * Track an event.
     * 
     * @param event the event to record
     */
    public void track(final Event event) {
        event.setSequence(getEventList().size());
        getEventList().add(event);
        record(event.toString());
    }

    /**
     * @return Returns the eventList.
     */
    List<Event> getEventList() {
        return this.eventList;
    }

}
