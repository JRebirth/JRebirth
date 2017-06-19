package org.jrebirth.af.core.service;

import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;

/**
 * The class <strong>DefaultService</strong> is a basic empty implementation of {@link Service} interface.
 * 
 * It's used as an adapter providing all methods in order to override only required ones.
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
