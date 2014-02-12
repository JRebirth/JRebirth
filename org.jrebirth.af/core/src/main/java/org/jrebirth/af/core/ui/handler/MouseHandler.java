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
package org.jrebirth.af.core.ui.handler;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import org.jrebirth.af.core.ui.adapter.MouseAdapter;

/**
 * The interface <strong>MouseHandler</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class MouseHandler extends AbstractNamedEventHandler<MouseEvent> {

    /** The Mouse adapter. */
    private final MouseAdapter mouseAdapter;

    /**
     * Default Constructor.
     * 
     * @param mouseAdapter the adapter to use
     */
    public MouseHandler(final MouseAdapter mouseAdapter) {
        super(MouseHandler.class.getSimpleName());
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
