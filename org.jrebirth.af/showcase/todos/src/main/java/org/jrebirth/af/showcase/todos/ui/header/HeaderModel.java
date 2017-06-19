package org.jrebirth.af.showcase.todos.ui.header;

import javafx.beans.binding.Bindings;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.showcase.todos.service.TodoService;

public class HeaderModel extends DefaultModel<HeaderModel, HeaderView> {

    @Override
    protected void bind() {
        super.bind();

        view().selectAll().visibleProperty().bind(Bindings.size(getService(TodoService.class).getTodoList().pTodo()).greaterThan(0));

    }

    @OnWave(TodoService.ADDED)
    public void todoAdded(final boolean added, final Wave wave) {

        if (added) {
            view().todoText().setText("");
        } else {
            // warn user
        }

    }
}
