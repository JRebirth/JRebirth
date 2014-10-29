/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License") {
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

import javafx.stage.WindowEvent;

import org.jrebirth.af.api.exception.CoreRuntimeException;

/**
 * The class <strong>WindowAdapter</strong>.
 *
 * @author Sébastien Bordes
 */
public interface WindowAdapter extends EventAdapter {

    /**
     * Manage window ANY events.
     *
     * Common supertype for all window event types.
     *
     * @param windowEvent the event to manage
     */
    default void window(final WindowEvent windowEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void window(final WindowEvent windowEvent)");
    }

    /**
     * Manage window close request events.
     *
     * This event is delivered to a window when there is an external request to close that window.
     *
     * @param windowEvent the event to manage
     */
    default void windowCloseRequest(final WindowEvent windowEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void windowCloseRequest(final WindowEvent windowEvent)");
    }

    /**
     * Manage window hidden events.
     *
     * This event occurs on window just after it is hidden.
     *
     * @param windowEvent the event to manage
     */
    default void windowHidden(final WindowEvent windowEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void windowHidden(final WindowEvent windowEvent)");
    }

    /**
     * Manage window hiding events.
     *
     * This event occurs on window just before it is hidden.
     *
     * @param windowEvent the event to manage
     */
    default void windowHiding(final WindowEvent windowEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void windowHiding(final WindowEvent windowEvent)");
    }

    /**
     * Manage window showing events.
     *
     * This event occurs on window just before it is shown.
     *
     * @param windowEvent the event to manage
     */
    default void windowShowing(final WindowEvent windowEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void windowShowing(final WindowEvent windowEvent)");
    }

    /**
     * Manage window shown events.
     *
     * This event occurs on window just after it is shown.
     *
     * @param windowEvent the event to manage
     */
    default void windowShown(final WindowEvent windowEvent) {
        throw new CoreRuntimeException(NOT_IMPLEMENTED_YET, getClass(), "void windowShown(final WindowEvent windowEvent)");
    }

}
