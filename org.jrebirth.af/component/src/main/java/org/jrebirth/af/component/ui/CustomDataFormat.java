package org.jrebirth.af.component.ui;

import javafx.scene.input.DataFormat;

import org.jrebirth.af.component.ui.beans.TabBB;

public interface CustomDataFormat {

    DataFormat TAB = new DataFormat(TabBB.class.getCanonicalName());

}
