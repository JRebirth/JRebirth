/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.wave;

import org.jrebirth.af.api.wave.WaveData;
import org.jrebirth.af.api.wave.WaveItem;

/**
 *
 * The class <strong>WaveData</strong>.
 *
 * Used to contain a value and its unique name.
 *
 * @author Sébastien Bordes
 *
 * @param <T> the type of the data hold
 */
public final class WaveDataBase<T> implements WaveData<T> {

    /**
     * The key property, must be set with an enumeration that implements WaveItem.
     */
    private WaveItem<T> waveItem;

    /** The value data. */
    private T value;

    /** The field used for sorting. */
    private int order;

    /**
     * Default Constructor.
     *
     * @param waveItem the enumeration used as key property
     * @param value the data
     */
    private WaveDataBase(final WaveItem<T> waveItem, final T value) {
        setKey(waveItem);
        setValue(value);
    }

    /**
     * Build a wave data.
     *
     * @param waveItem the wave item used as the key into the wave
     * @param value the data hold by he wave data wrapper
     *
     * @return a new fresh wave Data object
     *
     * @param <T> the type of the object wrapped by this WaveData
     */
    public static <T extends Object> WaveDataBase<T> build(final WaveItem<T> waveItem, final T value) {
        return new WaveDataBase<>(waveItem, value);
    }

    /**
     * @return Returns the waveItem.
     */
    public WaveItem<T> getKey() {
        return this.waveItem;
    }

    /**
     * @param waveItem the waveItem to set
     */
    public void setKey(final WaveItem<T> waveItem) {
        this.waveItem = waveItem;
    }

    /**
     * @return Returns the value.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(final T value) {
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
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        // A wave data is unique into a wave, no equals needed
        return obj instanceof WaveDataBase && getOrder() == ((WaveDataBase<T>) obj).getOrder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final WaveData<?> waveData) {
        return getOrder() - waveData.getOrder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Item: ").append(getKey().toString());
        sb.append(" Value:").append(getValue().toString());
        return sb.toString();
    }

}
