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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.WaveGroup;
import org.jrebirth.af.api.wave.WaveListener;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.link.LinkMessages;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 *
 * The class <strong>WaveBase</strong>.
 *
 * This Bean is used to move wave's data through layer. It allow to manage priorities.
 */
public class WaveBase implements Wave, LinkMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(WaveBase.class);

    /** The space separator. */
    private static final String SPACE_SEP = " ";

    /** The Wave Unique Identifier. */
    private final String wuid;

    /** The Wave timestamp. */
    private final long timestamp;

    /** The wave status (can be bound). */
    private final ObjectProperty<Status> statusProperty = new SimpleObjectProperty<>(Status.Created);

    /** The group of the wave used to dispatch the right event. */
    private WaveGroup waveGroup = WaveGroup.UNDEFINED;

    /**
     * The type of the wave used to call the right method name of the receiver object.
     */
    private WaveType waveType;

    /** The from class to used for create waves. */
    private Class<?> fromClass;

    /** The related component class to used for create waves. */
    private Class<?> componentClass;

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

    /** The list of Wave Handlers used to manage the Handled status. */
    private List<? extends Object> waveHandlers;

    /**
     * Default Constructor.
     */
    WaveBase() {
        super();
        // Generate a random but unique identifier
        this.wuid = UUID.randomUUID().toString();
        // Store the creation date
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveGroup waveGroup() {
        return this.waveGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave waveGroup(final WaveGroup waveGroup) {
        this.waveGroup = waveGroup;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType waveType() {
        return this.waveType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave waveType(final WaveType waveType) {
        this.waveType = waveType;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> fromClass() {
        return this.fromClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave fromClass(final Class<?> fromClass) {
        this.fromClass = fromClass;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> componentClass() {
        return this.componentClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave componentClass(final Class<?> componentClass) {
        this.componentClass = componentClass;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int priority() {
        return this.priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave priority(final int priority) {
        this.priority = priority;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave relatedWave() {
        return this.relatedWave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave relatedWave(final Wave nextWave) {
        this.relatedWave = nextWave;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WaveData<?>> waveDatas() {
        return this.waveDataList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave addDatas(final WaveData<?>... waveDatas) {

        for (final WaveData<?> waveData : waveDatas) {

            // Init the order of the wave Data
            waveData.setOrder(waveDatas().size());

            // Grab the previous value if any
            final WaveData<?> previous = this.waveItemsMap.get(waveData.getKey());

            // Store into the map to allow access by WaveItem
            this.waveItemsMap.put(waveData.getKey(), waveData);

            // Remove the old value from the list
            if (previous != null) {
                this.waveDataList.remove(previous);
            }

            // Add into the list to enable sorting
            this.waveDataList.add(waveData);

            // Sort the list
            Collections.sort(this.waveDataList);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Wave add(final WaveItem<T> waveItem, final T value) {
        final WaveData<T> waveData = Builders.waveData(waveItem, value);
        addDatas(waveData);
        return this;
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
    public boolean containsNotNull(final WaveItem<?> waveItem) {
        return contains(waveItem) && getData(waveItem).getValue() != null;
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
    public WaveBean waveBean() {
        if (this.waveBean == null) {
            if (this.waveBeanClass == null || WaveBean.class.equals(this.waveBeanClass)) {
                // Build an empty wave bean to avoid null pointer exception
                this.waveBean = new DefaultWaveBean();
            } else {
                try {
                    this.waveBean = this.waveBeanClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    LOGGER.error(WAVE_BEAN_CREATION_ERROR, e, this.waveBeanClass.toString());
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
    public Wave addWaveListener(final WaveListener waveListener) {
        this.waveListeners.add(waveListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave removeWaveListener(final WaveListener waveListener) {
        this.waveListeners.remove(waveListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status status() {
        synchronized (this) {
            return this.statusProperty.get();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectProperty<Status> statusProperty() {
        return this.statusProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave status(final Status status) {
        synchronized (this) {
            if (this.statusProperty.get() == status) {
                throw new CoreRuntimeException("The status " + status.toString() + " has been already set for this wave " + toString());
            } else {
                this.statusProperty.set(status);
                fireStatusChanged();
            }
        }
        return this;
    }

    /**
     * Fire a wave status change.
     */
    private void fireStatusChanged() {
        // System.out.println("fireStatusChanged " + this.status.toString());

        for (final WaveListener waveListener : this.waveListeners) {

            switch (this.statusProperty.get()) {

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

        if (waveGroup() != null) {
            sb.append(waveGroup()).append(SPACE_SEP);
        }
        if (fromClass() != null) {
            sb.append("fromClass=").append(fromClass().getSimpleName()).append(SPACE_SEP);
        }
        if (componentClass() != null) {
            sb.append("relatedClass=").append(componentClass().getSimpleName()).append(SPACE_SEP);
        }
        if (waveType() != null) {
            sb.append(waveType()).append(SPACE_SEP);
        }

        if (getWUID() != null) {
            sb.append("(").append(getWUID()).append(") ");
        }

        if (waveDatas().size() > 0) {
            sb.append("\r\nData=>");
            for (final WaveData<?> wd : waveDatas()) {
                sb.append(wd.getKey()).append("=").append(wd.getValue());
            }
        }

        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wave waveBean(final WaveBean waveBean) {
        if (waveBean != null) {
            this.waveBean = waveBean;
            this.waveBeanClass = waveBean.getClass();
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWaveHandlers(List<? extends Object> waveHandlers) {
        this.waveHandlers = waveHandlers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeWaveHandler(Object waveHandler) {
        if (waveHandler != null) {
            // Remove the handler that has terminated
            this.waveHandlers.remove(waveHandler);

            // Update the status if required
            if (status() == Status.Consumed && this.waveHandlers.isEmpty()) {
                status(Status.Handled);
            }
        }

    }

}
