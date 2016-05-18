package org.jrebirth.af.demo.workbench.ui;

import javafx.scene.layout.BorderPane;

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
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

@Register(value = ModuleModel.class)
public class TabDemoModel extends DefaultSimpleModel<BorderPane> implements ModuleModel {

    @Override
    protected void initSimpleView() {
        super.initSimpleView();

        final DockModel rootDock = getModel(DockModel.class,
                                            DockConfig.create()
                                                      .key("RootDock")
                                                      .orientation(DockOrientation.vertical)
                                                      .panes(
                                                             DockConfig.create().key("TopDock").orientation(DockOrientation.horizontal).panes(
                                                                                                                                              TabConfig.create().key("TopLeft")
                                                                                                                                                       .orientation(TabOrientation.top),
                                                                                                                                              TabConfig.create().key("TopRight")
                                                                                                                                                       .orientation(TabOrientation.right)),
                                                             DockConfig.create().key("BottomDock").orientation(DockOrientation.horizontal).panes(
                                                                                                                                                 TabConfig.create().key("BottomLeft")
                                                                                                                                                          .orientation(TabOrientation.bottom),
                                                                                                                                                 TabConfig.create().key("BottomRight")
                                                                                                                                                          .orientation(TabOrientation.left))));
        node().setCenter(rootDock.node());

        addTabWithCommand("TopLeft", Key.create(DockableModel.class, "Tab1"));
        addTabWithCommand("TopLeft", Key.create(DockableModel.class, "Tab2"));
        addTabWithCommand("TopLeft", Key.create(DockableModel.class, "Tab3"));
        addTabWithCommand("TopLeft", Key.create(DockableModel.class, "Tab4"));
        addTabWithCommand("TopLeft", Key.create(DockableModel.class, "Tab5"));
        addTabWithCommand("TopLeft", Key.create(DockableModel.class, "Tab6"));

        addTabWithCommand("TopRight", Key.create(DockableModel.class, "Tab7"));
        addTabWithCommand("TopRight", Key.create(DockableModel.class, "Tab8"));
        addTabWithCommand("TopRight", Key.create(DockableModel.class, "Tab9"));

        addTabWithCommand("BottomRight", Key.create(DockableModel.class, "Tab10"));
        addTabWithCommand("BottomRight", Key.create(DockableModel.class, "Tab11"));
        addTabWithCommand("BottomRight", Key.create(DockableModel.class, "Tab12"));
        addTabWithCommand("BottomRight", Key.create(DockableModel.class, "Tab13"));
        addTabWithCommand("BottomRight", Key.create(DockableModel.class, "Tab14"));

        addTabWithCommand("BottomLeft", Key.create(DockableModel.class, "Tab15"));
        addTabWithCommand("BottomLeft", Key.create(DockableModel.class, "Tab16"));

    }

    private void addTabWithCommand(final String tabKey, final UniqueKey<? extends Model> modelKey) {
        callCommand(AddTabCommand.class,
                    TabWaveBean.create()
                               .tabHolderKey(tabKey)
                               // .modelKey(modelKey)
                               .tab(Dockable.create().modelKey(modelKey).name(modelKey.value().toString())));
    }

    @Override
    public String moduleName() {
        return "Workbench";
    }

}
