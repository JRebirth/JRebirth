package org.jrebirth.af.showcase.todos.service;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.service.DefaultService;

import bean.Todo;
import bean.TodoList;

public class TodoServiceImpl extends DefaultService implements TodoService {

    TodoList todoList = new TodoList();

    /**
     * @return Returns the todoList.
     */
    TodoList getTodoList() {
        return todoList;
    }

    @Override
    public boolean doAdd(String text, Wave wave) {

        final Todo t = Todo.of().test(text).done(false);

        todoList.pTodo().set(t);

        return true;
    }

}
