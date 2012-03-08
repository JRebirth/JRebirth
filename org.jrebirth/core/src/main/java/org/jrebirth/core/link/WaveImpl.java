package org.jrebirth.core.link;

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
 * @author Sébastien Bordes
 * @version $Revision$ $Date$ $Name$
 * 
 * @since org.jrebirth.core 1.0
 */
public class WaveImpl implements Wave {

    /** The Wave Unique Identifier. */
    private final String wuid;

    /** The Wave timestamp. */
    private final long timestamp;

    /** The group of the wave used to dispatch the right event. */
    private WaveGroup waveGroup;

    /** The type of the wave used to call the right method name of the receiver object. */
    private WaveType waveType;

    /** The related class to used for create waves. */
    private Class<?> relatedClass;

    /** The priority used to process wave according to a custom order. */
    private int priority;

    /** A map used to contain all data. */
    private final Map<WaveItem, WaveData> waveItemsMap = new HashMap<>();

    /** A sortered list that contains all data. */
    private final List<WaveData> waveItemsList = new ArrayList<>();

    /**
     * Default Constructor.
     */
    public WaveImpl() {
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
    public List<WaveData> getWaveItems() {
        return this.waveItemsList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final WaveItem waveItem, final WaveData waveData) {
        // Init the order of the wave Data
        waveData.setOrder(getWaveItems().size());
        // Store intot the map to allow access by WaveItem
        this.waveItemsMap.put(waveItem, waveData);
        // Ad into the list to enbale sorting
        this.waveItemsList.add(waveData);
        // Sort the list
        Collections.sort(this.waveItemsList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveData get(final WaveItem waveItem) {
        return this.waveItemsMap.get(waveItem);
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

}
