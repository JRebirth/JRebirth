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
package org.jrebirth.core.ui.handler;

import javafx.event.EventType;
import javafx.scene.input.KeyEvent;

import org.jrebirth.core.ui.adapter.KeyAdapter;

/**
 * The interface <strong>KeyHandler</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class KeyHandler extends AbstractNamedEventHandler<KeyEvent> {

    /** The Key adapter. */
    private final KeyAdapter keyAdapter;

    /**
     * Default Constructor.
     * 
     * @param keyAdapter the adapter to use
     */
    public KeyHandler(final KeyAdapter keyAdapter) {
        super(KeyHandler.class.getSimpleName());
        this.keyAdapter = keyAdapter;
    }

    /**
     * Return the implementation of the key adapter interface.
     * 
     * @return Returns the keyAdapter.
     */
    public KeyAdapter getKeyAdapter() {
        return this.keyAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final KeyEvent keyEvent) {

        final EventType<?> type = keyEvent.getEventType();

        if (KeyEvent.KEY_PRESSED == type) {
            getKeyAdapter().keyPressed(keyEvent);
        } else if (KeyEvent.KEY_RELEASED == type) {
            getKeyAdapter().keyReleased(keyEvent);
        } else if (KeyEvent.KEY_TYPED == type) {
            getKeyAdapter().keyTyped(keyEvent);
        } else {
            getKeyAdapter().key(keyEvent);
        }

    }
}
