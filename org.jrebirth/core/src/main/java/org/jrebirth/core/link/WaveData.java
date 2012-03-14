package org.jrebirth.core.link;

/**
 * 
 * The class <strong>WaveData</strong>.
 * 
 * Used to contain a value and its unique name.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 36 $ $Author: sbordes $
 * @since $Date: 2011-10-10 23:17:57 +0200 (Mon, 10 Oct 2011) $
 */
public final class WaveData implements Comparable<WaveData> {

    /**
     * The key property, must be set with an enumeration that implements WaveItem.
     */
    private WaveItem waveItem;

    /** The value data. */
    private Object value;

    /** The field used for sorting. */
    private int order;

    /**
     * Default Constructor.
     * 
     * @param waveItem the enumeration used as key property
     * @param value the data
     */
    public WaveData(final WaveItem waveItem, final Object value) {
        setKey(waveItem);
        setValue(value);
    }

    /**
     * @return Returns the waveItem.
     */
    public WaveItem getKey() {
        return this.waveItem;
    }

    /**
     * @param waveItem the waveItem to set
     */
    public void setKey(final WaveItem waveItem) {
        this.waveItem = waveItem;
    }

    /**
     * @return Returns the value.
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(final Object value) {
        this.value = value;
    }

    /**
     * @return Returns the order.
     */
    public int getOrder() {
        return this.order;
    }

    /**
     * @param order The order to set.
     */
    public void setOrder(final int order) {
        this.order = order;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getOrder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        // A wave data is unique into a wave, no equals needed
        return obj instanceof WaveData && getOrder() == ((WaveData) obj).getOrder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final WaveData waveData) {
        return getOrder() - waveData.getOrder();
    }
}
