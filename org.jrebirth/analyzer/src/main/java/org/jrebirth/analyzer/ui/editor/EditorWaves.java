package org.jrebirth.analyzer.ui.editor;

import java.io.File;
import java.util.List;

import org.jrebirth.core.event.Event;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveTypeBase;

/**
 * The class <strong>EditorWaves</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface EditorWaves {

    /*************************************************************************/
    /** Wave Types **/
    /*************************************************************************/

    /** Trigger a Unload wave. */
    WaveTypeBase DO_UNLOAD = WaveTypeBase.build("UNLOAD");

    /** Trigger a Play wave. */
    WaveTypeBase DO_PLAY = WaveTypeBase.build("PLAY");

    /** Trigger a Next wave. */
    WaveTypeBase DO_NEXT = WaveTypeBase.build("NEXT");

    /** Trigger a Previous wave. */
    WaveTypeBase DO_PREVIOUS = WaveTypeBase.build("PREVIOUS");

    /** Trigger a Stop wave. */
    WaveTypeBase DO_STOP = WaveTypeBase.build("STOP");

    /** Wave used to display info into the properties view. */
    WaveTypeBase DO_SELECT_EVENT = WaveTypeBase.build("EVENT_SELECTED");

    /** Wave type used to return the event currently processed. */
    WaveTypeBase RE_EVENT_PROCESSED = WaveTypeBase.build("EVENT_PROCESSED");

    /*************************************************************************/
    /** Wave Items **/
    /*************************************************************************/

    /** The file containing all events serialized. */
    WaveItem<File> EVENTS_FILE = WaveItem.build();

    /** The name of the events. */
    WaveItem<List<Event>> EVENTS = WaveItem.build();

    /** An event unserialized. */
    WaveItem<Event> EVENT = WaveItem.build();

}
