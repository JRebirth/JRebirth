/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.api.wave;

import java.util.List;

import javafx.beans.property.ObjectProperty;

import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;

/**
 * The interface <strong>Wave</strong>.
 *
 * A Wave is a message which contain a type and a map of object.
 *
 * @author Sébastien Bordes
 */
public interface Wave {

    /**
     * The enumeration <strong>Status</strong>.
     *
     * @author Sébastien Bordes
     */
    enum Status {

        /** The wave has just been created, it's the default status. */
        Created,

        /** The wave has just been sent. */
        Sent,

        /** The wave is being processing. */
        Processing,

        /** The wave has just been cancelled. */
        Cancelled,

        /** The wave is being consumed. */
        Consumed,

        /** The wave has been performed by all handlers. */
        Handled,

        /** The wave processing has failed. */
        Failed,

        /** The wave has just been destroyed. */
        Destroyed;
    }

    /**
     * @return Returns the Wave Status.
     */
    Status status();

    /**
     * @param status The status to set.
     *
     * @return the current Wave to chain other method call
     */
    Wave status(final Status status);

    /**
     * Return the status property to allow binding.
     *
     * @return the status property
     */
    ObjectProperty<Status> statusProperty();

    /**
     * @return Returns the Wave Unique Identifier.
     */
    String wUID();

    /**
     * @return Returns the Wave timestamp (for creation).
     */
    long timestamp();

    /**
     * @return Returns the waveGroup.
     */
    WaveGroup waveGroup();

    /**
     * @param waveGroup The waveGroup to set.
     *
     * @return the current Wave to chain other method call
     */
    Wave waveGroup(final WaveGroup waveGroup);

    /**
     * @return Returns the waveType.
     */
    WaveType waveType();

    /**
     * @param waveType The waveType to set.
     *
     * @return the current Wave to chain other method call
     */
    Wave waveType(final WaveType waveType);

    /**
     * @return Returns the fromClass.
     */
    Class<?> fromClass();

    /**
     * @param fromClass The fromClass to set.
     *
     * @return the current Wave to chain other method call
     */
    Wave fromClass(final Class<?> fromClass);

    /**
     * @return Returns the class of the component that will handle this wave.
     */
    Class<?> componentClass();

    /**
     * @param componentClass the class of the component that will handle this wave.
     *
     * @return the current Wave to chain other method call
     */
    Wave componentClass(final Class<?> componentClass);

    /**
     * @return Returns the priority.
     */
    int priority();

    /**
     * @param priority The priority to set.
     *
     * @return the current Wave to chain other method call
     */
    Wave priority(final int priority);

    /**
     * Returns the wave considered as ancestor of the current wave.
     * 
     * @return Returns the relatedWave.
     */
    Wave relatedWave();

    /**
     * @param relatedWave The related Wave to set.
     *
     * @return the current Wave to chain other method call
     */
    Wave relatedWave(final Wave relatedWave);

    /**
     * Returns true if this wave is stored as related wave into another wave.
     * 
     * @return true if is related
     */
    boolean isRelated();

    /**
     * Set the flag indicating if this wave is a related wave.
     * 
     * @param isRelated the related flag to set
     * 
     * @return the current Wave to chain other method call
     */
    Wave isRelated(boolean isRelated);

    /**
     * Return all wave items sorted by order.
     *
     * @return Returns the the wave items.
     */
    List<WaveData<?>> waveDatas();

    /**
     * Add a wave data. Store an object and indexize it.
     *
     * @param waveItem the wave item used as hashkey
     * @param value the data to store
     *
     * @param <T> the type of the wave data to add
     *
     * @return the current Wave to chain other method call
     */
    <T> Wave add(final WaveItem<T> waveItem, final T value);

    /**
     * Retrieve a value.
     *
     * @param waveItem waveItem of the object to retrieve
     *
     * @return the data registered by the key
     *
     * @param <T> the type of the wave data to add
     */
    <T> T get(final WaveItem<T> waveItem);

    /**
     * Retrieve a wave data object.
     *
     * @param waveItem the waveItem of the object to retrieve
     *
     * @return the waveData registered by the key
     *
     * @param <T> the type of the wave data to add
     */
    <T> WaveData<T> getData(final WaveItem<T> waveItem);

    /**
     * Add a list of wave data. Store objects and indexize them.
     *
     * @param waveDatas the list of wave data to store
     *
     * @return the current Wave to chain other method call
     */
    Wave addDatas(final WaveData<?>... waveDatas);

    /**
     * Check if an object exists.
     *
     * @param waveItem the waveItem of the object to check
     *
     * @return true if the waveData is registered
     */
    boolean contains(final WaveItem<?> waveItem);

    /**
     * Check if an object exists and if it isn't null.
     *
     * @param waveItem the waveItem of the object to check
     *
     * @return true if the waveData is registered and the value is not null
     */
    boolean containsNotNull(final WaveItem<?> waveItem);

    /**
     * Return the wave bean used to wrap wave properties.
     *
     * @param waveBeanClass the {@link WaveBean} subtype to create
     * @param <WB> the {@link WaveBean} type used to chain methods call
     *
     * @return the wave bean, could be null
     */
    <WB extends WaveBean> WB waveBean(Class<WB> waveBeanClass);

    /**
     * Link a wave bean.
     *
     * @param waveBean the wave bean already built to link
     *
     * @return the current Wave to chain other method call
     */
    Wave waveBean(final WaveBean waveBean);

    /**
     * Return all wave bean carried by this wave.
     *
     * @return the list of wave bean
     */
    List<WaveBean> waveBeanList();

    /**
     * Add several WaveBean in a row.
     *
     * @param waveBeanList the list of WaveBean to add
     *
     * @return the current Wave to chain other method call
     */
    Wave waveBeanList(List<WaveBean> waveBeanList);

    /**
     * Add a wave listener.
     *
     * @param waveListener the wave listener that will be notified of wave status
     *
     * @return the current Wave to chain other method call
     */
    Wave addWaveListener(final WaveListener waveListener);

    /**
     * Remove a wave listener.
     *
     * @param waveListener the wave listener to removed
     *
     * @return the current Wave to chain other method call
     */
    Wave removeWaveListener(final WaveListener waveListener);

    /**
     * Store all Wave Handler.
     *
     * @param waveHandlers the list of WaveHandler
     */
    void setWaveHandlers(final List<? extends Object> waveHandlers);

    /**
     * Release the given WaveHandler.
     *
     * @param waveHandler the waveHandler
     */
    void removeWaveHandler(final Object waveHandler);

    /**
     * Returns true if the wave holds the given {@link WaveBean} class.
     *
     * @param waveBeanClass the type of the searched {@link WaveBean}
     *
     * @return true if the wave holds the given {@link WaveBean} type
     */
    boolean hasWaveBean(Class<? extends WaveBean> waveBeanClass);

}
