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
import java.util.List;

/**
 * The class <strong>WaveTypeBase</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class WaveTypeBase implements WaveType {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the wave type. */
    private int uid;

    /** The action to performed, basically the name of the method to call. */
    private final String action;

    /** The wave type of the wave returned after processing. */
    private WaveType returnWaveType;

    /** Define arguments types to use. */
    private final List<WaveItem<?>> waveItemList = new ArrayList<>();

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
     * Build a wave type.
     * 
     * @param action The action to performed
     * @param waveItems the list of #WaveItem required by this wave
     * 
     * @return a new fresh wave type object
     */
    public static WaveTypeBase build(final String action, final WaveType d, final WaveItem<?>... waveItems) {
        final WaveTypeBase waveType = build(action, waveItems);
        waveType.setReturnWaveType(d);
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

    public WaveType getReturnWaveType() {
        return this.returnWaveType;
    }

    public void setReturnWaveType(final WaveType returnWaveType) {
        this.returnWaveType = returnWaveType;
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
