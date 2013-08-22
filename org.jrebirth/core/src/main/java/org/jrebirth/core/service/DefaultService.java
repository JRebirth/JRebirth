package org.jrebirth.core.service;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>DefaultService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class DefaultService extends AbstractService {

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Initialize Service with private method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Must be overridden to manage action handling by service
    }

}
