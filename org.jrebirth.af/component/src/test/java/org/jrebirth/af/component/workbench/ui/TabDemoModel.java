package org.jrebirth.af.component.workbench.ui;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.command.tab.AddTabCommand;
import org.jrebirth.af.component.command.tab.TabWaveBean;
import org.jrebirth.af.component.ui.beans.DockConfig;
import org.jrebirth.af.component.ui.beans.DockOrientation;
import org.jrebirth.af.component.ui.beans.TabConfig;
import org.jrebirth.af.component.ui.beans.TabOrientation;
import org.jrebirth.af.component.ui.dock.DockModel;
import org.jrebirth.af.component.ui.tab.TabModel;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class TabDemoModel extends DefaultSimpleModel<BorderPane> {

    @Override
    protected void initSimpleView() {
        super.initSimpleView();

        // getRootNode().setOrientation(Orientation.VERTICAL);
        // SplitPane topSplit = new SplitPane();
        // topSplit.setOrientation(Orientation.HORIZONTAL);
        // SplitPane bottomSplit = new SplitPane();
        // bottomSplit.setOrientation(Orientation.HORIZONTAL);

        // getRootNode().getItems().addAll(topSplit, bottomSplit);

        final Rectangle r = new Rectangle(100, 600);
        r.setFocusTraversable(true);

        getRootNode().setLeft(r);

        final Rectangle r2 = new Rectangle(100, 600);
        r2.setFocusTraversable(true);
        getRootNode().setRight(r2);

        final DockModel rootDock = getModel(DockModel.class, DockConfig.create().dockKey("RootDock").orientation(DockOrientation.vertical));
        getRootNode().setCenter(rootDock.getRootNode());

        final TabModel topLeft = getModel(TabModel.class, TabConfig.create().tabKey("TopLeft").orientation(TabOrientation.top));
        final TabModel topRight = getModel(TabModel.class, TabConfig.create().tabKey("TopRight").orientation(TabOrientation.right));
        final TabModel bottomLeft = getModel(TabModel.class, TabConfig.create().tabKey("BottomLeft").orientation(TabOrientation.bottom));
        final TabModel bottomRight = getModel(TabModel.class, TabConfig.create().tabKey("BottomRight").orientation(TabOrientation.left));

        rootDock.addContainer(topLeft, null);
        rootDock.addContainer(topRight, null);
        rootDock.addContainer(bottomLeft, null);
        rootDock.addContainer(bottomRight, null);

        // topSplit.getItems().addAll(topLeft.getRootNode(), topRight.getRootNode());
        // bottomSplit.getItems().addAll(bottomRight.getRootNode(), bottomLeft.getRootNode());

        addTabWithCommand(topLeft.getObject(), Key.create(DockableModel.class, "Tab1"));
        addTabWithCommand(topLeft.getObject(), Key.create(DockableModel.class, "Tab2"));
        addTabWithCommand(topLeft.getObject(), Key.create(DockableModel.class, "Tab3"));
        addTabWithCommand(topLeft.getObject(), Key.create(DockableModel.class, "Tab4"));
        addTabWithCommand(topLeft.getObject(), Key.create(DockableModel.class, "Tab5"));
        addTabWithCommand(topLeft.getObject(), Key.create(DockableModel.class, "Tab6"));

        addTabWithCommand(topRight.getObject(), Key.create(DockableModel.class, "Tab7"));
        addTabWithCommand(topRight.getObject(), Key.create(DockableModel.class, "Tab8"));
        addTabWithCommand(topRight.getObject(), Key.create(DockableModel.class, "Tab9"));

        addTabWithCommand(bottomRight.getObject(), Key.create(DockableModel.class, "Tab10"));
        addTabWithCommand(bottomRight.getObject(), Key.create(DockableModel.class, "Tab11"));
        addTabWithCommand(bottomRight.getObject(), Key.create(DockableModel.class, "Tab12"));
        addTabWithCommand(bottomRight.getObject(), Key.create(DockableModel.class, "Tab13"));
        addTabWithCommand(bottomRight.getObject(), Key.create(DockableModel.class, "Tab14"));

        addTabWithCommand(bottomLeft.getObject(), Key.create(DockableModel.class, "Tab15"));
        addTabWithCommand(bottomLeft.getObject(), Key.create(DockableModel.class, "Tab16"));

    }

    private void addTabWithCommand(final TabConfig tabConfig, final UniqueKey<? extends Model> modelKey) {
        callCommand(AddTabCommand.class,
                TabWaveBean.create()
                        // .tabHolderKey(tabHolderKey)
                        .tabConfig(tabConfig)
                        // .modelKey(modelKey)
                        .tab(Dockable.create().modelKey(modelKey).name(modelKey.getValue().toString())));
    }

}
