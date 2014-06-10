package org.jrebirth.af.component.workbench.ui;

import javafx.scene.text.Text;

import org.jrebirth.af.component.ui.behavior.TabBehavior;
import org.jrebirth.af.core.ui.simple.DefaultSimpleObjectModel;

public class DockableModel extends DefaultSimpleObjectModel<Text, String> implements TabBehavior {

    @Override
    protected void initSimpleView() {

        getRootNode().setText(getObject());

    }

}
