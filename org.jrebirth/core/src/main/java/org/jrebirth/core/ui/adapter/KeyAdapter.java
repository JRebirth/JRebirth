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

import javafx.scene.input.KeyEvent;

/**
 * The class <strong>KeyAdapter</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface KeyAdapter extends EventAdapter {

    /**
     * Manage key ANY events.
     * 
     * Common supertype for all key event types.
     * 
     * @param keyEvent the event to manage
     */
    void key(final KeyEvent keyEvent);

    /**
     * Manage key pressed events.
     * 
     * This event occurs when a key has been pressed.
     * 
     * @param keyEvent the event to manage
     */
    void keyPressed(final KeyEvent keyEvent);

    /**
     * Manage key released events.
     * 
     * This event occurs when a key has been released.
     * 
     * @param keyEvent the event to manage
     */
    void keyReleased(final KeyEvent keyEvent);

    /**
     * Manage key typed events.
     * 
     * This event occurs when a key has been typed (pressed and released).
     * 
     * @param keyEvent the event to manage
     */
    void keyTyped(final KeyEvent keyEvent);

}
