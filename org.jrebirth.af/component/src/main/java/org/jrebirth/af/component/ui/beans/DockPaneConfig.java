package org.jrebirth.af.component.ui.beans;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.jrebirth.af.component.ui.dock.DockPaneModel;

public class DockPaneConfig extends PartConfig<DockPaneModel, DockPaneConfig> {

    private final ObjectProperty<DockPaneOrientation> orientationPy = new SimpleObjectProperty<>(DockPaneOrientation.vertical);

    private final ObservableList<PartConfig<?, ?>> panes = FXCollections.observableArrayList();

    public static DockPaneConfig create() {
        return new DockPaneConfig(DockPaneModel.class);
    }

    public DockPaneConfig(final Class<DockPaneModel> modelClass) {
        super(modelClass);
    }

    public ObjectProperty<DockPaneOrientation> orientationPy() {
        return this.orientationPy;
    }

    public DockPaneOrientation orientation() {
        return this.orientationPy.get();
    }

    public DockPaneConfig orientation(final DockPaneOrientation orientation) {
        this.orientationPy.set(orientation);
        return this;
    }

    public DockPaneConfig panes(final PartConfig<?, ?>... configs) {
        this.panes.addAll(configs);
        return this;
    }

    public ObservableList<PartConfig<?, ?>> panes() {
        return this.panes;
    }
}
