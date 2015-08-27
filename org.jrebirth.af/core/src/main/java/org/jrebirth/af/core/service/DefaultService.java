package org.jrebirth.af.core.service;

import org.jrebirth.af.api.wave.Wave;

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do
    }

}
