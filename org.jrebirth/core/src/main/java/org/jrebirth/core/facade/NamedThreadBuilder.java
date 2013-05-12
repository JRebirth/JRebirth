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
package org.jrebirth.core.facade;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;

/**
 * The class <strong>NamedThreadBuilder</strong>.
 * 
 * @author Sébastien Bordes
 */
public class NamedThreadBuilder implements ThreadFactory {

    /** The number of the pooled thread. */
    private int poolIndex = 1;

    /** The base name of created thread. */
    private final String baseName;

    /** The uncaught Exception handler. */
    private final UncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * Default Constructor.
     * 
     * @param uncaughtExceptionHandler the handler to use for uncaught exception
     * @param baseName the base name for all threads created into this pool
     */
    public NamedThreadBuilder(final UncaughtExceptionHandler uncaughtExceptionHandler, final String baseName) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        this.baseName = baseName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Thread newThread(final Runnable r) {
        final String pooledThreadName = this.baseName + this.poolIndex++;
        final Thread thread = new Thread(r, pooledThreadName);
        thread.setUncaughtExceptionHandler(this.uncaughtExceptionHandler); // FIXME Cannot be used !!! see JRebirthThread.runNow method which will catch exceptions

        return thread;
    }
}
