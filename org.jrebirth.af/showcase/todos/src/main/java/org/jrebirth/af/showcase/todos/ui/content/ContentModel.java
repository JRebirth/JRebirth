package org.jrebirth.af.showcase.todos.ui.content;

import javafx.collections.transformation.FilteredList;

import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.showcase.todos.bean.Todo;
import org.jrebirth.af.showcase.todos.ui.WWaves;


@RegistrationPoint
public interface ContentModel extends Model {

    FilteredList<Todo> getFilteredList();

    void setFilterId(final Object id);

    @OnWave(WWaves.UPDATE_STATUS)
    void updateStatus(Wave wave);

    void refresh();
}
