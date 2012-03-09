package org.jrebirth.core.concurent;

import javafx.application.Platform;

/**
 * The class <strong>JRebirth</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class JRebirth {

    /**
     * 
     */
    private JRebirth() {
        super();
    }

    public static void run(final RunIntoType runInto, final JRebirthRunnable runnable) {
        switch (runInto) {
        case JAT:
            runIntoJAT(runnable);
            break;
        case JET:
            runIntoJET(runnable);
            break;
        case THREAD_POOL:
            runIntoThreadPool(runnable);
            break;

        }
    }

    /**
     * TODO To complete.
     * 
     * @param runnable
     */
    public static void runIntoJAT(final JRebirthRunnable jRebirthRunnable) {
        Platform.runLater(jRebirthRunnable);

    }

    /**
     * TODO To complete.
     * 
     * @param runnable
     */
    public static void runIntoJET(final JRebirthRunnable jRebirthRunnable) {
        JRebirthThread.runLater(jRebirthRunnable);

    }

    /**
     * TODO To complete.
     * 
     * @param runnable
     */
    public static void runIntoThreadPool(final JRebirthRunnable runnable) {
        // TODO
    }
}
