package org.jrebirth.af.showcase.todos.service;

import static org.jrebirth.af.core.wave.WBuilder.waveType;

import javafx.beans.property.StringProperty;

import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.wave.JRebirthItems;
import org.jrebirth.af.core.wave.WaveItemBase;

import bean.Todo;
import bean.TodoList;

/**
 * The interface <strong>TodoService</strong>.
 *
 * @author Sébastien Bordes
 */
@RegistrationPoint
public interface TodoService extends Service {

    String ADDED = "ADDED";

    String REMOVED = "REMOVED";

    WaveItem<StringProperty> TODO_STRING = new WaveItemBase<StringProperty>() {
    };

    WaveItem<Todo> TODO = new WaveItemBase<Todo>() {
    };

    // WaveItem<List<TodoList>> RESULT = new WaveItemBase<List<TodoList>>() {
    // };

    WaveType DO_ADD = waveType("ADD")
                                     .items(TODO_STRING)
                                     .returnItem(JRebirthItems.booleanItem)
                                     .returnAction(ADDED);

    WaveType DO_REMOVE = waveType("REMOVE")
                                           .items(TODO)
                                           .returnItem(JRebirthItems.booleanItem)
                                           .returnAction(REMOVED);

    boolean doAdd(final StringProperty text, final Wave wave);

    boolean doRemove(final Todo todo, final Wave wave);

    TodoList getTodoList();
}
