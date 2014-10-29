package org.jrebirth.af.api.concurrent;

import java.util.concurrent.ExecutorService;

/**
 *
 * The class <strong>JRebirthThreadPoolExecutor</strong> is used to to manage the JRebirth Thread Pool (<b>JTP</b>).
 *
 * @author Sébastien Bordes
 */
public interface IJRebirthThreadPoolExecutor extends ExecutorService {

    /**
     * Check if a slot is available for the given task priority.
     *
     * @param taskPriority the priority to check
     *
     * @return true if this priority can be run right now
     */
    boolean checkAvailability(final RunnablePriority taskPriority);

}
