/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.api.wave.contract;

/**
 * The interface <strong>WaveData</strong> used to contain a value and its unique name.
 *
 * @author Sébastien Bordes
 *
 * @param <T> the type of the data hold
 */
public interface WaveData<T> extends Comparable<WaveData<?>> {

    /**
     * @return Returns the waveItem.
     */
    WaveItem<T> getKey();

    /**
     * @param waveItem the waveItem to set
     */
    void setKey(final WaveItem<T> waveItem);

    /**
     * @return Returns the value.
     */
    T getValue();

    /**
     * @param value the value to set
     */
    void setValue(final T value);

    /**
     * @return Returns the order.
     */
    int getOrder();

    /**
     * @param order The order to set.
     */
    void setOrder(final int order);

}
