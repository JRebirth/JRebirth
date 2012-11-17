package org.jrebirth.core.wave;

/**
 * The class <strong>WaveItem</strong>.
 * 
 * Wave item is used to identify an object into a wave.
 * 
 * @author Sébastien Bordes
 * 
 * @param <T> the type of the object mapped by this WaveItem
 */
public final class WaveItem<T> {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the wave item. */
    private int uid;

    /** The type of the related object registered by this wave item. */
    // private Class<T> itemClass;

    /**
     * Private Constructor.
     */
    private WaveItem() {
        // this.itemClass = null;// (Class<T>) ClassUtility.getGenericType(0);
    }

    /**
     * Build a wave item.
     * 
     * @return a new fresh wave item object
     * 
     * @param <T> the type of the object wrapped by this WaveData
     */
    public static <T> WaveItem<T> build() {
        final WaveItem<T> waveItem = new WaveItem<>();

        // Ensure that the uid will be unique at runtime
        synchronized (WaveItem.class) {
            waveItem.setUid(++idGenerator);
        }
        return waveItem;
    }

    /**
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * @param uid The uid to set.
     */
    private void setUid(final int uid) {
        this.uid = uid;
    }

    // /**
    // * @return Returns the itemClass.
    // */
    // public Class<T> getItemClass() {
    // return this.itemClass;
    // }

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
