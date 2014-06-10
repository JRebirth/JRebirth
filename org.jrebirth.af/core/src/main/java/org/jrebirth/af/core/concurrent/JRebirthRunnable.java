package org.jrebirth.af.core.concurrent;

/**
 * The class <strong>JRebirthRunnable</strong>.
 *
 * @author Sébastien Bordes
 */
public interface JRebirthRunnable extends Runnable {

    /**
     * Return the runnable priority.
     *
     * @return the runnable priority
     */
    RunnablePriority getPriority();

    /**
     * Return the creation time.
     *
     * @return the creation time in milliseconds
     */
    long getCreationTime();
}
