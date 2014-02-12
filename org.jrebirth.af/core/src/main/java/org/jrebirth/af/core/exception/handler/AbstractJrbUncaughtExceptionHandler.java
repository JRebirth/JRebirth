/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.exception.handler;

import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * The class <strong>AbstractJRBUncaughtExceptionHandler</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractJrbUncaughtExceptionHandler implements JrbUncaughtExceptionHandler, HandlerMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractJrbUncaughtExceptionHandler.class);

    /** The type of UncaughtExceptionHandler. */
    private final UncaughtExceptionHandlerType uncaughtExceptionHandlerType;

    /**
     * Instantiates a new abstract jrb uncaught exception handler.
     * 
     * @param uncaughtExceptionHandlerType the type of handler
     */
    public AbstractJrbUncaughtExceptionHandler(final UncaughtExceptionHandlerType uncaughtExceptionHandlerType) {
        this.uncaughtExceptionHandlerType = uncaughtExceptionHandlerType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UncaughtExceptionHandlerType getUncaughtExceptionHandlerType() {
        return this.uncaughtExceptionHandlerType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uncaughtException(final Thread t, final Throwable e) {
        // e.printStackTrace();
        if (e instanceof Exception) {
            LOGGER.error(UNCAUGHT_EXCEPTION, e, e.getMessage());
        } else {
            LOGGER.error(UNKNOWN_UNCAUGHT_EXCEPTION, e.getClass().getCanonicalName(), e.getMessage());
        }

    }

}
