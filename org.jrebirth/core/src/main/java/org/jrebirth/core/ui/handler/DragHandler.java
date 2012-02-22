package org.jrebirth.core.ui.handler;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.DragEvent;

import org.jrebirth.core.ui.adapter.DragAdapter;

/**
 * The interface <strong>MouseHandler</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class DragHandler implements EventHandler<DragEvent> {

    /** The Drag adapter. */
    private final DragAdapter dragAdapter;

    /**
     * Default Constructor.
     * 
     * @param dragAdapter the adapter to use
     */
    public DragHandler(final DragAdapter dragAdapter) {
        this.dragAdapter = dragAdapter;
    }

    /**
     * Return the implementation of the drag adapter interface.
     * 
     * @return Returns the mouseAdapter.
     */
    public DragAdapter getDragAdapter() {
        return this.dragAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final DragEvent dragEvent) {

        final EventType<?> type = dragEvent.getEventType();

        if (DragEvent.DRAG_DONE == type) {
            getDragAdapter().dragDone(dragEvent);
        } else if (DragEvent.DRAG_DROPPED == type) {
            getDragAdapter().dragDropped(dragEvent);
        } else if (DragEvent.DRAG_ENTERED == type) {
            getDragAdapter().dragEntered(dragEvent);
        } else if (DragEvent.DRAG_ENTERED_TARGET == type) {
            getDragAdapter().dragEnteredTarget(dragEvent);
        } else if (DragEvent.DRAG_EXITED == type) {
            getDragAdapter().dragExited(dragEvent);
        } else if (DragEvent.DRAG_EXITED_TARGET == type) {
            getDragAdapter().dragExitedTarget(dragEvent);
        } else if (DragEvent.DRAG_OVER == type) {
            getDragAdapter().dragOver(dragEvent);
        } else {
            getDragAdapter().drag(dragEvent);
        }

    }
}
