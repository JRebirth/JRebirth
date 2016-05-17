package org.jrebirth.af.demo.workbench.ui;

import javafx.scene.text.Text;

import org.jrebirth.af.component.behavior.dockable.DockableBehavior;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class TabbableModel extends DefaultSimpleModel<Text> {

    @Override
    protected void initSimpleView() {

        final Dockable bean = getBehaviorData(DockableBehavior.class);
        node().setText(bean.name());

    }

}
