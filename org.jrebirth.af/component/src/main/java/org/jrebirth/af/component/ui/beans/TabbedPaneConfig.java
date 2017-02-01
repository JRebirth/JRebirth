package org.jrebirth.af.component.ui.beans;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.tab.TabbedPaneModel;

public class TabbedPaneConfig extends PartConfig<TabbedPaneModel, TabbedPaneConfig> {

    private final ObjectProperty<TabbedPaneOrientation> orientationPy = new SimpleObjectProperty<>(TabbedPaneOrientation.top);

    private final ObservableList<Dockable> tabs = FXCollections.observableArrayList();

    public static TabbedPaneConfig create() {
        return new TabbedPaneConfig(TabbedPaneModel.class);
    }

    public TabbedPaneConfig(final Class<TabbedPaneModel> modelClass) {
        super(modelClass);
    }

    public ObjectProperty<TabbedPaneOrientation> orientationPy() {
        return this.orientationPy;
    }

    public TabbedPaneOrientation orientation() {
        return this.orientationPy.get();
    }

    public TabbedPaneConfig orientation(final TabbedPaneOrientation orientation) {
        this.orientationPy.set(orientation);
        return this;
    }

    public ObservableList<Dockable> tabs() {
        return this.tabs;
    }

}
