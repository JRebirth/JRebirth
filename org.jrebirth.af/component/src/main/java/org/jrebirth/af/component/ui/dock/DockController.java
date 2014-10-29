/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.component.ui.dock;

import javafx.scene.input.DragEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;

/**
 * The Class TabController.
 */
public class DockController extends DefaultController<DockModel, DockView> {

    /**
     * The Constructor.
     *
     * @param view the view
     * @throws CoreException the core exception
     */
    public DockController(final DockView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        addAdapter(new DockTargetDragAdapter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventHandlers() throws CoreException {

        getRootNode().setOnDragOver(getHandler(DragEvent.DRAG_OVER));
        getRootNode().setOnDragEntered(getHandler(DragEvent.DRAG_ENTERED_TARGET));
        getRootNode().setOnDragExited(getHandler(DragEvent.DRAG_EXITED_TARGET));
        getRootNode().setOnDragDropped(getHandler(DragEvent.DRAG_DROPPED));
        getRootNode().setOnDragDone(getHandler(DragEvent.DRAG_DONE));

    }

}
