package org.jrebirth.core.link;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

/**
 * The class <strong>JRebirthWaveItem</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum JRebirthWaveItem implements WaveItem {

    /** . */
    attachUi(ObjectProperty.class),

    /** . */
    addUi(ObservableList.class);

    /** . */
    private Class<?> dataClass;

    private JRebirthWaveItem(final Class<?> dataClass) {
        this.dataClass = dataClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> dataClass() {
        return this.dataClass;
    }
}
