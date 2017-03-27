package org.jrebirth.af.api.wave;

import org.jrebirth.af.api.component.basic.Component;

public interface WaveHandler {

    /**
     * Check the wave if the wave checker is not null anf if it returns true.
     *
     * @param wave the wave to check
     *
     * @return true, if check succeeded or if wave checker is null
     */
    boolean check(Wave wave);

    /**
     * Gets the wave ready.
     *
     * @return Returns the waveReady.
     */
    Component<?> getWaveReady();

    /**
     * Handle the wave into JAT for model component or into current thread for others.
     *
     * @param wave the wave to manage
     *
     * @throws WaveException if an error occurred while processing the wave
     */
    void handle(Wave wave) throws WaveException;

}
