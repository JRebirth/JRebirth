package org.jrebirth.af.core.ui.model.simple;

import javafx.scene.layout.BorderPane;

import org.jrebirth.af.core.ui.model.ModelBean;
import org.jrebirth.af.core.ui.simple.DefaultSimpleObjectModel;

public class MySimpleObjectModel extends DefaultSimpleObjectModel<BorderPane, ModelBean> {

    @Override
    protected void initSimpleView() {
        node().setCenter(new BorderPane());
    }

}
