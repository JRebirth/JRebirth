package org.jrebirth.af.component.workbench.ui;

import javafx.scene.text.Text;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.core.ui.simple.DefaultSimpleObjectModel;

public class DockableModel extends DefaultSimpleObjectModel<Text, String> {

    @Override
    protected void initModel() {
        addBehavior(Dockable.create()
                            .name(getObject())
                            .modelKey(getKey()));
    }

    @Override
    protected void initSimpleView() {

        getRootNode().setText(getObject());

    }

}
