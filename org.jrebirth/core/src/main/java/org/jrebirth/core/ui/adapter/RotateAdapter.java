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

import javafx.scene.input.RotateEvent;

/**
 * The class <strong>RotateAdapter</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface RotateAdapter {

    /**
     * Manage ANY rotate events.
     * 
     * Common super-type for all rotate event types.
     * 
     * @param rotateEvent the event to manage
     */
    void anyRotate(RotateEvent rotateEvent);

    /**
     * Manage events when a rotating gesture is detected.
     * 
     * @param rotateEvent the event to manage
     */
    void rotationStarted(RotateEvent rotateEvent);

    /**
     * Manage events when user performs a rotating gesture such as dragging two fingers around each other.
     * 
     * @param rotateEvent the event to manage
     */
    void rotate(RotateEvent rotateEvent);

    /**
     * Manage events when a rotating gesture ends.
     * 
     * @param rotateEvent the event to manage
     */
    void rotationFinished(RotateEvent rotateEvent);

}
