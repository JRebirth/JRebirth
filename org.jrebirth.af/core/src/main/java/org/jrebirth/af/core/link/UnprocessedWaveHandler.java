package org.jrebirth.af.core.link;

import org.jrebirth.af.core.wave.Wave;

/**
 * The class <strong>UnprocessedWaveHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public interface UnprocessedWaveHandler {

    /**
     * Manage unprocessed Wave.
     *
     * @param wave the wave that wasn't be processed for any reason
     */
    void manageUnprocessedWave(final Wave wave);

    /**
     * Manage unprocessed Wave.
     *
     * @param contextExplanation a string that explains the context
     * @param wave the wave that wasn't be processed for any reason
     */
    void manageUnprocessedWave(final String contextExplanation, final Wave wave);

}
