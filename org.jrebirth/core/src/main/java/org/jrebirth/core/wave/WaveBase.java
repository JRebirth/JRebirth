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
package org.jrebirth.core.wave;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The class <strong>WaveBase</strong>.
 * 
 * This Bean is used to move wave's data through layer. It allow to manage priorities.
 */
public class WaveBase implements Wave {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(WaveBase.class);

    /** The space separator. */
    private static final String SPACE_SEP = " ";

    /** The Wave Unique Identifier. */
    private final String wuid;

    /** The Wave timestamp. */
    private final long timestamp;

    /** The wave status. */
    private Status status = Status.Created;

    /** The group of the wave used to dispatch the right event. */
    private WaveGroup waveGroup = WaveGroup.UNDEFINED;

    /**
     * The type of the wave used to call the right method name of the receiver object.
     */
    private WaveType waveType;

    /** The related class to used for create waves. */
    private Class<?> relatedClass;

    /** The priority used to process wave according to a custom order. */
    private int priority;

    /** The related wave to the current wave, cold be a parent wave or child wave according context. */
    private Wave relatedWave;

    /** A map used to contain all data. */
    private final Map<WaveItem<?>, WaveData<?>> waveItemsMap = new HashMap<>();

    /** A sorted list that contains all data. */
    private final List<WaveData<?>> waveDataList = new ArrayList<>();

    /**
     * The wave bean.
     */
    private WaveBean waveBean;

    /** The type extending WaveBean to use to embed some values. */
    private Class<? extends WaveBean> waveBeanClass;

    /**
     * The list of wave Listener to warn when wave status changed.
     */
    private final List<WaveListener> waveListeners = Collections.synchronizedList(new ArrayList<WaveListener>());

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
    public Wave getRelatedWave() {
        return this.relatedWave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelatedWave(final Wave nextWave) {
        this.relatedWave = nextWave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WaveData<?>> getWaveItems() {
        return this.waveDataList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Object> void addData(final WaveData<T> waveData) {

        // Init the order of the wave Data
        waveData.setOrder(getWaveItems().size());
        // Store into the map to allow access by WaveItem
        this.waveItemsMap.put(waveData.getKey(), waveData);
        // Ad into the list to enable sorting
        this.waveDataList.add(waveData);

        // Sort the list
        Collections.sort(this.waveDataList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDatas(final WaveData<?>[] waveDatas) {

        for (final WaveData<?> waveData : waveDatas) {
            // Init the order of the wave Data
            waveData.setOrder(getWaveItems().size());
            // Store into the map to allow access by WaveItem
            this.waveItemsMap.put(waveData.getKey(), waveData);
            // Ad into the list to enable sorting
            this.waveDataList.add(waveData);

            // Sort the list
            Collections.sort(this.waveDataList);
        }
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
        return (T) (this.waveItemsMap.containsKey(waveItem) ? this.waveItemsMap.get(waveItem).getValue() : null);
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
    @Override
    public WaveBean getWaveBean() {
        if (this.waveBean == null) {
            if (WaveBean.class.equals(this.waveBeanClass)) {
                // Build an empty wave bean to avoid null pointer exception
                this.waveBean = new DefaultWaveBean();
            } else {
                try {
                    this.waveBean = this.waveBeanClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    LOGGER.error("Impossible to build WaveBean instance : " + this.waveBeanClass.toString(), e);
                } finally {
                    if (this.waveBean == null) {
                        this.waveBean = new DefaultWaveBean();
                    }
                }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void addWaveListener(final WaveListener waveListener) {
        this.waveListeners.add(waveListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeWaveListener(final WaveListener waveListener) {
        this.waveListeners.remove(waveListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(final Status status) {
        synchronized (status) {
            this.status = status;
            JRebirth.runIntoJIT(new AbstractJrbRunnable("Set Wave Status") {

                @Override
                protected void runInto() throws JRebirthThreadException {
                    fireStatusChanged();
                }
            });

        }
    }

    /**
     * Fire a wave status change.
     */
    private void fireStatusChanged() {
        // System.out.println("fireStatusChanged " + this.status.toString());

        for (final WaveListener waveListener : this.waveListeners) {

            switch (this.status) {

                case Created:
                    waveListener.waveCreated(this);
                    break;
                case Sent:
                    waveListener.waveSent(this);
                    break;
                case Processing:
                    waveListener.waveProcessed(this);
                    break;
                case Consumed:
                    waveListener.waveConsumed(this);
                    break;
                case Failed:
                    waveListener.waveFailed(this);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        if (getWaveGroup() != null) {
            sb.append(getWaveGroup()).append(SPACE_SEP);
        }
        if (getRelatedClass() != null) {
            sb.append(getRelatedClass().getSimpleName()).append(SPACE_SEP);
        }
        if (getWaveType() != null) {
            sb.append(getWaveType()).append(SPACE_SEP);
        }

        if (getWUID() != null) {
            sb.append("(").append(getWUID()).append(") ");
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void linkWaveBean(final WaveBean waveBean) {
        if (waveBean != null) {
            this.waveBean = waveBean;
            this.waveBeanClass = waveBean.getClass();
        }
    }

}
