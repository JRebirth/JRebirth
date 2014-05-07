package org.jrebirth.af.component.ui.tab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import org.jrebirth.af.component.ui.beans.Tab;
import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;

public class TabController extends DefaultController<TabModel, TabView> {

	public TabController(TabView view) throws CoreException {
		super(view);
	}

	@Override
	protected void initEventAdapters() throws CoreException {

		addAdapter(new TabSourceDragAdapter());
		addAdapter(new TabTargetDragAdapter());

	}

	@Override
	protected void initEventHandlers() throws CoreException {

		getView().getBox().setOnDragOver(getHandler(DragEvent.DRAG_OVER));
		getView().getBox().setOnDragEntered(getHandler(DragEvent.DRAG_OVER));
		getView().getBox().setOnDragExited(getHandler(DragEvent.DRAG_EXITED));
		getView().getBox().setOnDragDropped(getHandler(DragEvent.DRAG_DROPPED));

	}

	void initTabEventHandler(Button tabButton) {

		try {
			tabButton.setOnDragDetected(getHandler(MouseEvent.DRAG_DETECTED));
			tabButton.setOnDragDone(getHandler(DragEvent.DRAG_DONE));
			
			
			tabButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					Tab t = (Tab)  ((Button)event.getSource()).getUserData();
					
					getView().selectTab(t);
					
				}
			});
			
			
		} catch (CoreException ce) {
			
		}
	}

}
