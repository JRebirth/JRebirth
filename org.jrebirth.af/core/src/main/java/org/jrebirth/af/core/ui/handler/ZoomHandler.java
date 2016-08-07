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
import javafx.scene.input.ZoomEvent;

import org.jrebirth.af.core.ui.adapter.ZoomAdapter;

/**
 * The interface <strong>ZoomHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public final class ZoomHandler extends AbstractNamedEventHandler<ZoomEvent, ZoomAdapter> {

    /**
     * Default Constructor.
     *
     * @param zoomAdapter the adapter to use
     */
    public ZoomHandler(final ZoomAdapter zoomAdapter) {
        super(ZoomHandler.class.getSimpleName(), zoomAdapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final ZoomEvent zoomEvent) {

        final EventType<?> type = zoomEvent.getEventType();

        if (ZoomEvent.ZOOM_STARTED == type) {
            adapter().zoomStarted(zoomEvent);
        } else if (ZoomEvent.ZOOM == type) {
            adapter().zoom(zoomEvent);
        } else if (ZoomEvent.ZOOM_FINISHED == type) {
            adapter().zoomFinished(zoomEvent);
        } else {
            adapter().anyZoom(zoomEvent);
        }

    }
}
