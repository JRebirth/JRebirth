package org.jrebirth.af.showcase.todos.ui.content;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.showcase.todos.service.TodoService;

public class ContentModel extends DefaultModel<ContentModel, ContentView> {

    @Link
    private TodoService todoService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        super.initModel();

    }

    @Override
    protected void bind() {
        super.bind();

        view().getTable().setItems(this.todoService.getTodoList().pTodo());
    }

    @OnWave(TodoService.ADDED)
    public void todoAdded(final boolean added, final Wave wave) {

        // view().getTable().setItems(todoService.getTodoList().pTodo());
        view().getTable().refresh();

    }

    public void reload(final String id) {
        // view().getTable().setItems(todoService.getTodoList().pTodo().filtered());

    }

}
