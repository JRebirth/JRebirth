package org.jrebirth.core.ui.handler;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.WindowEvent;

import org.jrebirth.core.ui.adapter.WindowAdapter;

/**
 * The interface <strong>WindowHandler</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class WindowHandler implements EventHandler<WindowEvent> {

    /** The Window adapter. */
    private final WindowAdapter windowAdapter;

    /**
     * Default Constructor.
     * 
     * @param windowAdapter the adapter to use
     */
    public WindowHandler(final WindowAdapter windowAdapter) {
        this.windowAdapter = windowAdapter;
    }

    /**
     * Return the implementation of the window adapter interface.
     * 
     * @return Returns the WindowAdapter.
     */
    public WindowAdapter getWindowAdapter() {
        return this.windowAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final WindowEvent windowEvent) {

        final EventType<?> type = windowEvent.getEventType();

        if (WindowEvent.WINDOW_CLOSE_REQUEST == type) {
            getWindowAdapter().windowCloseRequest(windowEvent);
        } else if (WindowEvent.WINDOW_HIDDEN == type) {
            getWindowAdapter().windowHidden(windowEvent);
        } else if (WindowEvent.WINDOW_HIDING == type) {
            getWindowAdapter().windowHiding(windowEvent);
        } else if (WindowEvent.WINDOW_SHOWING == type) {
            getWindowAdapter().windowShowing(windowEvent);
        } else if (WindowEvent.WINDOW_SHOWN == type) {
            getWindowAdapter().windowShown(windowEvent);
        } else {
            getWindowAdapter().window(windowEvent);
        }

    }
}
