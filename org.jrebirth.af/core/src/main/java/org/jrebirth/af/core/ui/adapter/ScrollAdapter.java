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

import javafx.scene.input.ScrollEvent;

/**
 * The class <strong>ScrollAdapter</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface ScrollAdapter extends EventAdapter {

    /**
     * Manage ANY scroll events.
     * 
     * Common super-type for all scroll event types.
     * 
     * @param scrollEvent the event to manage
     */
    void anyScroll(final ScrollEvent scrollEvent);

    /**
     * Manage events when a scrolling gesture is detected.
     * 
     * @param scrollEvent the event to manage
     */
    void scrollStarted(final ScrollEvent scrollEvent);

    /**
     * Manage events when user performs a scrolling gesture such as rotating mouse wheel or dragging a finger over touch screen.
     * 
     * @param scrollEvent the event to manage
     */
    void scroll(final ScrollEvent scrollEvent);

    /**
     * Manage events when a scrolling gesture ends.
     * 
     * @param scrollEvent the event to manage
     */
    void scrollFinished(final ScrollEvent scrollEvent);

}
