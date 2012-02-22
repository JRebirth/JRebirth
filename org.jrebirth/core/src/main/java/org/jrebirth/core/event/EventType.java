package org.jrebirth.core.event;

/**
 * The enumeration <strong>EventType</strong>.
 * 
 * Usr to track the type of all JRebirth events
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public enum EventType {

    /** */
    NONE,
    /** */
    CREATE_APPLICATION,
    /** */
    CREATE_NOTIFIER,
    /** */
    CREATE_GLOBAL_FACADE,
    /** */
    CREATE_COMMAND_FACADE,
    /** */
    CREATE_SERVICE_FACADE,
    /** */
    CREATE_UI_FACADE,
    /** */
    CREATE_COMMAND,
    /** */
    CREATE_SERVICE,
    /** */
    CREATE_MODEL,
    /** */
    CREATE_VIEW,
    /** */
    CREATE_CONTROLLER,
    /** */
    CREATE_WAVE,

    /** */
    ACCESS_COMMAND,
    /** */
    ACCESS_SERVICE,
    /** */
    ACCESS_MODEL,
    /** */
    ACCESS_VIEW,
    /** */
    ACCESS_CONTROLLER,

    /** */
    DESTROY_COMMAND,
    /** */
    DESTROY_SERVICE,
    /** */
    DESTROY_MODEL,
    /** */
    DESTROY_VIEW,
    /** */
    DESTROY_CONTROLLER,
    /** */
    DESTROY_WAVE,

}
