package org.jrebirth.core.facade;

import java.util.concurrent.ThreadFactory;

import org.jrebirth.core.exception.handler.PoolUncaughtExceptionHandler;

/**
 * The class <strong>NamedThreadFactory</strong>.
 * 
 * @author Sébastien Bordes
 */
public class NamedThreadFactory implements ThreadFactory {

    /** The number of the pooled thread. */
    private int poolIndex = 1;

    /** The base name of created thread. */
    private final String baseName;

    /** The uncaught Exception handler. */
    private final PoolUncaughtExceptionHandler uncaughtExceptionHandler;

    public NamedThreadFactory(final PoolUncaughtExceptionHandler uncaughtExceptionHandler, final String baseName) {
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
        thread.setUncaughtExceptionHandler(this.uncaughtExceptionHandler);

        return thread;
    }
}
