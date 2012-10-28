package org.jrebirth.core.wave;

import org.jrebirth.core.util.ClassUtility;

/**
 * The class <strong>WaveItem</strong>.
 * 
 * Wave item is used to identify an object into a wave.
 * 
 * @author Sébastien Bordes
 * 
 * @param <T> the type of the object mapped by this WaveItem
 */
public class WaveItem<T> {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the wave item. */
    private final int uid;

    /** The type of the related object registered by this wave item. */
    private final Class<T> itemClass;

    /**
     * Default Constructor.
     */
    public WaveItem() {
        // Ensure that the uid will be unique at runtime
        synchronized (WaveItem.class) {
            this.uid = ++idGenerator;
        }

        // FIXME
        this.itemClass = (Class<T>) ClassUtility.getGenericType(0);
    }

    /**
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * @return Returns the itemClass.
     */
    public Class<T> getItemClass() {
        return this.itemClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object waveItem) {
        return waveItem instanceof WaveItem && getUid() == ((WaveItem<?>) waveItem).getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUid();
    }

}
