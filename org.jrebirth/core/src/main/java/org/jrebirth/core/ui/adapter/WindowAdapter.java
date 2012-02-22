package org.jrebirth.core.ui.adapter;

import javafx.stage.WindowEvent;

/**
 * The class <strong>WindowAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface WindowAdapter {

    /**
     * Manage window ANY events.
     * 
     * Common supertype for all window event types.
     * 
     * @param windowEvent the event to manage
     */
    void window(WindowEvent windowEvent);

    /**
     * Manage window close request events.
     * 
     * This event is delivered to a window when there is an external request to close that window.
     * 
     * @param windowEvent the event to manage
     */
    void windowCloseRequest(WindowEvent windowEvent);

    /**
     * Manage window hidden events.
     * 
     * This event occurs on window just after it is hidden.
     * 
     * @param windowEvent the event to manage
     */
    void windowHidden(WindowEvent windowEvent);

    /**
     * Manage window hiding events.
     * 
     * This event occurs on window just before it is hidden.
     * 
     * @param windowEvent the event to manage
     */
    void windowHiding(WindowEvent windowEvent);

    /**
     * Manage window showing events.
     * 
     * This event occurs on window just before it is shown.
     * 
     * @param windowEvent the event to manage
     */
    void windowShowing(WindowEvent windowEvent);

    /**
     * Manage window shown events.
     * 
     * This event occurs on window just after it is shown.
     * 
     * @param windowEvent the event to manage
     */
    void windowShown(WindowEvent windowEvent);

}
