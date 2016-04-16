/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
import javafx.scene.input.DragEvent;

/**
 * The Drag event type.<br />
 * The Drag type will be appended to method name to use.
 */
public enum Drag implements EnumEventType {

    /** Any Drag Event. */
    Any(DragEvent.ANY),

    /** Drag done event. */
    Done(DragEvent.DRAG_DONE),

    /** Drag dropped event. */
    Dropped(DragEvent.DRAG_DROPPED),

    /** Drag entered event. */
    Entered(DragEvent.DRAG_ENTERED),

    /** Drag entered target event. */
    EnteredTarget(DragEvent.DRAG_ENTERED_TARGET),

    /** Drag exited event. */
    Exited(DragEvent.DRAG_EXITED),

    /** Drag exited target event. */
    ExitedTarget(DragEvent.DRAG_EXITED_TARGET),

    /** Drag over event. */
    Over(DragEvent.DRAG_OVER);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Drag(final EventType<?> eventType) {
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
