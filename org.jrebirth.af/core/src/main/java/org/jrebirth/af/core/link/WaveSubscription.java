package org.jrebirth.af.core.link;

import java.util.List;

import org.jrebirth.af.api.wave.WaveType;

/**
 * The class <strong>WaveSubscription</strong> is used to store all wave handler related to a wave type.
 *
 * @author Sébastien Bordes
 */
public class WaveSubscription {

    /** The listened wave type. */
    private final WaveType waveType;

    /** The list of wev handler to call when this wave type is received. */
    private final List<WaveHandler> waveHandlers;

    /**
     * Default Constructor.
     *
     * @param waveType the listened wave type
     * @param waveHandlers the list of handler attached to this wave type
     */
    public WaveSubscription(final WaveType waveType, final List<WaveHandler> waveHandlers) {
        this.waveType = waveType;
        this.waveHandlers = waveHandlers;
    }

    /**
     * @return Returns the waveType.
     */
    public WaveType getWaveType() {
        return this.waveType;
    }

    /**
     * @return Returns the waveHandlers.
     */
    public List<WaveHandler> getWaveHandlers() {
        return this.waveHandlers;
    }
}
