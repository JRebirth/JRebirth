package org.jrebirth.af.showcase.todos.ui.header;

import javafx.beans.binding.Bindings;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.showcase.todos.service.TodoService;
import org.jrebirth.af.showcase.todos.ui.WWaves;
import org.jrebirth.af.showcase.todos.ui.content.ContentModel;

import bean.Todo;

public class HeaderModel extends DefaultModel<HeaderModel, HeaderView> {

    @Link
    private ContentModel contentModel;

    @Override
    protected void bind() {
        super.bind();

        view().selectAll().visibleProperty().bind(Bindings.size(this.contentModel.getFilteredList()).greaterThan(0));

    }

    @OnWave(TodoService.ADDED)
    public void todoAdded(final boolean added, final Wave wave) {

        if (added) {
            view().todoText().setText("");
        } else {
            // warn user
        }
        sendWave(WWaves.UPDATE_STATUS_WT);
    }

    public void selectAll(final boolean selected) {
        for (final Todo t : this.contentModel.getFilteredList()) {
            t.done(selected);
        }
        sendWave(WWaves.UPDATE_STATUS_WT);
    }

}
