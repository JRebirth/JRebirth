package org.jrebirth.af.demo.workbench.ui;

import javafx.scene.layout.BorderPane;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.api.ui.annotation.AutoRelease;
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
@AutoRelease(value=false)
public class TabDemoModel extends DefaultSimpleModel<BorderPane> implements ModuleModel {

	private boolean done = false;
    @Override
    protected void initSimpleView() {
        super.initSimpleView();

        String topLeft = "TopLeft";
        String topRight = "TopRight";
        String bottomLeft = "BottomLeft";
        String bottomRight = "BottomRight";
        final DockModel rootDock = getModel(DockModel.class,
                                            DockConfig.create()
                                                      .id("RootDock")
                                                      .orientation(DockOrientation.vertical)
                                                      .panes(
                                                             DockConfig.create()
                                                                       .id("TopDock")
                                                                       .orientation(DockOrientation.horizontal)
                                                                       .panes(
                                                                              TabConfig.create()
                                                                                       .id(topLeft)
                                                                                       .styleClass("Top Left")
                                                                                       .orientation(TabOrientation.top),
                                                                              TabConfig.create()
                                                                                       .id(topRight)
                                                                                       .styleClass("Top Right")
                                                                                       .orientation(TabOrientation.right)),
                                                             DockConfig.create()
                                                                       .id("BottomDock")
                                                                       .orientation(DockOrientation.horizontal)
                                                                       .panes(
                                                                              TabConfig.create()
                                                                                       .id(bottomLeft)
                                                                                       .styleClass("Bottom Left")
                                                                                       .orientation(TabOrientation.bottom),
                                                                              TabConfig.create()
                                                                                       .id(bottomRight)
                                                                                       .styleClass("Bottom Right")
                                                                                       .orientation(TabOrientation.left))));
        node().setCenter(rootDock.node());

    }
    
    /* (non-Javadoc)
	 * @see org.jrebirth.af.core.ui.simple.DefaultSimpleModel#showView()
	 */
	@Override
	protected void showView() {
		super.showView();
		
		if(!done){
			done = true;
            String topLeft = "TopLeft";
            String topRight = "TopRight";
            String bottomLeft = "BottomLeft";
            String bottomRight = "BottomRight";
    
            addTabWithCommand(topLeft, "Tab1");
            addTabWithCommand(topLeft, "Tab2");
            addTabWithCommand(topLeft, "Tab3");
            addTabWithCommand(topLeft, "Tab4");
            addTabWithCommand(topLeft, "Tab5");
            addTabWithCommand(topLeft, "Tab6");

            addTabWithCommand(topRight, "Tab7");
            addTabWithCommand(topRight, "Tab8");
            addTabWithCommand(topRight, "Tab9");
            
            addTabWithCommand(bottomRight, "Tab10");
            addTabWithCommand(bottomRight, "Tab11");
            addTabWithCommand(bottomRight, "Tab12");
            addTabWithCommand(bottomRight, "Tab13");
            addTabWithCommand(bottomRight, "Tab14");
            
            addTabWithCommand(bottomLeft, "Tab15");
            addTabWithCommand(bottomLeft, "Tab16");
		}
	}




	private void addTabWithCommand(final String tabKey, final String modelKey) {
        callCommand(AddTabCommand.class,
                    TabWaveBean.create()
                               .tabHolderKey(tabKey)
                               .tab(Dockable.create().modelKey(
                                                               Key.create(SimpleModel.class, modelKey))
                                            .name(modelKey)));
    }

    @Override
    public String moduleName() {
        return "Workbench";
    }

}
