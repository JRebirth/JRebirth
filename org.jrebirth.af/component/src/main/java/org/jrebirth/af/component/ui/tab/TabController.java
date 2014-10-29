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
package org.jrebirth.af.component.ui.tab;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;

/**
 * The Class TabController.
 */
public class TabController extends DefaultController<TabModel, TabView> {

    /**
     * The Constructor.
     *
     * @param view the view
     * @throws CoreException the core exception
     */
    public TabController(final TabView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        addAdapter(new TabSourceDragAdapter());
        addAdapter(new TabTargetDragAdapter());
        addAdapter(new TabActionAdapter());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventHandlers() throws CoreException {

        getView().getBox().setOnDragOver(getHandler(DragEvent.DRAG_OVER));
        getView().getBox().setOnDragEntered(getHandler(DragEvent.DRAG_ENTERED_TARGET));
        getView().getBox().setOnDragExited(getHandler(DragEvent.DRAG_EXITED_TARGET));
        getView().getBox().setOnDragDropped(getHandler(DragEvent.DRAG_DROPPED));
        getView().getBox().setOnDragDone(getHandler(DragEvent.DRAG_DONE));

    }

    /**
     * Inits the tab event handler.
     *
     * @param tabButton the tab button
     */
    void initTabEventHandler(final Button tabButton) {

        try {
            tabButton.setOnDragDetected(getHandler(MouseEvent.DRAG_DETECTED));
            tabButton.setOnAction(getHandler(ActionEvent.ACTION));

        } catch (final CoreException ce) {
            ce.printStackTrace();
        }
    }

}
