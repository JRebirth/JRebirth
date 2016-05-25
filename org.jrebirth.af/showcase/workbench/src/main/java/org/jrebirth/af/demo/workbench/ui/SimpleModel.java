package org.jrebirth.af.demo.workbench.ui;

import javafx.scene.text.Text;

import org.jrebirth.af.core.ui.simple.DefaultSimpleObjectModel;

public class SimpleModel extends DefaultSimpleObjectModel<Text, String> {

    @Override
    protected void initSimpleView() {

        node().setText(object());

    }

}
