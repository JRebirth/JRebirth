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

import javafx.scene.input.TouchEvent;

import org.jrebirth.af.core.exception.CoreRuntimeException;

/**
 * The class <strong>TouchAdapter</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface TouchAdapter extends EventAdapter {

    /**
     * Manage ANY touch events.
     * 
     * Common super-type for all touch event types.
     * 
     * @param touchEvent the event to manage
     */
    default void touch(final TouchEvent touchEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void touch(final TouchEvent touchEvent)");
    }

    /**
     * Manage events when touch point is moved.
     * 
     * @param touchEvent the event to manage
     */
    default void touchMoved(final TouchEvent touchEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void touchMoved(final TouchEvent touchEvent)");
    }

    /**
     * Manage events when touch point is touched for the first time.
     * 
     * @param touchEvent the event to manage
     */
    default void touchPressed(final TouchEvent touchEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void touchPressed(final TouchEvent touchEvent)");
    }

    /**
     * Manage events when touch point is released.
     * 
     * @param touchEvent the event to manage
     */
    default void touchReleased(final TouchEvent touchEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void touchReleased(final TouchEvent touchEvent)");
    }

    /**
     * Manage events when touch point is pressed and still (doesn't move)..
     * 
     * @param touchEvent the event to manage
     */
    default void touchStationary(final TouchEvent touchEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, this.getClass(), "void touchStationary(final TouchEvent touchEvent)");
    }

}
