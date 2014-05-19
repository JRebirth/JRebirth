package org.jrebirth.af.component.ui.beans;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DockConfig {

    private TabOrientation orientation = TabOrientation.top;

    private final ObservableList<Tab> panes = FXCollections.observableArrayList();

    public static DockConfig create() {
        return new DockConfig();
    }

    public TabOrientation orientation() {
        return this.orientation;
    }

    public DockConfig orientation(final TabOrientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public ObservableList<Tab> panes() {
        return this.panes;
    }
}
