/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.core.wave;

import java.util.List;

/**
 * The interface <strong>Wave</strong>.
 * 
 * A Wave is a message which contain a type and a map of object.
 * 
 * @author Sébastien Bordes
 */
public interface Wave {

    enum Status {
        created,
        sent,
        processed,
        consumed;
    }

    /**
     * @return Returns the Wave Unique Identifier.
     */
    Status getStatus();

    /**
     * @return Returns the Wave Unique Identifier.
     */
    void setStatus(Status status);

    /**
     * @return Returns the Wave Unique Identifier.
     */
    String getWUID();

    /**
     * @return Returns the Wave timestamp (for creation).
     */
    long getTimestamp();

    /**
     * @return Returns the waveGroup.
     */
    WaveGroup getWaveGroup();

    /**
     * @param waveGroup The waveGroup to set.
     */
    void setWaveGroup(WaveGroup waveGroup);

    /**
     * @return Returns the waveType.
     */
    WaveType getWaveType();

    /**
     * @param waveType The waveType to set.
     */
    void setWaveType(WaveType waveType);

    /**
     * @return Returns the relatedClass.
     */
    Class<?> getRelatedClass();

    /**
     * @param relatedClass The relatedClass to set.
     */
    void setRelatedClass(Class<?> relatedClass);

    /**
     * @return Returns the priority.
     */
    int getPriority();

    /**
     * @param priority The priority to set.
     */
    void setPriority(int priority);

    /**
     * @return Returns the nextWave.
     */
    Wave getNextWave();

    /**
     * @param nextWave The nextWave to set.
     */
    void setNextWave(Wave nextWave);

    /**
     * Return all wave items sorted by order.
     * 
     * @return Returns the the wave items.
     */
    List<WaveData<?>> getWaveItems();

    /**
     * Add a wave data. Store an object and indexize it.
     * 
     * @param waveData the object to store
     * 
     * @param <T> the type of the wave data to add
     */
    <T> void addData(WaveData<T> waveData);

    /**
     * Add a list of wave data. Store objects and indexize them.
     * 
     * @param waveDatas the list of wave data to store
     */
    void addDatas(WaveData<?>[] waveDatas);

    /**
     * Add a wave data. Store an object and indexize it.
     * 
     * @param waveItem the wave item used as hashkey
     * @param value the data to store
     * 
     * @param <T> the type of the wave data to add
     */
    <T> void add(final WaveItem<T> waveItem, final T value);

    /**
     * Retrieve a wave data object.
     * 
     * @param waveItem the waveItem of the object to retrieve
     * 
     * @return the waveData registered by the key
     * 
     * @param <T> the type of the wave data to add
     */
    <T> WaveData<T> getData(WaveItem<T> waveItem);

    /**
     * Retrieve a value.
     * 
     * @param waveItem waveItem of the object to retrieve
     * 
     * @return the data registered by the key
     * 
     * @param <T> the type of the wave data to add
     */
    <T> T get(WaveItem<T> waveItem);

    /**
     * Check if an object exists.
     * 
     * @param waveItem the waveItem of the object to check
     * 
     * @return true if the waveData is registered
     */
    boolean contains(WaveItem<?> waveItem);

    /**
     * Return the wave bean used to wrap wave properties.
     * 
     * @return the wave bean, could be null
     */
    WaveBean getWaveBean();

    /**
     * TODO To complete.
     * 
     * @param waveListener
     */
    void addWaveListener(WaveListener waveListener);
}
