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
import javafx.scene.input.ScrollEvent;

import org.jrebirth.af.core.ui.adapter.ScrollAdapter;

/**
 * The interface <strong>ScrollHandler</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ScrollHandler extends AbstractNamedEventHandler<ScrollEvent> {

    /** The Scroll adapter. */
    private final ScrollAdapter scrollAdapter;

    /**
     * Default Constructor.
     * 
     * @param scrollAdapter the adapter to use
     */
    public ScrollHandler(final ScrollAdapter scrollAdapter) {
        super(ScrollHandler.class.getSimpleName());
        this.scrollAdapter = scrollAdapter;
    }

    /**
     * Return the implementation of the scroll adapter interface.
     * 
     * @return Returns the scrollAdapter.
     */
    public ScrollAdapter getScrollAdapter() {
        return this.scrollAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final ScrollEvent scrollEvent) {

        final EventType<?> type = scrollEvent.getEventType();

        if (ScrollEvent.SCROLL_STARTED == type) {
            getScrollAdapter().scrollStarted(scrollEvent);
        } else if (ScrollEvent.SCROLL == type) {
            getScrollAdapter().scroll(scrollEvent);
        } else if (ScrollEvent.SCROLL_FINISHED == type) {
            getScrollAdapter().scrollFinished(scrollEvent);
        } else {
            getScrollAdapter().anyScroll(scrollEvent);
        }

    }
}
