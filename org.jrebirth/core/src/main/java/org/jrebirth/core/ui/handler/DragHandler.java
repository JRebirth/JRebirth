/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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
package org.jrebirth.core.ui.handler;

import javafx.event.EventType;
import javafx.scene.input.DragEvent;

import org.jrebirth.core.ui.adapter.DragAdapter;

/**
 * The interface <strong>DragHandler</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class DragHandler extends AbstractNamedEventHandler<DragEvent> {

    /** The Drag adapter. */
    private final DragAdapter dragAdapter;

    /**
     * Default Constructor.
     * 
     * @param dragAdapter the adapter to use
     */
    public DragHandler(final DragAdapter dragAdapter) {
        super(DragHandler.class.getSimpleName());
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
