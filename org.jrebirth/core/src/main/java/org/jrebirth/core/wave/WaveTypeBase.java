package org.jrebirth.core.wave;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <strong>WaveTypeBase</strong>.
 * 
 * @author Sébastien Bordes
 */
public class WaveTypeBase implements WaveType {

    private static int idGenerator;

    private final String action;

    private final List<WaveItem<?>> waveItemList = new ArrayList<>();

    private final int uid;

    public WaveTypeBase(final String action, final WaveItem<?>... waveItems) {
        synchronized (WaveItem.class) {
            this.uid = ++idGenerator;
        }

        this.action = action;
        for (final WaveItem<?> waveItem : waveItems) {
            this.waveItemList.add(waveItem);
        }
    }

    /**
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
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
        return waveType != null && waveType instanceof WaveTypeBase && getUid() == ((WaveTypeBase) waveType).getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUid();
    }

}
