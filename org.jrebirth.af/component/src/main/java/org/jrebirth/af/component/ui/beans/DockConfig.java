package org.jrebirth.af.component.ui.beans;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DockConfig {

    private final ObjectProperty<DockOrientation> orientationPy = new SimpleObjectProperty<>(DockOrientation.vertical);

    private final ObservableList<TabConfig> panes = FXCollections.observableArrayList();

    private String dockKey;

    public static DockConfig create() {
        return new DockConfig();
    }

    public String dockKey() {
        return this.dockKey;
    }

    public DockConfig dockKey(final String dockKey) {
        this.dockKey = dockKey;
        return this;
    }

    public ObjectProperty<DockOrientation> orientationPy() {
        return this.orientationPy;
    }

    public DockOrientation orientation() {
        return this.orientationPy.get();
    }

    public DockConfig orientation(final DockOrientation orientation) {
        this.orientationPy.set(orientation);
        return this;
    }

    public ObservableList<TabConfig> panes() {
        return this.panes;
    }
}
