package org.jrebirth.af.component.ui;

import javafx.scene.input.DataFormat;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;

public interface CustomDataFormat {

    DataFormat DOCKABLE = new DataFormat(Dockable.class.getCanonicalName());

}
