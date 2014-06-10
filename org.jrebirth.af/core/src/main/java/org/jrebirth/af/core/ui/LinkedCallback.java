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
package org.jrebirth.af.core.ui;

import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * The class <strong>LinkedCallback</strong>.
 *
 * @author Sébastien Bordes
 */
public final class LinkedCallback {

    /** The mouse single click checker. */
    public static final Callback<MouseEvent, Boolean> CHECK_MOUSE_SINGLE_CLICK = new Callback<MouseEvent, Boolean>() {

        /**
         * Check if the mouse event is a single click.
         *
         * @param event the mouse event triggered
         *
         * @return true for single click
         */
        @Override
        public Boolean call(final MouseEvent event) {
            return event.getClickCount() == 1;
        }
    };

    /** The mouse double click checker. */
    public static final Callback<MouseEvent, Boolean> CHECK_MOUSE_DOUBLE_CLICK = new Callback<MouseEvent, Boolean>() {

        /**
         * Check if the mouse event is a double click.
         *
         * @param event the mouse event triggered
         *
         * @return true for double click
         */
        @Override
        public Boolean call(final MouseEvent event) {
            return event.getClickCount() == 2;
        }
    };

    /**
     * Private Constructor.
     */
    private LinkedCallback() {
        // Nothing to do
    }
}
