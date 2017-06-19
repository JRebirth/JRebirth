package org.jrebirth.af.showcase.todos.ui.status;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.showcase.todos.ui.content.ContentModel;

public class StatusModel extends DefaultModel<StatusModel, StatusView> {

    @Override
    protected void bind() {
        super.bind();

        view().getGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(final ObservableValue<? extends Toggle> observable, final Toggle oldValue, final Toggle newValue) {

                getModel(ContentModel.class).reload(((ToggleButton) newValue).getId());

            }
        });
    }

}
