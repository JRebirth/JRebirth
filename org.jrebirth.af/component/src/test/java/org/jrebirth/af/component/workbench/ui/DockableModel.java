package org.jrebirth.af.component.workbench.ui;

import javafx.scene.text.Text;

import org.jrebirth.af.component.ui.Dockable;
import org.jrebirth.af.core.command.Command;
import org.jrebirth.af.core.service.Service;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.core.ui.simple.DefaultSimpleObjectModel;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveData;
import org.jrebirth.af.core.wave.WaveType;

public class DockableModel extends DefaultSimpleObjectModel<Text, String> implements Dockable {

	@Override
	protected void initSimpleView() {

		getRootNode().setText(getObject());
		
	}

	@Override
	public String modelName() {
		return getObject();
	}


}
