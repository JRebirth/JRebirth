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
