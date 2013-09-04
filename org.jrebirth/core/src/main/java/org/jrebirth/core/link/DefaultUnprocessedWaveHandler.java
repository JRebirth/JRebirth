package org.jrebirth.core.link;

import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>DefaultUnprocessedWaveHandler</strong>.
 * 
 * @author Sébastien Bordes
 */
public class DefaultUnprocessedWaveHandler implements UnprocessedWaveHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageUnprocessedWave(final Wave wave) {
        throw new CoreRuntimeException("Wave Lost : " + wave.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageUnprocessedWave(final String contextExplanation, final Wave wave) {
        throw new CoreRuntimeException(contextExplanation + " : " + wave.toString());
    }

}
