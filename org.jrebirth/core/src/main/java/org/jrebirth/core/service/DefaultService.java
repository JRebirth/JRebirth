package org.jrebirth.core.service;

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
    protected void initService() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Must be overridden to manage action handling by service
    }

}
