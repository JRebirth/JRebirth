package org.jrebirth.af.core.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import org.jrebirth.af.core.ui.annotation.EnumEventType;

/**
 * The Mouse event type.<br />
 * The mouse type will be appended to method name to use.
 */
public enum Mouse implements EnumEventType {

    /** Any Rotate Event. */
    Any(MouseEvent.ANY),

    /** Mouse drag detected event. */
    DragDetected(MouseEvent.DRAG_DETECTED),

    /** Mouse clicked event. */
    Clicked(MouseEvent.MOUSE_CLICKED),

    /** Mouse dragged event. */
    Dragged(MouseEvent.MOUSE_DRAGGED),

    /** Mouse entered event. */
    Entered(MouseEvent.MOUSE_ENTERED),

    /** Mouse entered target event. */
    EnteredTarget(MouseEvent.MOUSE_ENTERED_TARGET),

    /** Mouse exited event. */
    Exited(MouseEvent.MOUSE_EXITED),

    /** Mouse exited target event. */
    ExitedTarget(MouseEvent.MOUSE_EXITED_TARGET),

    /** Mouse moved event. */
    Moved(MouseEvent.MOUSE_MOVED),

    /** Mouse pressed event. */
    Pressed(MouseEvent.MOUSE_PRESSED),

    /** Mouse released event. */
    Released(MouseEvent.MOUSE_RELEASED);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Mouse(final EventType<?> eventType) {
        this.eventType = eventType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventType<?> eventType() {
        return this.eventType;
    }

}