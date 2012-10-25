package org.jrebirth.core.wave;

import org.jrebirth.core.util.ClassUtility;

/**
 * The class <strong>WaveItem</strong>.
 * 
 * Wave item is used to identify an object into a wave.
 * 
 * @author Sébastien Bordes
 * 
 */
public class WaveItem<T> {

    private static int idGenerator;

    private final int uid;

    private final Class<T> itemClass;

    public WaveItem() {
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
        return waveItem != null && waveItem instanceof WaveItem && getUid() == ((WaveItem<?>) waveItem).getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUid();
    }

}
