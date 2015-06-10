/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2015
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
package org.jrebirth.af.core.command.basic;

import javafx.scene.Cursor;
import javafx.scene.Node;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUICommand;

/**
 * The Class UpdateCursorCommand is sued to update the application cursor.
 */
public class UpdateCursorCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Wave wave) {

        final Cursor cursor = getKeyPart(Cursor.class);

        // A cursor shall be used as key part
        if (cursor == null) {
            throw new CoreRuntimeException("You must use a Cursor instance as a key part.");
        }

        final Node node = getKeyPart(Node.class);

        // Check if a node has been provided
        if (node == null) {
            // Define the cursor on the application's root node
            getLocalFacade().getGlobalFacade().getApplication().getRootNode().setCursor(cursor);
        } else {
            // Define the cursor for the given node
            node.setCursor(cursor);
        }

    }
}
