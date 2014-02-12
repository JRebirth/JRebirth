package org.jrebirth.af.core.resource.i18n;

import org.jrebirth.af.core.log.JRLevel;

import org.slf4j.Marker;

/**
 * The class <strong>LogMessageParams</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface LogMessageParams extends MessageParams {

    /**
     * Return the log marker.
     * 
     * @return the log marker
     */
    Marker marker();

    /**
     * Return the log level.
     * 
     * @return the log level
     */
    JRLevel level();
}
