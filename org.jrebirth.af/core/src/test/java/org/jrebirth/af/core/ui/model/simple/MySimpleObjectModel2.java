package org.jrebirth.af.core.ui.model.simple;

import javafx.scene.layout.BorderPane;

import org.jrebirth.af.core.ui.model.ModelBean2;
import org.jrebirth.af.core.ui.simple.DefaultSimpleObjectModel;

public class MySimpleObjectModel2 extends DefaultSimpleObjectModel<BorderPane, ModelBean2> {

    @Override
    protected void initSimpleView() {
        node().setCenter(new BorderPane());
    }

}
