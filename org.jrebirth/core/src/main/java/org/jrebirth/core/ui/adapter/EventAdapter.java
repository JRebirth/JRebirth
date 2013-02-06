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
package org.jrebirth.core.ui.adapter;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import javafx.stage.WindowEvent;

import org.jrebirth.core.ui.handler.DragHandler;
import org.jrebirth.core.ui.handler.KeyHandler;
import org.jrebirth.core.ui.handler.MouseHandler;
import org.jrebirth.core.ui.handler.RotateHandler;
import org.jrebirth.core.ui.handler.ScrollHandler;
import org.jrebirth.core.ui.handler.SwipeHandler;
import org.jrebirth.core.ui.handler.TouchHandler;
import org.jrebirth.core.ui.handler.WindowHandler;
import org.jrebirth.core.ui.handler.ZoomHandler;

/**
 * The class <strong>EventAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <C> The controller class which manage this event adapter
 */
public interface EventAdapter {

    // /**
    // * @param controller The controller to set.
    // */
    // void setController(final C controller);

    enum Linker {

        // Action(ActionEvent.ANY, ActionAdapter.class, ActionHandler.class),
        Mouse(MouseEvent.ANY, MouseAdapter.class, MouseHandler.class),
        Key(KeyEvent.ANY, KeyAdapter.class, KeyHandler.class),
        Drag(DragEvent.ANY, DragAdapter.class, DragHandler.class),
        Rotate(RotateEvent.ANY, RotateAdapter.class, RotateHandler.class),
        Scroll(ScrollEvent.ANY, ScrollAdapter.class, ScrollHandler.class),
        Swipe(SwipeEvent.ANY, SwipeAdapter.class, SwipeHandler.class),
        Touch(TouchEvent.ANY, TouchAdapter.class, TouchHandler.class),
        Window(WindowEvent.ANY, WindowAdapter.class, WindowHandler.class),
        Zoom(ZoomEvent.ANY, ZoomAdapter.class, ZoomHandler.class);

        /** The JavaFX internal api name. */
        private final EventType<?> eventType;

        private final Class<? extends EventAdapter> adapterClass;

        private final Class<? extends EventHandler<?>> handlerClass;

        /**
         * Default constructor used to link the apiName
         * 
         * @param eventType the javafx event type
         */
        private <E extends Event> Linker(final EventType<E> eventType, final Class<? extends EventAdapter> adapterClass, final Class<? extends EventHandler<E>> handlerClass) {
            this.eventType = eventType;
            this.adapterClass = adapterClass;
            this.handlerClass = handlerClass;
        }

        /**
         * {@inheritDoc}
         */
        public EventType<?> eventType() {
            return this.eventType;
        }

        /**
         * {@inheritDoc}
         */
        public Class<? extends EventAdapter> adapterClass() {
            return this.adapterClass;
        }

        /**
         * {@inheritDoc}
         */
        public Class<? extends EventHandler<?>> handlerClass() {
            return this.handlerClass;
        }

    }
}
