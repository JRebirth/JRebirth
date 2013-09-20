package org.jrebirth.core.resource.i18n;

import org.slf4j.Marker;

/**
 * The class <strong>LogMessageParams</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface LogMessageParams extends MessageParams {

    /**
     * Return the mandatory log marker.
     * 
     * @return the log marker
     */
    Marker marker();
}
