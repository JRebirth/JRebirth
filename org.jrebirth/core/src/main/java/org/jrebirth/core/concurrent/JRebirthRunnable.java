package org.jrebirth.core.concurrent;

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
}
