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
package org.jrebirth.af.component.ui.tab;

import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import org.jrebirth.af.component.ui.CustomDataFormat;
import org.jrebirth.af.core.ui.adapter.AbstractDefaultAdapter;
import org.jrebirth.af.core.ui.adapter.MouseAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class TabSourceDragAdapter.
 */
class TabSourceDragAdapter extends AbstractDefaultAdapter<TabController> implements MouseAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragDetected(final MouseEvent mouseEvent) {

        final Button b = (Button) mouseEvent.getSource();

        final Dragboard db = b.startDragAndDrop(TransferMode.MOVE);

        // Put a TabBB on a dragboard
        final ClipboardContent content = new ClipboardContent();
        content.put(CustomDataFormat.DOCKABLE, b.getUserData());
        db.setContent(content);

        mouseEvent.consume();
    }

}
