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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * The class <strong>WaveImpl</strong>.
 * 
 * This Bean is used to move wave's data through layer. It allow to manage priorities.
 * 
 * @param <B> The wave bean class used
 */
public class WaveBase implements Wave {

    /** The Wave Unique Identifier. */
    private final String wuid;

    /** The Wave timestamp. */
    private final long timestamp;

    /** The group of the wave used to dispatch the right event. */
    private WaveGroup waveGroup;

    /**
     * The type of the wave used to call the right method name of the receiver object.
     */
    private WaveType waveType;

    /** The related class to used for create waves. */
    private Class<?> relatedClass;

    /** The priority used to process wave according to a custom order. */
    private int priority;

    /** The next wave to process after this one, used to chain waves. */
    private Wave nextWave;

    /** A map used to contain all data. */
    private final Map<WaveItem, WaveData<?>> waveItemsMap = new HashMap<>();

    /** A sorted list that contains all data. */
    private final List<WaveData<?>> waveItemsList = new ArrayList<>();

    /**
     * The wave bean.
     */
    private WaveBean waveBean;

    /** The type extending WaveBean to use to embed some values. */
    private Class<? extends WaveBean> waveBeanClass;

    /**
     * Default Constructor.
     */
    public WaveBase() {
        super();
        // Generate a random but unique identifier
        this.wuid = UUID.randomUUID().toString();
        // Store the creation date
        this.timestamp = Calendar.getInstance().getTimeInMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveGroup getWaveGroup() {
        return this.waveGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWaveGroup(final WaveGroup waveGroup) {
        this.waveGroup = waveGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType getWaveType() {
        return this.waveType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWaveType(final WaveType waveType) {
        this.waveType = waveType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getRelatedClass() {
        return this.relatedClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelatedClass(final Class<?> relatedClass) {
        this.relatedClass = relatedClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPriority() {
        return this.priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPriority(final int priority) {
        this.priority = priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave getNextWave() {
        return this.nextWave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextWave(final Wave nextWave) {
        this.nextWave = nextWave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WaveData<?>> getWaveItems() {
        return this.waveItemsList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void addData(final WaveData<T> waveData) {
        // Init the order of the wave Data
        waveData.setOrder(getWaveItems().size());
        // Store into the map to allow access by WaveItem
        this.waveItemsMap.put(waveData.getKey(), waveData);
        // Ad into the list to enable sorting
        this.waveItemsList.add(waveData);
        // Sort the list
        Collections.sort(this.waveItemsList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void add(final WaveItem<T> waveItem, final T value) {
        final WaveData<T> waveData = WaveData.build(waveItem, value);
        addData(waveData);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> WaveData<T> getData(final WaveItem<T> waveItem) {
        return (WaveData<T>) this.waveItemsMap.get(waveItem);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(final WaveItem<T> waveItem) {
        return (T) this.waveItemsMap.get(waveItem).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final WaveItem<?> waveItem) {
        return this.waveItemsMap.containsKey(waveItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWUID() {
        return this.wuid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public WaveBean getWaveBean() {
        if (this.waveBean == null) {
            if (!WaveBean.class.equals(this.waveBeanClass)) {
                try {
                    this.waveBean = this.waveBeanClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    // throw new CoreRuntimeException("Impossible to build Wave Bean instance", e);
                } finally {
                    if (this.waveBean == null) {
                        this.waveBean = new DefaultWaveBean();
                    }
                }
            } else {
                // Build an empty wave bean to avoid null pointer exception
                this.waveBean = new DefaultWaveBean();
            }
        }

        return this.waveBean;
    }

    /**
     * @return Returns the waveBeanClass.
     */
    public Class<? extends WaveBean> getWaveBeanClass() {
        return this.waveBeanClass;
    }

    /**
     * @param waveBeanClass The waveBeanClass to set.
     */
    public void setWaveBeanClass(final Class<? extends WaveBean> waveBeanClass) {
        this.waveBeanClass = waveBeanClass;
    }

}
