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
package org.jrebirth.af.core.ui.adapter;

import javafx.scene.input.ZoomEvent;

/**
 * The class <strong>ZoomAdapter</strong>.
 *
 * @author Sébastien Bordes
 */
public interface ZoomAdapter extends EventAdapter {

    /**
     * Manage ANY zoom events.
     *
     * Common super-type for all zoom event types.
     *
     * @param zoomEvent the event to manage
     */
    void anyZoom(final ZoomEvent zoomEvent);

    /**
     * Manage events when a zooming gesture is detected.
     *
     * @param zoomEvent the event to manage
     */
    void zoomStarted(final ZoomEvent zoomEvent);

    /**
     * Manage events when user performs a zooming gesture such as dragging two fingers apart.
     *
     * @param zoomEvent the event to manage
     */
    void zoom(final ZoomEvent zoomEvent);

    /**
     * Manage events when a zooming gesture ends.
     *
     * @param zoomEvent the event to manage
     */
    void zoomFinished(final ZoomEvent zoomEvent);

}
