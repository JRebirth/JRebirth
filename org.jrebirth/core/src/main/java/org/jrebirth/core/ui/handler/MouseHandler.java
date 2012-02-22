package org.jrebirth.core.ui.handler;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import org.jrebirth.core.ui.adapter.MouseAdapter;

/**
 * The interface <strong>MouseHandler</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class MouseHandler implements EventHandler<MouseEvent> {

    /** The Mouse adapter. */
    private final MouseAdapter mouseAdapter;

    /**
     * Default Constructor.
     * 
     * @param mouseAdapter the adapter to use
     */
    public MouseHandler(final MouseAdapter mouseAdapter) {
        this.mouseAdapter = mouseAdapter;
    }

    /**
     * Return the implementation of the mouse adapter interface.
     * 
     * @return Returns the mouseAdapter.
     */
    public MouseAdapter getMouseAdapter() {
        return this.mouseAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final MouseEvent mouseEvent) {

        final EventType<?> type = mouseEvent.getEventType();

        if (MouseEvent.DRAG_DETECTED == type) {
            getMouseAdapter().mouseDragDetected(mouseEvent);
        } else if (MouseEvent.MOUSE_CLICKED == type) {
            getMouseAdapter().mouseClicked(mouseEvent);
        } else if (MouseEvent.MOUSE_DRAGGED == type) {
            getMouseAdapter().mouseDragged(mouseEvent);
        } else if (MouseEvent.MOUSE_ENTERED == type) {
            getMouseAdapter().mouseEntered(mouseEvent);
        } else if (MouseEvent.MOUSE_ENTERED_TARGET == type) {
            getMouseAdapter().mouseEnteredTarget(mouseEvent);
        } else if (MouseEvent.MOUSE_EXITED == type) {
            getMouseAdapter().mouseExited(mouseEvent);
        } else if (MouseEvent.MOUSE_EXITED_TARGET == type) {
            getMouseAdapter().mouseExitedTarget(mouseEvent);
        } else if (MouseEvent.MOUSE_MOVED == type) {
            getMouseAdapter().mouseMoved(mouseEvent);
        } else if (MouseEvent.MOUSE_PRESSED == type) {
            getMouseAdapter().mousePressed(mouseEvent);
        } else if (MouseEvent.MOUSE_RELEASED == type) {
            getMouseAdapter().mouseReleased(mouseEvent);
        } else {
            getMouseAdapter().mouse(mouseEvent);
        }

    }
}
