package org.jrebirth.af.component.ui.beans;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.tab.TabModel;

public class TabConfig extends PartConfig<TabConfig> {

    private final ObjectProperty<TabOrientation> orientationPy = new SimpleObjectProperty<>(TabOrientation.top);

    private final ObservableList<Dockable> tabs = FXCollections.observableArrayList();

    public TabConfig(final Class<? extends Model> modelClass) {
        super(modelClass);
    }

    public static TabConfig create() {
        return new TabConfig(TabModel.class);
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
