package org.jrebirth.core.facade;

import java.util.concurrent.ThreadFactory;

import org.jrebirth.core.exception.handler.PoolUncaughtExceptionHandler;

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
    private final PoolUncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * Default Constructor.
     * 
     * @param uncaughtExceptionHandler the handler to use for uncaught exception
     * @param baseName the base name for all threads created into this pool
     */
    public NamedThreadBuilder(final PoolUncaughtExceptionHandler uncaughtExceptionHandler, final String baseName) {
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
