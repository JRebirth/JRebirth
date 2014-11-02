/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.api.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.ui.annotation.EnumEventType;

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
