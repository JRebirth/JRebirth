package org.jrebirth.af.component.workbench.ui;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

import org.jrebirth.af.component.command.tab.AddTabCommand;
import org.jrebirth.af.component.command.tab.TabWaveBean;
import org.jrebirth.af.component.ui.Dockable;
import org.jrebirth.af.component.ui.beans.TabBB;
import org.jrebirth.af.component.ui.beans.TabConfig;
import org.jrebirth.af.component.ui.beans.TabOrientation;
import org.jrebirth.af.component.ui.tab.TabModel;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class TabDemoModel extends DefaultSimpleModel<SplitPane> {

    @Override
    protected void initSimpleView() {
        super.initSimpleView();

        getRootNode().setOrientation(Orientation.VERTICAL);

        SplitPane topSplit = new SplitPane();
        topSplit.setOrientation(Orientation.HORIZONTAL);
        SplitPane bottomSplit = new SplitPane();
        bottomSplit.setOrientation(Orientation.HORIZONTAL);

        getRootNode().getItems().addAll(topSplit, bottomSplit);

        TabModel top = getModel(TabModel.class, TabConfig.create().tabKey("TopLeft").orientation(TabOrientation.top), "top");
        TabModel right = getModel(TabModel.class, TabConfig.create().tabKey("TopRight").orientation(TabOrientation.right), "right");
        TabModel bottom = getModel(TabModel.class, TabConfig.create().tabKey("BottomLeft").orientation(TabOrientation.bottom), "bottom");
        TabModel left = getModel(TabModel.class, TabConfig.create().tabKey("BottomRight").orientation(TabOrientation.left), "left");

        topSplit.getItems().addAll(top.getRootNode(), right.getRootNode());
        bottomSplit.getItems().addAll(left.getRootNode(), bottom.getRootNode());
        
        DockableModel model = getModel(DockableModel.class, "Tab1");
        
        callCommand(AddTabCommand.class, 
        		TabWaveBean.create()
        		.tabHolderKey("top")
        		.tab(
        				TabBB.create()
        				.name(model.modelName())
        				.modelKey((UniqueKey<? extends Dockable>) model.getKey())
        				));

//        top.addTab(getModel(DockableModel.class, "Tab1"), null);
//        top.addTab(getModel(DockableModel.class, "Tab2"), null);
//        top.addTab(getModel(DockableModel.class, "Tab3"), null);
//        top.addTab(getModel(DockableModel.class, "Tab4"), null);
//        top.addTab(getModel(DockableModel.class, "Tab5"), null);
//        top.addTab(getModel(DockableModel.class, "Tab6"), null);
//
//        right.addTab(getModel(DockableModel.class, "Tab7"), null);
//        right.addTab(getModel(DockableModel.class, "Tab8"), null);
//        right.addTab(getModel(DockableModel.class, "Tab9"), null);
//
//        bottom.addTab(getModel(DockableModel.class, "Tab10"), null);
//        bottom.addTab(getModel(DockableModel.class, "Tab11"), null);
//        bottom.addTab(getModel(DockableModel.class, "Tab12"), null);
//        bottom.addTab(getModel(DockableModel.class, "Tab13"), null);
//        bottom.addTab(getModel(DockableModel.class, "Tab14"), null);
//
//        left.addTab(getModel(DockableModel.class, "Tab15"), null);
//        left.addTab(getModel(DockableModel.class, "Tab16"), null);

    }

}
