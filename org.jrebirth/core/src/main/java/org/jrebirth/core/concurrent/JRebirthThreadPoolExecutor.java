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

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jrebirth.core.log.JRLogger;
import org.jrebirth.core.log.JRLoggerFactory;

/**
 * 
 * The class <strong>JRebirthThreadPoolExecutor</strong> is used to to manage the JRebirth Thread Pool (<b>JTP</b>).
 * 
 * @author Sébastien Bordes
 */
public class JRebirthThreadPoolExecutor extends ThreadPoolExecutor implements ConcurrentMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(JRebirthThreadPoolExecutor.class);

    /**
     * Default Constructor.
     * 
     * @param threadNumber the number of thread managed by the pool
     * @param threadFactory the factory used to add a thread into the pool
     */
    public JRebirthThreadPoolExecutor(final int threadNumber, final ThreadFactory threadFactory) {
        super(threadNumber, threadNumber, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>(threadNumber, new JRebirthRunnableComparator()),
                threadFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void afterExecute(final Runnable r, final Throwable t) {
        super.afterExecute(r, t);
        Throwable rootCause = null;
        if (t == null && r instanceof Future<?>) {
            try {
                final Object result = ((Future<?>) r).get();
                if (result != null) {
                    LOGGER.log(FUTURE_DONE, r.hashCode(), result.toString());
                }
            } catch (final CancellationException | ExecutionException e) {
                rootCause = e.getCause();
            } catch (final InterruptedException ie) {
                Thread.currentThread().interrupt(); // ignore/reset
            }
        }
        if (t != null) {
            LOGGER.log(JTP_ERROR, t);
        }
        if (rootCause != null) {
            LOGGER.log(JTP_ERROR_EXPLANATION, rootCause);
        }
    }
}
