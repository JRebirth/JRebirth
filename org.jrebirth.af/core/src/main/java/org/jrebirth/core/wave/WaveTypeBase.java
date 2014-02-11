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

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrebirth.core.resource.provided.JRebirthParameters;
import org.jrebirth.core.util.ObjectUtility;

/**
 * The class <strong>WaveTypeBase</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class WaveTypeBase implements WaveType {

    /** The generator of unique id. */
    private static int idGenerator;

    /** Map that store WaveType used according to their unique identifier. */
    private static Map<String, WaveType> waveTypeMap = Collections.synchronizedMap(new HashMap<String, WaveType>());

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
     * @param action The action to perform, "DO_" (by default see {@link JRebirthParameters.WAVE_HANDLER_PREFIX}) keyword will be prepended to the action name to generate the handler method
     * 
     * @param waveItems the list of #WaveItem{@link WaveItem} required by this wave
     */
    private WaveTypeBase(final String action, final WaveItem<?>... waveItems) {

        // The action name will be used to define the name of the wave handler method
        // Prepend do the the action name to force wave handler method to begin with do (convention parameterizable)
        this.action = JRebirthParameters.WAVE_HANDLER_PREFIX.get() + action;

        // Add each wave item to manage method argument
        for (final WaveItem<?> waveItem : waveItems) {
            this.waveItemList.add(waveItem);
        }
    }

    /**
     * Build a wave type.
     * 
     * @param action The action to perform, "DO_" keyword (by default see {@link JRebirthParameters.WAVE_HANDLER_PREFIX}) will be prepended to the action name to generate the handler method
     * 
     * @param waveItems the list of {@link WaveItem} required by this wave
     * 
     * @return a new fresh wave type object
     */
    public static WaveTypeBase build(final String action, final WaveItem<?>... waveItems) {
        final WaveTypeBase waveType = new WaveTypeBase(action, waveItems);

        // Ensure that the uid will be unique at runtime
        synchronized (WaveTypeBase.class) {
            waveType.setUid(++idGenerator);
        }

        // it uses action without any prefix
        if (!waveTypeMap.containsKey(action)) {
            waveTypeMap.put(action, waveType);
        }

        return waveType;
    }

    /**
     * Retrieve a WaveType according to its unique action name.
     * 
     * Be careful it could return null if the {@link WaveType} has not been initialized yet.
     * 
     * @param action the unique action name used to register the WaveType
     * 
     * @return the WaveType found into registry or null
     */
    public static WaveType getWaveType(final String action) {

        WaveType waveType = null;
        if (waveTypeMap.containsKey(action)) {
            waveType = waveTypeMap.get(action);
        }
        return waveType;
    }

    /**
     * Build a wave type.
     * 
     * @param action The action to perform "DO_" (by default see {@link JRebirthParameters.WAVE_HANDLER_PREFIX}) keyword will be prepended to the action name to generate the handler method
     * @param returnWaveType the return wave Type to call after having processing the current
     * @param waveItems the list of {@link WaveItem} required by this wave
     * 
     * @return a new fresh wave type object
     */
    public static WaveTypeBase build(final String action, final WaveType returnWaveType, final WaveItem<?>... waveItems) {
        final WaveTypeBase waveType = build(action, waveItems);
        waveType.setReturnWaveType(waveType);
        return waveType;
    }

    /**
     * Gets the uid.
     * 
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     * 
     * @param uid The uid to set.
     */
    public void setUid(final int uid) {
        this.uid = uid;
    }

    /**
     * Gets the action.
     * 
     * @return Returns the action.
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Gets the wave item list.
     * 
     * @return Returns the waveItemList.
     */
    public List<WaveItem<?>> getWaveItemList() {
        return this.waveItemList;
    }

    /**
     * Return the required method parameter list to handle this WaveType.
     * 
     * @return the parameter list (Type1 arg1, Type2 arg2 ...)
     */
    public String getItems() {
        final StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (final WaveItem<?> waveItem : getWaveItemList()) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            String fullName = waveItem.getItemType() instanceof ParameterizedType ? ((ParameterizedType) waveItem.getItemType()).toString()
                    : ((Class<?>) waveItem.getItemType()).getName();
            sb.append(fullName).append(" ");

            fullName = fullName.replaceAll("[<>]", "");
            if (waveItem.getName() == null || waveItem.getName().isEmpty()) {
                sb.append(ObjectUtility.lowerFirstChar(fullName.substring(fullName.lastIndexOf('.') + 1)));
            } else {
                sb.append(waveItem.getName());
            }
        }
        return sb.toString();
    }

    /**
     * Gets the return wave type.
     * 
     * @return the return wave type
     */
    public WaveType getReturnWaveType() {
        return this.returnWaveType;
    }

    /**
     * Sets the return wave type.
     * 
     * @param returnWaveType the new return wave type
     */
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
