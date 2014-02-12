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
package org.jrebirth.af.core.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * 
 * The class <strong>JRebirthThreadPoolExecutor</strong> is used to to manage the JRebirth Thread Pool (<b>JTP</b>).
 * 
 * @author Sébastien Bordes
 */
public class JRebirthThreadPoolExecutor extends ThreadPoolExecutor implements ConcurrentMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(JRebirthThreadPoolExecutor.class);

    /** The pending list of JRebirthRunnable. */
    private final List<JRebirthRunnable> pending = new ArrayList<>();

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
     * Check if a slot is available for the given task priority.
     * 
     * @param taskPriority the priority to check
     * 
     * @return true if this priority can be run right now
     */
    public boolean checkAvailability(final RunnablePriority taskPriority) {

        // The next task could be added if:
        // _ a slot is available
        // _ the task has a lower priority than current executed

        return getActiveCount() < getCorePoolSize() || checkPriority(taskPriority);
    }

    /**
     * Check given priority with current pending list.
     * 
     * @param taskPriority the priority to check
     * 
     * @return true if the priority is greater than those pending
     */
    private boolean checkPriority(final RunnablePriority taskPriority) {
        boolean highPriority = false;
        for (final JRebirthRunnable jr : this.pending) {
            highPriority |= taskPriority.getLevel() > jr.getPriority().getLevel();
        }
        return !highPriority;
    }

    // /**
    // * Execute the JRebirthRunnable.
    // *
    // * @param task to run
    // */
    // public void execute(final JRebirthRunnable task) {
    // super.execute(task);
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeExecute(final Thread t, final Runnable r) {

        this.pending.add((JRebirthRunnable) r);

        super.beforeExecute(t, r);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void afterExecute(final Runnable r, final Throwable t) {
        super.afterExecute(r, t);

        this.pending.remove(r);

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
