package org.jrebirth.af.showcase.wave;

import javafx.scene.layout.StackPane;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.showcase.data.ui.main.MainModel;

public class WaveApplication extends DefaultApplication<StackPane> {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public Class<? extends Model> firstModelClass() {
        return MainModel.class;
    }

}
