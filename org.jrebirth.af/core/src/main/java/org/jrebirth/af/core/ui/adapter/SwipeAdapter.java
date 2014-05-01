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

import javafx.scene.input.SwipeEvent;

import org.jrebirth.af.core.exception.CoreRuntimeException;

/**
 * The class <strong>SwipeAdapter</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface SwipeAdapter extends EventAdapter {

    /**
     * Manage ANY swipe events.
     * 
     * Common super-type for all swipe event types.
     * 
     * @param swipeEvent the event to manage
     */
    default void anySwipe(final SwipeEvent swipeEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void anySwipe(final SwipeEvent swipeEvent)");
    }

    /**
     * Manage events when user performs downward swipe gesture.
     * 
     * @param swipeEvent the event to manage
     */
    default void swipeDown(final SwipeEvent swipeEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void swipeDown(final SwipeEvent swipeEvent)");
    }

    /**
     * Manage events when user performs leftward swipe gesture.
     * 
     * @param swipeEvent the event to manage
     */
    default void swipeLeft(final SwipeEvent swipeEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void swipeLeft(final SwipeEvent swipeEvent)");
    }

    /**
     * Manage events when user performs rightward swipe gesture.
     * 
     * @param swipeEvent the event to manage
     */
    default void swipeRight(final SwipeEvent swipeEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void swipeRight(final SwipeEvent swipeEvent)");
    }

    /**
     * Manage events when user performs upward swipe gesture.
     * 
     * @param swipeEvent the event to manage
     */
    default void swipeUp(final SwipeEvent swipeEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void swipeUp(final SwipeEvent swipeEvent)");
    }

}
