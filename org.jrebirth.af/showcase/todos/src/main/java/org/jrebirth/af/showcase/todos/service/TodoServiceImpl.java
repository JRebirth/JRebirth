package org.jrebirth.af.showcase.todos.service;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.service.DefaultService;
import org.jrebirth.af.showcase.todos.bean.Todo;
import org.jrebirth.af.showcase.todos.bean.TodoList;

import javafx.beans.property.StringProperty;

@Register(value = TodoService.class)
public class TodoServiceImpl extends DefaultService implements TodoService {

    private final TodoList todoList = new TodoList();

    /**
     * @return Returns the todoList.
     */
    @Override
    public TodoList getTodoList() {
        return this.todoList;
    }

    @Override
    public boolean doAdd(final StringProperty text, final Wave wave) {

        this.todoList.addTodo(Todo.of().text(text.getValue()).done(false));

        return true;
    }

    @Override
    public boolean doRemove(final Todo todo, final Wave wave) {

        this.todoList.removeTodo(todo);

        return true;
    }

}
