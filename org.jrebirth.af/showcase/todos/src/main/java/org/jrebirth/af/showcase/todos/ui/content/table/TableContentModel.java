package org.jrebirth.af.showcase.todos.ui.content.table;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.Pane;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.showcase.todos.service.TodoService;
import org.jrebirth.af.showcase.todos.ui.WWaves;
import org.jrebirth.af.showcase.todos.ui.content.ContentModel;
import org.jrebirth.af.showcase.todos.ui.status.FilterKind;

import bean.Todo;

@Register(value=ContentModel.class, priority=PriorityLevel.High)
public class TableContentModel extends DefaultModel<TableContentModel, TableContentView> implements ContentModel {

    @Link
    private TodoService todoService;

    private FilterKind filterId = FilterKind.All;

    private FilteredList<Todo> filteredList;

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

        filteredList = new FilteredList<>(this.todoService.getTodoList().pTodo(), this::filter);
        view().getTable().setItems(filteredList);
    }

    /**
     * @return Returns the filteredList.
     */
    public FilteredList<Todo> getFilteredList() {
        return filteredList;
    }

    private boolean filter(Todo t) {

        switch (this.filterId) {
            case Done:
                return t.done();
            case Undone:
                return !t.done();
            default:
                return true;
        }
    }

    public void setFilterId(final Object id) {
        filterId = (FilterKind) id;
    }

    @Override
    protected void showView() {
        super.showView();
       
        view().getTable().widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldValue, Number newValue) {
                final Pane header = (Pane) view().getTable().lookup("TableHeaderRow");
                if (header.isVisible()) {
                    header.setMaxHeight(0);
                    header.setMinHeight(0);
                    header.setPrefHeight(0);
                    header.setVisible(false);
                }
            }
        });

    }

    @OnWave(WWaves.UPDATE_STATUS)
    public void updateStatus(Wave wave) {
        refresh();
    }

    public void refresh() {
        filteredList.setPredicate(this::filter);
        view().getTable().refresh();
    }

}





