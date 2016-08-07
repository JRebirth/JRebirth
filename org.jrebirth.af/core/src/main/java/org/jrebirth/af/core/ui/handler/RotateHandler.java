/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.core.ui.handler;

import javafx.event.EventType;
import javafx.scene.input.RotateEvent;

import org.jrebirth.af.core.ui.adapter.RotateAdapter;

/**
 * The interface <strong>RotateHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public final class RotateHandler extends AbstractNamedEventHandler<RotateEvent, RotateAdapter> {

    /**
     * Default Constructor.
     *
     * @param rotateAdapter the adapter to use
     */
    public RotateHandler(final RotateAdapter rotateAdapter) {
        super(RotateHandler.class.getSimpleName(), rotateAdapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final RotateEvent rotateEvent) {

        final EventType<?> type = rotateEvent.getEventType();

        if (RotateEvent.ROTATION_STARTED == type) {
            adapter().rotationStarted(rotateEvent);
        } else if (RotateEvent.ROTATE == type) {
            adapter().rotate(rotateEvent);
        } else if (RotateEvent.ROTATION_FINISHED == type) {
            adapter().rotationFinished(rotateEvent);
        } else {
            adapter().anyRotate(rotateEvent);
        }

    }
}
