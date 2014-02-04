package org.jrebirth.core.ui.model.simple;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;

import org.jrebirth.core.ui.model.ModelBean;
import org.jrebirth.core.ui.simple.DefaultSimpleObjectModel;

public class MySimpleObjectModel extends DefaultSimpleObjectModel<BorderPane, ModelBean> {

    @Override
    protected BorderPane prepareNode() {
        return BorderPaneBuilder.create().build();
    }

}
