package org.jrebirth.af.core.ui.model.simple;

import javafx.scene.layout.BorderPane;

import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class MySimpleModel extends DefaultSimpleModel<BorderPane> {

    @Override
    protected void initSimpleView() {
        getRootNode().setCenter(new BorderPane());
    }

}
