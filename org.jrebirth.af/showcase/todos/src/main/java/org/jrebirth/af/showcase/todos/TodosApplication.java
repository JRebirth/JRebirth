package org.jrebirth.af.showcase.todos;

import javafx.scene.layout.StackPane;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.showcase.todos.ui.main.MainModel;

public class TodosApplication extends DefaultApplication<StackPane> {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public Class<? extends Model> firstModelClass() {
        return MainModel.class;
    }

}
