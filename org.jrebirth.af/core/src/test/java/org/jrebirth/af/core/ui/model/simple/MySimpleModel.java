package org.jrebirth.af.core.ui.model.simple;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;

import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class MySimpleModel extends DefaultSimpleModel<BorderPane> {

    @Override
    protected BorderPane prepareNode() {
        return BorderPaneBuilder.create().build();
    }

}
