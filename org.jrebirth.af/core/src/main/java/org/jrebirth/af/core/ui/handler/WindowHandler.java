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
import javafx.stage.WindowEvent;

import org.jrebirth.af.core.ui.adapter.WindowAdapter;

/**
 * The interface <strong>WindowHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public final class WindowHandler extends AbstractNamedEventHandler<WindowEvent> {

    /** The Window adapter. */
    private final WindowAdapter windowAdapter;

    /**
     * Default Constructor.
     *
     * @param windowAdapter the adapter to use
     */
    public WindowHandler(final WindowAdapter windowAdapter) {
        super(WindowHandler.class.getSimpleName());
        this.windowAdapter = windowAdapter;
    }

    /**
     * Return the implementation of the window adapter interface.
     *
     * @return Returns the WindowAdapter.
     */
    public WindowAdapter getWindowAdapter() {
        return this.windowAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final WindowEvent windowEvent) {

        final EventType<?> type = windowEvent.getEventType();

        if (WindowEvent.WINDOW_CLOSE_REQUEST == type) {
            getWindowAdapter().windowCloseRequest(windowEvent);
        } else if (WindowEvent.WINDOW_HIDDEN == type) {
            getWindowAdapter().windowHidden(windowEvent);
        } else if (WindowEvent.WINDOW_HIDING == type) {
            getWindowAdapter().windowHiding(windowEvent);
        } else if (WindowEvent.WINDOW_SHOWING == type) {
            getWindowAdapter().windowShowing(windowEvent);
        } else if (WindowEvent.WINDOW_SHOWN == type) {
            getWindowAdapter().windowShown(windowEvent);
        } else {
            getWindowAdapter().window(windowEvent);
        }

    }
}
