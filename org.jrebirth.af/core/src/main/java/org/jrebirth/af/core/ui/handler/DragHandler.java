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
package org.jrebirth.af.core.ui.handler;

import javafx.event.EventType;
import javafx.scene.input.DragEvent;

import org.jrebirth.af.core.ui.adapter.DragAdapter;

/**
 * The interface <strong>DragHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public final class DragHandler extends AbstractNamedEventHandler<DragEvent, DragAdapter> {

    /**
     * Default Constructor.
     *
     * @param dragAdapter the adapter to use
     */
    public DragHandler(final DragAdapter dragAdapter) {
        super(DragHandler.class.getSimpleName(), dragAdapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final DragEvent dragEvent) {

        final EventType<?> type = dragEvent.getEventType();

        if (DragEvent.DRAG_DONE == type) {
            adapter().dragDone(dragEvent);
        } else if (DragEvent.DRAG_DROPPED == type) {
            adapter().dragDropped(dragEvent);
        } else if (DragEvent.DRAG_ENTERED == type) {
            adapter().dragEntered(dragEvent);
        } else if (DragEvent.DRAG_ENTERED_TARGET == type) {
            adapter().dragEnteredTarget(dragEvent);
        } else if (DragEvent.DRAG_EXITED == type) {
            adapter().dragExited(dragEvent);
        } else if (DragEvent.DRAG_EXITED_TARGET == type) {
            adapter().dragExitedTarget(dragEvent);
        } else if (DragEvent.DRAG_OVER == type) {
            adapter().dragOver(dragEvent);
        } else {
            adapter().drag(dragEvent);
        }

    }
}
