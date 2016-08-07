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
import javafx.scene.control.ToggleButton;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TabController.
 */
public class TabbedPaneController extends DefaultController<TabbedPaneModel, TabbedPaneView> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TabbedPaneController.class);

    /**
     * The Constructor.
     *
     * @param view the view
     * @throws CoreException the core exception
     */
    public TabbedPaneController(final TabbedPaneView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        addAdapter(new TabbedPaneSourceDragAdapter());
        addAdapter(new TabbedPaneTargetDragAdapter());
        addAdapter(new TabbedPaneActionAdapter());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventHandlers() throws CoreException {

        view().getBox().setOnDragOver(getHandler(DragEvent.DRAG_OVER));
        view().getBox().setOnDragEntered(getHandler(DragEvent.DRAG_ENTERED_TARGET));
        view().getBox().setOnDragExited(getHandler(DragEvent.DRAG_EXITED_TARGET));
        view().getBox().setOnDragDropped(getHandler(DragEvent.DRAG_DROPPED));
        view().getBox().setOnDragDone(getHandler(DragEvent.DRAG_DONE));

    }

    /**
     * Inits the tab event handler.
     *
     * @param tabButton the tab button
     */
    void initTabEventHandler(final ToggleButton tabButton) {

        try {
            tabButton.setOnDragDetected(getHandler(MouseEvent.DRAG_DETECTED));
            tabButton.setOnAction(getHandler(ActionEvent.ACTION));

        } catch (final CoreException ce) {
            LOGGER.error("Error while attaching event handler", ce);
        }
    }

}
