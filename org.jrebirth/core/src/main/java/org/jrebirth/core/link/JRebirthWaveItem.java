package org.jrebirth.core.link;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

/**
 * The class <strong>JRebirthWaveItem</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum JRebirthWaveItem implements WaveItem {

    /** Manage a property data class. */
    attachUi(ObjectProperty.class),

    /** Manage an observable list data class. */
    addUi(ObservableList.class);

    /** The class to manage to hold date. */
    private Class<?> dataClass;

    /**
     * Private CoOnstructor.
     * 
     * @param dataClass the class to manage
     */
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
