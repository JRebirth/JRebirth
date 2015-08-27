/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.jrebirth.af.api.wave.contract.WaveType;

/**
 * The Class WaveTypeRegistry used to store all {@link WaveType}.
 *
 * @author Sébastien Bordes
 */
public final class WaveTypeRegistry {

    /** The generator of unique id. */
    public static final AtomicInteger idGenerator = new AtomicInteger();

    /** Map that store WaveType used according to their unique identifier. */
    public static final Map<String, WaveType> waveTypeMap = Collections.synchronizedMap(new HashMap<String, WaveType>());

    /**
     * Private Constructor.
     */
    private WaveTypeRegistry() {
        // Nothing to do
    }

    /**
     * Return the next free unique identifier.
     *
     * @return the next uid
     */
    public static int getNextUid() {
        return idGenerator.incrementAndGet();
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
     * Store a {@link WaveType} by its action name.
     *
     * @param action the action
     * @param waveType the wave type
     */
    public static void store(final String action, final WaveType waveType) {

        // it uses action without any prefix
        if (!waveTypeMap.containsKey(action)) {
            waveTypeMap.put(action, waveType);
        }

    }

}
