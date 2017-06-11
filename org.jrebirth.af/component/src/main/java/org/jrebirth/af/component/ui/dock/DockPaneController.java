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
public class DockPaneController extends DefaultController<DockPaneModel, DockPaneView> {

    /**
     * The Constructor.
     *
     * @param view the view
     * @throws CoreException the core exception
     */
    public DockPaneController(final DockPaneView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        addAdapter(new DockPaneTargetDragAdapter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventHandlers() throws CoreException {
    	
    	node().addEventHandler(DragEvent.DRAG_OVER, getHandler(DragEvent.DRAG_OVER));
    	node().addEventHandler(DragEvent.DRAG_ENTERED_TARGET, getHandler(DragEvent.DRAG_ENTERED_TARGET));
        node().addEventHandler(DragEvent.DRAG_EXITED_TARGET,getHandler(DragEvent.DRAG_EXITED_TARGET));
        node().addEventHandler(DragEvent.DRAG_DROPPED,getHandler(DragEvent.DRAG_DROPPED));
        node().addEventHandler(DragEvent.DRAG_DONE, getHandler(DragEvent.DRAG_DONE));
    	
//    	node().addEventFilter(DragEvent.DRAG_OVER, getHandler(DragEvent.DRAG_OVER));
//    	node().addEventFilter(DragEvent.DRAG_ENTERED_TARGET, getHandler(DragEvent.DRAG_ENTERED_TARGET));
//        node().addEventFilter(DragEvent.DRAG_EXITED_TARGET,getHandler(DragEvent.DRAG_EXITED_TARGET));
//        node().addEventFilter(DragEvent.DRAG_DROPPED,getHandler(DragEvent.DRAG_DROPPED));
//        node().addEventFilter(DragEvent.DRAG_DONE, getHandler(DragEvent.DRAG_DONE));
    	
    	
//        node().setOnDragOver(getHandler(DragEvent.DRAG_OVER));
//        node().setOnDragEntered(getHandler(DragEvent.DRAG_ENTERED));
//        node().setOnDragExited(getHandler(DragEvent.DRAG_EXITED));
//        node().setOnDragDropped(getHandler(DragEvent.DRAG_DROPPED));
//        node().setOnDragDone(getHandler(DragEvent.DRAG_DONE));
        
        //node().setOnMousePressed(e -> System.out.println("Pressed=>"+ model().key().toString()) );
        //node().setOnMouseMoved(e -> System.out.println("Moved=>"+ model().key().toString()) );

    }

}
