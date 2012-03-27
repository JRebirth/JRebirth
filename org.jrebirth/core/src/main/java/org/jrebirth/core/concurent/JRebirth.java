package org.jrebirth.core.concurent;

import javafx.application.Platform;

/**
 * The class <strong>JRebirth</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class JRebirth {

    /**
     * Private Constructor.
     */
    private JRebirth() {
        super();
    }

    /**
     * Run the task into the appropriated thread.
     * 
     * @param runInto the targeted thread
     * @param runnable the task to run
     */
    public static void run(final RunIntoType runInto, final Runnable runnable) {
        switch (runInto) {
            case JAT:
                runIntoJAT(runnable);
                break;
            case JIT:
                runIntoJIT(runnable);
                break;
            case POOL:
                runIntoThreadPool(runnable);
                break;
            default:
        }
    }

    /**
     * Run the task into the Java Application Thread.
     * 
     * @param runnable the task to run
     */
    public static void runIntoJAT(final Runnable runnable) {
        Platform.runLater(runnable);

    }

    /**
     * Run into the JRebirth Internal Thread.
     * 
     * @param runnable the task to run
     */
    public static void runIntoJIT(final Runnable runnable) {
        JRebirthThread.runLater(runnable);

    }

    /**
     * Run into the internal thread pool.
     * 
     * @param runnable the task to run
     */
    public static void runIntoThreadPool(final Runnable runnable) {
        // TODO add the runnable into the thread pool
        // Manage different pools (1 and 4)
    }
}
