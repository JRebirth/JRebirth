package org.jrebirth.af.demo.workbench.ui;

import javafx.scene.layout.BorderPane;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.command.tab.AddTabCommand;
import org.jrebirth.af.component.command.tab.TabWaveBean;
import org.jrebirth.af.component.ui.beans.DockPaneConfig;
import org.jrebirth.af.component.ui.beans.DockPaneOrientation;
import org.jrebirth.af.component.ui.beans.TabbedPaneConfig;
import org.jrebirth.af.component.ui.beans.TabbedPaneOrientation;
import org.jrebirth.af.component.ui.dock.DockPaneModel;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

@Register(value = ModuleModel.class)
public class TabDemoModel extends DefaultSimpleModel<BorderPane> implements ModuleModel {

	private boolean done = false;

	@Override
	protected void initSimpleView() {
		super.initSimpleView();

		final String topLeft = "TopLeft";
		final String topRight = "TopRight";
		final String bottomLeft = "BottomLeft";
		final String bottomRight = "BottomRight";
		final DockPaneModel rootDock = getModel(DockPaneModel.class,
				DockPaneConfig.create()
						.id("RootDock")
						.orientation(DockPaneOrientation.vertical)
						.panes(
								DockPaneConfig.create()
										.id("TopDock")
										.orientation(DockPaneOrientation.horizontal)
										.panes(
												DockPaneConfig.create()
														.id(topLeft+"Dock")
														.orientation(DockPaneOrientation.horizontal)
														.panes(TabbedPaneConfig.create()
																.id(topLeft)
																.styleClass("Top Left")
																.orientation(TabbedPaneOrientation.top)
																.tabs(
																		dockable("1"),
																		dockable("2"),
																		dockable("3"),
																		dockable("4"),
																		dockable("5"),
																		dockable("6")
																		)),
												DockPaneConfig.create()
														.id(topRight+"Dock")
														.orientation(DockPaneOrientation.horizontal)
														.panes(TabbedPaneConfig.create()
																.id(topRight)
																.styleClass("Top Right")
																.orientation(TabbedPaneOrientation.right)
																.tabs(
																		dockable("7"),
																		dockable("8"),
																		dockable("9")
																		))),
								DockPaneConfig.create()
										.id("BottomDock")
										.orientation(DockPaneOrientation.horizontal)
										.panes(
												DockPaneConfig.create()
														.id(bottomLeft+"Dock")
														.orientation(DockPaneOrientation.horizontal)
														.panes(TabbedPaneConfig.create()
																.id(bottomLeft)
																.styleClass("Bottom Left")
																.orientation(TabbedPaneOrientation.bottom)
																.tabs(
																		dockable("15"),
																		dockable("16")
																		)),
												DockPaneConfig.create()
														.id(bottomRight+"Dock")
														.orientation(DockPaneOrientation.horizontal)
														.panes(TabbedPaneConfig.create()
																.id(bottomRight)
																.styleClass("Bottom Right")
																.orientation(TabbedPaneOrientation.left)
																.tabs(
																		dockable("10"),
																		dockable("11"),
																		dockable("12"),
																		dockable("13"),
																		dockable("14")
																		)
																))));
		node().setCenter(rootDock.node());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jrebirth.af.core.ui.simple.DefaultSimpleModel#showView()
	 */
	@Override
	protected void showView() {
		super.showView();

//		if (!this.done) {
//			this.done = true;
//			final String topLeft = "TopLeft";
//			final String topRight = "TopRight";
//			final String bottomLeft = "BottomLeft";
//			final String bottomRight = "BottomRight";
//
//			addTabWithCommand(topLeft, "Tab1");
//			addTabWithCommand(topLeft, "Tab2");
//			addTabWithCommand(topLeft, "Tab3");
//			addTabWithCommand(topLeft, "Tab4");
//			addTabWithCommand(topLeft, "Tab5");
//			addTabWithCommand(topLeft, "Tab6");
//
//			addTabWithCommand(topRight, "Tab7");
//			addTabWithCommand(topRight, "Tab8");
//			addTabWithCommand(topRight, "Tab9");
//
//			addTabWithCommand(bottomRight, "Tab10");
//			addTabWithCommand(bottomRight, "Tab11");
//			addTabWithCommand(bottomRight, "Tab12");
//			addTabWithCommand(bottomRight, "Tab13");
//			addTabWithCommand(bottomRight, "Tab14");
//
//			addTabWithCommand(bottomLeft, "Tab15");
//			addTabWithCommand(bottomLeft, "Tab16");
//		}
	}

//	private void addTabWithCommand(final String tabKey, final String modelKey) {
//		callCommand(AddTabCommand.class,
//				TabWaveBean.create()
//						.tabHolderKey(tabKey)
//						.tab(Dockable.create().modelKey(
//								Key.create(SimpleModel.class, modelKey))
//								.name(modelKey)));
//	}
	
	private Dockable dockable(String modelKey){
		return Dockable.create().modelKey(
				Key.create(SimpleModel.class, "Tab"+modelKey))
				.name("Tab"+modelKey);
	}

	@Override
	public String moduleName() {
		return "Workbench";
	}

}
