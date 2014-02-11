/*
 * 
 */
package org.jrebirth.core.log;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * The class <strong>JRebirthMarkers</strong>.
 * 
 * Only 5 characters per marker name.
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthMarkers {

    /** The default marker. */
    Marker EMPTY = MarkerFactory.getMarker("EMPTY");

    /** The Marker used by Application package. */
    Marker APPLICATION = MarkerFactory.getMarker("APPLI");

    /** The Marker used by Message Engine. */
    Marker MESSAGE = MarkerFactory.getMarker("MESSG");

    /** The Marker used by Parameter Engine. */
    Marker PARAMETER = MarkerFactory.getMarker("PARAM");

    /** The Marker used by JRebirth EventParameter Engine. */
    Marker JREVENT = MarkerFactory.getMarker("JREVT");

    /** The Marker used by JRebirth Concurrent layer. */
    Marker CONCURRENT = MarkerFactory.getMarker("CNCRT");

    /** The Marker used by JRebirth Facade layer. */
    Marker FACADE = MarkerFactory.getMarker("FACAD");

    /** The Marker used by JRebirth Link layer. */
    Marker LINK = MarkerFactory.getMarker("LINK");

    /** The Marker used by JRebirth Key layer. */
    Marker KEY = MarkerFactory.getMarker("KEY  ");

    /** The Marker used by JRebirth Util layer. */
    Marker UTIL = MarkerFactory.getMarker("UTIL");

    /** The Marker used to report Wave related message. */
    Marker WAVE = MarkerFactory.getMarker("WAVE ");

    /** The Marker used by JRebirth Service layer. */
    Marker SERVICE = MarkerFactory.getMarker("SERVC");

    /** The Marker used by JRebirth Util layer. */
    Marker UI = MarkerFactory.getMarker("UI   ");

    /** The Marker used by JRebirth UI.FXML layer. */
    Marker FXML = MarkerFactory.getMarker("FXML ");

    /** The Marker used by JRebirth Exception.Handler layer. */
    Marker UNCAUGHT = MarkerFactory.getMarker("UNCAU");

}
