package org.jrebirth.af.showcase.fonticon;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.resources.ComponentStyles;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.showcase.fonticon.ui.main.MainModel;

public class FontIconApplication extends DefaultApplication<StackPane> {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public Class<? extends Model> firstModelClass() {
        return MainModel.class;
    }

    @Override
    protected void customizeScene(final Scene scene) {
        super.customizeScene(scene);

        addCSS(scene, ComponentStyles.DEFAULT);
    }
}
