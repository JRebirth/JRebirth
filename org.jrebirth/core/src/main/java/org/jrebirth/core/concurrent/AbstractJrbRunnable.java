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
package org.jrebirth.core.concurrent;

import org.jrebirth.core.exception.JRebirthThreadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>AbstractJrbRunnable</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractJrbRunnable implements Runnable {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJrbRunnable.class);

    /** This name is used for debug purpose. */
    private final String runnableName;

    /**
     * Default Constructor.
     * 
     * @param runnableName the name of the runnable to allow live debugging
     */
    public AbstractJrbRunnable(final String runnableName) {
        this.runnableName = runnableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void run() {

        LOGGER.trace("Run> " + this.runnableName);

        try {
            runInto();
        } catch (final JRebirthThreadException jte) {
            LOGGER.error(jte.getMessage(), jte);
        }

    }

    /**
     * Run task and allow to throw a JRebirthException.
     * 
     * @throws JRebirthThreadException it thread error
     */
    protected abstract void runInto() throws JRebirthThreadException;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AbstractJrbRunnable - " + this.runnableName;
    }

}
