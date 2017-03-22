/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2017
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

import java.util.stream.Stream;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.util.Callback;

import org.jrebirth.af.api.ui.Controller;

/**
 * The interface <strong>LinkedCallback</strong> can be added to any {@link Controller} to add convenient {@link Callback}.
 *
 * @author Sébastien Bordes
 */
public interface EventFilter {

    /**
     * The mouse single click checker.
     * 
     * Check if the mouse event is a single click.
     */
    Callback<MouseEvent, Boolean> CHECK_MOUSE_SINGLE_CLICK = event -> event.getClickCount() == 1;

    /**
     * The mouse double click checker.
     * 
     * Check if the mouse event is a double click.
     */
    Callback<MouseEvent, Boolean> CHECK_MOUSE_DOUBLE_CLICK = event -> event.getClickCount() == 2;

    /**
     * Return the mouse click count checker callback.
     * 
     * Check if the mouse click count.
     * 
     * @return the mouse click count callback
     */
    default Callback<MouseEvent, Boolean> clickCount(int nb) {
        return event -> event.getClickCount() == nb;
    }

    /**
     * Return the touch count checker callback.
     * 
     * Check if the touch count.
     * 
     * @return the touch count callback
     */
    default Callback<TouchEvent, Boolean> touchCount(int nb) {
        return event -> event.getTouchCount() == nb;
    }

    /**
     * Return the allowed key checker callback (white-list).
     * 
     * Check if the key event concerns a KeyCode provided. true if the current key triggered is into provided list
     * 
     * @return the allowed callback
     */
    default Callback<KeyEvent, Boolean> allowedKeys(KeyCode... codes) {
        return event -> Stream.of(codes).anyMatch(kc -> kc == event.getCode());
    }

    /**
     * Return the forbidden key checker callback (black-list).
     * 
     * Check if the key event does NOT concern a KeyCode provided. the current key triggered is NOT into provided list
     * 
     * @return the forbidden callback
     */
    default Callback<KeyEvent, Boolean> forbiddenKeys(KeyCode... codes) {
        return event -> !Stream.of(codes).anyMatch(kc -> kc == event.getCode());
    }

    /**
     * Call handler function when callback returns true.
     * 
     * @param callback condition to evaluate
     * @param handler handler method to call taking the {@link Event} as parameter
     * 
     * @return the {@link EventHandler} using the condition and the function to call
     */
    default <E extends Event> EventHandler<E> handleIf(Callback<E, Boolean> callback, EventHandler<E> handler) {
        return event -> {
            if (callback.call(event)) {
                handler.handle(event);
            }
        };
    }

    /**
     * Call handler function when callback returns true.
     * 
     * @param callback condition to evaluate
     * @param handler handler method to call not taking any parameter
     * 
     * @return the {@link EventHandler} using the condition and the function to call
     */
    default <E extends Event> EventHandler<E> handleIf(Callback<E, Boolean> callback, Thunk handler) {
        return event -> {
            if (callback.call(event)) {
                handler.apply();
            }
        };
    }

    /**
     * The interface <strong>Thunk</strong> is used to reference a method not taking any parameter and returning void.
     *
     * @author Sébastien Bordes
     */
    @FunctionalInterface
    interface Thunk {
        /**
         * Method doing something without any argument and returning nothing.
         */
        void apply();
    }

}
