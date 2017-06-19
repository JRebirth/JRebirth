package org.jrebirth.af.showcase.todos.service;

import static org.jrebirth.af.core.wave.WBuilder.waveType;

import java.util.List;

import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.wave.JRebirthItems;
import org.jrebirth.af.core.wave.WaveItemBase;

import bean.TodoList;

/**
 * The interface <strong>TodoService</strong>.
 *
 * @author Sébastien Bordes
 */
@RegistrationPoint
public interface TodoService extends Service {

    String ADDED = "ADDED";

    WaveItem<String> TODO = new WaveItemBase<String>() {
    };

    WaveItem<List<TodoList>> RESULT = new WaveItemBase<List<TodoList>>() {
    };

    /** Perform something. */
    WaveType DO_COMPARE = waveType("ADD")
                                         .items(TODO)
                                         .returnItem(JRebirthItems.booleanItem)
                                         .returnAction(ADDED);

    boolean doAdd(final String text, final Wave wave);

}
