package org.jrebirth.core.wave;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <strong>WaveTypeBase</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class WaveTypeBase implements WaveType {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The action to performed, basically the name of the method to call. */
    private final String action;

    /** Define arguments types to use. */
    private final List<WaveItem<?>> waveItemList = new ArrayList<>();

    /** The unique identifier of the wave type. */
    private int uid;

    /**
     * Default constructor.
     * 
     * @param action The action to performed
     * @param waveItems the list of #WaveItem required by this wave
     */
    private WaveTypeBase(final String action, final WaveItem<?>... waveItems) {

        this.action = action;
        for (final WaveItem<?> waveItem : waveItems) {
            this.waveItemList.add(waveItem);
        }
    }

    /**
     * Build a wave type.
     * 
     * @param action The action to performed
     * @param waveItems the list of #WaveItem required by this wave
     * 
     * @return a new fresh wave type object
     */
    public static WaveTypeBase build(final String action, final WaveItem<?>... waveItems) {
        final WaveTypeBase waveType = new WaveTypeBase(action, waveItems);

        // Ensure that the uid will be unique at runtime
        synchronized (WaveTypeBase.class) {
            waveType.setUid(++idGenerator);
        }
        return waveType;
    }

    /**
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * @param uid The uid to set.
     */
    public void setUid(final int uid) {
        this.uid = uid;
    }

    /**
     * @return Returns the action.
     */
    public String getAction() {
        return this.action;
    }

    /**
     * @return Returns the waveItemList.
     */
    public List<WaveItem<?>> getWaveItemList() {
        return this.waveItemList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object waveType) {
        return waveType instanceof WaveTypeBase && getUid() == ((WaveTypeBase) waveType).getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUid();
    }

}
