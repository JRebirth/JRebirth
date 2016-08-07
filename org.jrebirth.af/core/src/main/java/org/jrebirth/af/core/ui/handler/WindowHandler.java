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
import javafx.stage.WindowEvent;

import org.jrebirth.af.core.ui.adapter.WindowAdapter;

/**
 * The interface <strong>WindowHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public final class WindowHandler extends AbstractNamedEventHandler<WindowEvent, WindowAdapter> {

    /**
     * Default Constructor.
     *
     * @param windowAdapter the adapter to use
     */
    public WindowHandler(final WindowAdapter windowAdapter) {
        super(WindowHandler.class.getSimpleName(), windowAdapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final WindowEvent windowEvent) {

        final EventType<?> type = windowEvent.getEventType();

        if (WindowEvent.WINDOW_CLOSE_REQUEST == type) {
            adapter().windowCloseRequest(windowEvent);
        } else if (WindowEvent.WINDOW_HIDDEN == type) {
            adapter().windowHidden(windowEvent);
        } else if (WindowEvent.WINDOW_HIDING == type) {
            adapter().windowHiding(windowEvent);
        } else if (WindowEvent.WINDOW_SHOWING == type) {
            adapter().windowShowing(windowEvent);
        } else if (WindowEvent.WINDOW_SHOWN == type) {
            adapter().windowShown(windowEvent);
        } else {
            adapter().window(windowEvent);
        }

    }
}
