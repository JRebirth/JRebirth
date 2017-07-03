package org.jrebirth.af.showcase.todos.ui.status;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.showcase.todos.service.TodoService;
import org.jrebirth.af.showcase.todos.ui.WWaves;
import org.jrebirth.af.showcase.todos.ui.content.ContentModel;

public class StatusModel extends DefaultModel<StatusModel, StatusView> {

    @Override
    protected void bind() {
        super.bind();

        // getService(TodoService.class).getTodoList().pTodo().add

        view().getGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(final ObservableValue<? extends Toggle> observable, final Toggle oldValue, final Toggle newValue) {

                getModel(ContentModel.class).setFilterId(((ToggleButton) newValue).getUserData());
                getModel(ContentModel.class).refresh();

            }
        });
    }

    @OnWave(WWaves.UPDATE_STATUS)
    public void updateStatus(final Wave wave) {

        view().updateStatus(getService(TodoService.class).getTodoList().todo().stream().filter(t -> !t.done()).count());
    }

}
