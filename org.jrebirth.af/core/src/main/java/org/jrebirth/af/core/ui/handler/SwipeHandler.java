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
import javafx.scene.input.SwipeEvent;

import org.jrebirth.af.core.ui.adapter.SwipeAdapter;

/**
 * The interface <strong>SwipeHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public final class SwipeHandler extends AbstractNamedEventHandler<SwipeEvent, SwipeAdapter> {

    /**
     * Default Constructor.
     *
     * @param swipeAdapter the adapter to use
     */
    public SwipeHandler(final SwipeAdapter swipeAdapter) {
        super(SwipeHandler.class.getSimpleName(), swipeAdapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final SwipeEvent swipeEvent) {

        final EventType<?> type = swipeEvent.getEventType();

        if (SwipeEvent.SWIPE_DOWN == type) {
            adapter().swipeDown(swipeEvent);
        } else if (SwipeEvent.SWIPE_LEFT == type) {
            adapter().swipeLeft(swipeEvent);
        } else if (SwipeEvent.SWIPE_RIGHT == type) {
            adapter().swipeRight(swipeEvent);
        } else if (SwipeEvent.SWIPE_UP == type) {
            adapter().swipeUp(swipeEvent);
        } else {
            adapter().anySwipe(swipeEvent);
        }

    }
}
