/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.core.exception.handler;

import org.jrebirth.core.facade.GlobalFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>AbstractJRBUncaughtExceptionHandler</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractJrbUncaughtExceptionHandler implements JrbUncaughtExceptionHandler {

    /** The class logger. */
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractJrbUncaughtExceptionHandler.class);

    /** The global facade. */
    private final GlobalFacade globalFacade;

    /** The type of UncaughtExceptionHandler. */
    private final UncaughtExceptionHandlerType uncaughtExceptionHandlerType;

    /**
     * Instantiates a new abstract jrb uncaught exception handler.
     * 
     * @param globalFacade the global facade
     * @param uncaughtExceptionHandlerType the type of handler
     */
    public AbstractJrbUncaughtExceptionHandler(final GlobalFacade globalFacade, final UncaughtExceptionHandlerType uncaughtExceptionHandlerType) {
        this.globalFacade = globalFacade;
        this.uncaughtExceptionHandlerType = uncaughtExceptionHandlerType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GlobalFacade getGlobalFacade() {
        return this.globalFacade;
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
            LOGGER.error(e.getMessage(), e);
        } else {
            LOGGER.error(e.getMessage());
        }

    }

}
