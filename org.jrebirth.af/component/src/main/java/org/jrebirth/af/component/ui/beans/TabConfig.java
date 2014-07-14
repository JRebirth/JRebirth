package org.jrebirth.af.component.ui.beans;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;

public class TabConfig {

    private String tabKey;

    private final ObjectProperty<TabOrientation> orientationPy = new SimpleObjectProperty<>(TabOrientation.top);

    private final ObservableList<Dockable> tabs = FXCollections.observableArrayList();

    public static TabConfig create() {
        return new TabConfig();
    }

    public String tabKey() {
        return this.tabKey;
    }

    public TabConfig tabKey(final String tabKey) {
        this.tabKey = tabKey;
        return this;
    }

    public ObjectProperty<TabOrientation> orientationPy() {
        return this.orientationPy;
    }

    public TabOrientation orientation() {
        return this.orientationPy.get();
    }

    public TabConfig orientation(final TabOrientation orientation) {
        this.orientationPy.set(orientation);
        return this;
    }

    public ObservableList<Dockable> tabs() {
        return this.tabs;
    }

}
