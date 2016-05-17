package org.jrebirth.af.demo.workbench.ui;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.ModuleModel;
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

@Register(value = ModuleModel.class)
public class TabDemoModel extends DefaultSimpleModel<BorderPane> implements ModuleModel {

    @Override
    protected void initSimpleView() {
        super.initSimpleView();

        // getRootNode().setOrientation(Orientation.VERTICAL);
        // SplitPane topSplit = new SplitPane();
        // topSplit.setOrientation(Orientation.HORIZONTAL);
        // SplitPane bottomSplit = new SplitPane();
        // bottomSplit.setOrientation(Orientation.HORIZONTAL);

        // getRootNode().getItems().addAll(topSplit, bottomSplit);

//        final Rectangle r = new Rectangle(100, 600);
//        r.setFocusTraversable(true);
//        node().setLeft(r);
//
//        final Rectangle r2 = new Rectangle(100, 600);
//        r2.setFocusTraversable(true);
//        node().setRight(r2);

        final DockModel rootDock = getModel(DockModel.class, 
        		DockConfig.create()
        		.key("RootDock")
        		.orientation(DockOrientation.vertical)
        		.panes(
        				DockConfig.create().key("TopDock").orientation(DockOrientation.horizontal).panes(
        						TabConfig.create().key("TopLeft").orientation(TabOrientation.top),
        						TabConfig.create().key("TopRight").orientation(TabOrientation.right)),
        				DockConfig.create().key("BottomDock").orientation(DockOrientation.horizontal).panes(
        						TabConfig.create().key("BottomLeft").orientation(TabOrientation.bottom),
        						TabConfig.create().key("BottomRight").orientation(TabOrientation.left)
        						)
        				)
        		);
        node().setCenter(rootDock.node());

//        rootDock.addPart(topLeft, null);
//        rootDock.addPart(topRight, null);
//        rootDock.addPart(bottomLeft, null);
//        rootDock.addPart(bottomRight, null);

        // topSplit.getItems().addAll(topLeft.getRootNode(), topRight.getRootNode());
        // bottomSplit.getItems().addAll(bottomRight.getRootNode(), bottomLeft.getRootNode());

//        addTabWithCommand(topLeft.object(), Key.create(DockableModel.class, "Tab1"));
//        addTabWithCommand(topLeft.object(), Key.create(DockableModel.class, "Tab2"));
//        addTabWithCommand(topLeft.object(), Key.create(DockableModel.class, "Tab3"));
//        addTabWithCommand(topLeft.object(), Key.create(DockableModel.class, "Tab4"));
//        addTabWithCommand(topLeft.object(), Key.create(DockableModel.class, "Tab5"));
//        addTabWithCommand(topLeft.object(), Key.create(DockableModel.class, "Tab6"));
//
//        addTabWithCommand(topRight.object(), Key.create(DockableModel.class, "Tab7"));
//        addTabWithCommand(topRight.object(), Key.create(DockableModel.class, "Tab8"));
//        addTabWithCommand(topRight.object(), Key.create(DockableModel.class, "Tab9"));
//
//        addTabWithCommand(bottomRight.object(), Key.create(DockableModel.class, "Tab10"));
//        addTabWithCommand(bottomRight.object(), Key.create(DockableModel.class, "Tab11"));
//        addTabWithCommand(bottomRight.object(), Key.create(DockableModel.class, "Tab12"));
//        addTabWithCommand(bottomRight.object(), Key.create(DockableModel.class, "Tab13"));
//        addTabWithCommand(bottomRight.object(), Key.create(DockableModel.class, "Tab14"));
//
//        addTabWithCommand(bottomLeft.object(), Key.create(DockableModel.class, "Tab15"));
//        addTabWithCommand(bottomLeft.object(), Key.create(DockableModel.class, "Tab16"));

    }

    private void addTabWithCommand(final TabConfig tabConfig, final UniqueKey<? extends Model> modelKey) {
        callCommand(AddTabCommand.class,
                    TabWaveBean.create()
                               // .tabHolderKey(tabHolderKey)
                               .tabConfig(tabConfig)
                               // .modelKey(modelKey)
                               .tab(Dockable.create().modelKey(modelKey).name(modelKey.value().toString())));
    }

	@Override
	public String moduleName() {
		return "Workbench";
	}

}
