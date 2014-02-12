package org.jrebirth.core.ui.model.simple;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;

import org.jrebirth.af.core.ui.simple.DefaultSimpleObjectModel;
import org.jrebirth.core.ui.model.ModelBean2;

public class MySimpleObjectModel2 extends DefaultSimpleObjectModel<BorderPane, ModelBean2> {

    @Override
    protected BorderPane prepareNode() {
        return BorderPaneBuilder.create().build();
    }

}
