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

import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.exception.CoreRuntimeException;

/**
 * The class <strong>MouseAdapter</strong>.
 *
 * @author Sébastien Bordes
 */
public interface MouseAdapter extends EventAdapter {

    /**
     * Manage mouse ANY events.
     *
     * Common supertype for all mouse event types.
     *
     * @param mouseEvent the event to manage
     */
    default void mouse(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouse(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse drag detected events.
     *
     * This event is delivered to a node that is identified as a potential source of drag and drop gesture.
     *
     * @param mouseEvent the event to manage
     */
    default void mouseDragDetected(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseDragDetected(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse clicked events.
     *
     * This event occurs when mouse button has been clicked (pressed and released on the same node).
     *
     * @param mouseEvent the event to manage
     */
    default void mouseClicked(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseClicked(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse dragged events.
     *
     * This event occurs when mouse moves with a pressed button.
     *
     * @param mouseEvent the event to manage
     */
    default void mouseDragged(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseDragged(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse entered events.
     *
     * This event occurs when mouse enters a node.
     *
     * @param mouseEvent the event to manage
     */
    default void mouseEntered(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseEntered(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse entered target events.
     *
     * This event occurs when mouse enters a node.
     *
     * @param mouseEvent the event to manage
     */
    default void mouseEnteredTarget(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseEnteredTarget(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse exited events.
     *
     * This event occurs when mouse exits a node.
     *
     * @param mouseEvent the event to manage
     */
    default void mouseExited(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseExited(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse exited target events.
     *
     * This event occurs when mouse exits a node.
     *
     * @param mouseEvent the event to manage
     */
    default void mouseExitedTarget(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseExitedTarget(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse moved events.
     *
     * This event occurs when mouse moves within a node and no buttons are pressed.
     *
     * @param mouseEvent the event to manage
     */
    default void mouseMoved(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseMoved(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse pressed events.
     *
     * This event occurs when mouse button is pressed.
     *
     * @param mouseEvent the event to manage
     */
    default void mousePressed(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mousePressed(final MouseEvent mouseEvent)");
    }

    /**
     * Manage mouse released events.
     *
     * This event occurs when mouse button is released.
     *
     * @param mouseEvent the event to manage
     */
    default void mouseReleased(final MouseEvent mouseEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void mouseReleased(final MouseEvent mouseEvent)");
    }

}
