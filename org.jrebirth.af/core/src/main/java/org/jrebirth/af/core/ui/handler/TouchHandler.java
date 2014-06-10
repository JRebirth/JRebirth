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
import javafx.scene.input.TouchEvent;

import org.jrebirth.af.core.ui.adapter.TouchAdapter;

/**
 * The interface <strong>TouchHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public final class TouchHandler extends AbstractNamedEventHandler<TouchEvent> {

    /** The Touch adapter. */
    private final TouchAdapter touchAdapter;

    /**
     * Default Constructor.
     *
     * @param touchAdapter the adapter to use
     */
    public TouchHandler(final TouchAdapter touchAdapter) {
        super(TouchHandler.class.getSimpleName());
        this.touchAdapter = touchAdapter;
    }

    /**
     * Return the implementation of the touch adapter interface.
     *
     * @return Returns the touchAdapter.
     */
    public TouchAdapter getTouchAdapter() {
        return this.touchAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final TouchEvent touchEvent) {

        final EventType<?> type = touchEvent.getEventType();

        if (TouchEvent.TOUCH_PRESSED == type) {
            getTouchAdapter().touchPressed(touchEvent);
        } else if (TouchEvent.TOUCH_RELEASED == type) {
            getTouchAdapter().touchReleased(touchEvent);
        } else if (TouchEvent.TOUCH_MOVED == type) {
            getTouchAdapter().touchMoved(touchEvent);
        } else if (TouchEvent.TOUCH_STATIONARY == type) {
            getTouchAdapter().touchStationary(touchEvent);
        } else {
            getTouchAdapter().touch(touchEvent);
        }

    }
}
