package org.jrebirth.analyzer.ui.properties;

import org.jrebirth.core.event.Event;
import org.jrebirth.core.wave.WaveItem;

/**
 * The class <strong>EditorWaves</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface PropertiesWaves {

    /** The event object. */
    WaveItem<Event> EVENT_OBJECT = new WaveItem<>();

    /** The type of the node. */
    WaveItem<Event> NODE_TYPE = new WaveItem<>();

}
