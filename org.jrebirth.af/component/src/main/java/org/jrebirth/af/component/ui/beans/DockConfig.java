package org.jrebirth.af.component.ui.beans;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.ui.dock.DockModel;

public class DockConfig extends PartConfig<DockConfig> {

    private final ObjectProperty<DockOrientation> orientationPy = new SimpleObjectProperty<>(DockOrientation.vertical);

    private final ObservableList<PartConfig<?>> panes = FXCollections.observableArrayList();

    public static DockConfig create() {
        return new DockConfig(DockModel.class);
    }

    public DockConfig(final Class<? extends Model> modelClass) {
        super(modelClass);
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

    public DockConfig panes(final PartConfig<?>... configs) {
        this.panes.addAll(configs);
        return this;
    }

    public ObservableList<PartConfig<?>> panes() {
        return this.panes;
    }
}
