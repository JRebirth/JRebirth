package org.jrebirth.af.component.ui;

import javafx.scene.input.DataFormat;

import org.jrebirth.af.component.ui.beans.Tab;

public interface CustomDataFormat {

    DataFormat TAB = new DataFormat(Tab.class.getCanonicalName());

}
