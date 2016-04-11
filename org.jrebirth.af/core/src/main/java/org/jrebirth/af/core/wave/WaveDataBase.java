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

import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveItem;

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
    public WaveDataBase(final WaveItem<T> waveItem, final T value) {
        key(waveItem);
        value(value);
    }

    /**
     * @return Returns the waveItem.
     */
    @Override
    public WaveItem<T> key() {
        return this.waveItem;
    }

    /**
     * @param waveItem the waveItem to set
     */
    @Override
    public void key(final WaveItem<T> waveItem) {
        this.waveItem = waveItem;
    }

    /**
     * @return Returns the value.
     */
    @Override
    public T value() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    @Override
    public void value(final T value) {
        this.value = value;
    }

    /**
     * @return Returns the order.
     */
    @Override
    public int order() {
        return this.order;
    }

    /**
     * @param order The order to set.
     */
    @Override
    public void order(final int order) {
        this.order = order;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return order();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        // A wave data is unique into a wave, no equals needed
        return obj instanceof WaveData && order() == ((WaveData<T>) obj).order();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final WaveData<?> waveData) {
        return order() - waveData.order();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Item: ")
          .append(key().toString())
          .append(" Value:")
          .append(value().toString());

        return sb.toString();
    }

}
