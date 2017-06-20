package org.jrebirth.af.showcase.todos.ui.header;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.showcase.todos.service.TodoService;

public final class HeaderController extends DefaultController<HeaderModel, HeaderView> {

    public HeaderController(final HeaderView view) throws CoreException {
        super(view);
    }

    @Override
    protected void initEventAdapters() throws CoreException {
        super.initEventAdapters();

        linkService(view().todoText(), KeyEvent.KEY_RELEASED, TodoService.class, TodoService.DO_ADD, event -> event.getCode() == KeyCode.ENTER && !view().todoText().getText().isEmpty(),
                    WBuilder.waveData(TodoService.TODO_STRING, view().todoText().textProperty()));
    }

    public void onAction(final ActionEvent event) {

    	if(event.getSource() == view().selectAll()){
    		model().selectAll(view().selectAll().isSelected());
    	}
        /*
         * if(event.getCode() == KeyCode.ENTER){
         * 
         * model().returnData(TodoService.class, TodoService.DO_ADD, WBuilder.waveData(TodoService.TODO, view().todoText().getText()));
         * 
         * }
         */

    }

}
