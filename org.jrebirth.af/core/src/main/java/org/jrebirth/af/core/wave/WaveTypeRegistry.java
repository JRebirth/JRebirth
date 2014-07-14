package org.jrebirth.af.core.wave;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WaveTypeRegistry {

    /** The generator of unique id. */
    public static AtomicInteger idGenerator = new AtomicInteger();

    /** Map that store WaveType used according to their unique identifier. */
    public static Map<String, WaveType> waveTypeMap = Collections.synchronizedMap(new HashMap<String, WaveType>());

    /**
     * .
     *
     * @return
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

    public static void store(final String action, final WaveType waveType) {

        // it uses action without any prefix
        if (!waveTypeMap.containsKey(action)) {
            waveTypeMap.put(action, waveType);
        }

    }

}
