/**
 * Copyright JRebirth.org © 2011-2012 
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

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

import org.jrebirth.core.application.JRebirthApplication;
import org.jrebirth.core.command.basic.ShowModelWaveBuilder;
import org.jrebirth.core.event.JRebirthLogger;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.facade.GlobalFacade;
import org.jrebirth.core.facade.GlobalFacadeBase;

/**
 * The class <strong>JRebirthThread</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class JRebirthThread extends Thread {

    /** The JRebirth Thread name. */
    public static final String NAME = "JRebirth Thread";

    /** The unique instance of the current class. */
    private static JRebirthThread internalThread;

    /** The Global Facade object that handle other sub facade. */
    private transient GlobalFacade facade;

    /** The javaFX application that launch this thread. */
    private transient JRebirthApplication application;

    /** The list of tasks to execute, all access MUST BE synchronized. */
    // private final List<Runnable> tasks;

    /** The list of tasks queued waiting to be processed, all access MUST BE synchronized. */
    private final List<Runnable> queuedTasks;

    /** The list of tasks being processed, all access MUST BE synchronized. */
    private final List<Runnable> processingTasks;

    /** Flag to stop the infinite loop that process JRebirth Events. */
    private boolean infiniteLoop = true;

    /**
     * private final boolean readyToShutdown = false;
     * 
     * /** Build the JRebirth Thread.
     */
    private JRebirthThread() {
        super(NAME);

        // Daemonize this thread, thus it will be killed with the main JavaFX
        // thread
        setDaemon(true);

        // Initialize the queue
        this.queuedTasks = new ArrayList<>();
        this.processingTasks = new ArrayList<>();
    }

    /**
     * Run this task as soon as possible. Enqueue the task to be run at the next event pulse.
     * 
     * @param runnable the task to run
     */
    public void runAsap(final Runnable runnable) {
        // Synchronize the queue !
        synchronized (this) {
            this.queuedTasks.add(runnable);
        }
    }

    /**
     * Launch the JRebirth thread.
     * 
     * @param application the javaFX application instance
     */
    public void launch(final JRebirthApplication application) {

        // LInk the current application
        this.application = application;
        // Build the global facade at startup
        this.facade = new GlobalFacadeBase(application);

        // Start the thread (infinite loop)
        start();
    }

    /**
     * {@inheritDoc}
     */
    public void run() {

        try {
            launchFirstView();
        } catch (final CoreException e) {
            getFacade().getLogger().logException(e);
        }

        while (this.infiniteLoop) {
            try {

                // Need to sort tasks to launch by priority
                // Collections.sort(tasks);

                // Synchronize the re-copy operation
                synchronized (this.queuedTasks) {
                    // Copy enqueued tasks into processing list to run them
                    this.processingTasks.addAll(this.queuedTasks);
                    // Purge the current queue
                    this.queuedTasks.clear();
                }

                // Synchronize the queue !
                synchronized (this.processingTasks) {

                    // Run all tasks that are waiting to be processed
                    for (final Runnable r : this.processingTasks) {
                        r.run();
                    }
                    // Remove all task processed
                    this.processingTasks.clear();
                }

                // Pause this thread during 20ms to let other thread adding some
                // task into the queue
                Thread.sleep(20);

            } catch (final InterruptedException e) {
                e.printStackTrace();
                this.facade.getLogger().logException(e);
            }

        }
        // Shutdown the application more properly
        shutdown();
    }

    /**
     * {@inheritDoc}
     */
    public void interrupt() {
        super.interrupt();

        // Release all resources
        shutdown();
    }

    /**
     * 
     */
    public void close() {
        this.infiniteLoop = false;

        // Release all resources
        shutdown();
    }

    /**
     * Release all resources.
     */
    private void shutdown() {
        try {
            this.facade.stop();
            this.facade = null;
        } catch (final CoreException e) {
            JRebirthLogger.getInstance().logException(e);
        }
    }

    /**
     * Launch the first view by adding it into the root node.
     * 
     * @throws CoreException if the first class was not found
     */
    protected void launchFirstView() throws CoreException {

        try {

            getFacade().getNotifier().sendWave(
                    ShowModelWaveBuilder.create()
                            .parentNode((Pane) JRebirthThread.this.application.getScene().getRoot())
                            .modelClass(this.application.getFirstModelClass())
                            .build()
                    );

        } catch (final JRebirthThreadException e) {
            // Impossible case, unless someone override JRebirthThread class
            throw new CoreException("launchFirstView method was called outside JIT.");
        }
    }

    /**
     * @return Returns the application.
     */
    public JRebirthApplication getApplication() {
        return this.application;
    }

    /**
     * @return Returns the facade.
     */
    public GlobalFacade getFacade() {
        return this.facade;
    }

    /**
     * Return the JRebirthThread used to manage facades and waves.
     * 
     * @return the JRebirthThread
     */
    public static JRebirthThread getThread() {
        synchronized (JRebirthThread.class) {
            if (internalThread == null) {
                internalThread = new JRebirthThread();
            }
        }
        return internalThread;
    }

    /**
     * Return true if we are into the JRebirth Thread.
     * 
     * @return true if currentThread == JRebirthThread
     */
    public static boolean isJRebirthThread() {
        return NAME.equals(Thread.currentThread().getName());
    }

    /**
     * Check if the current code is executed into the JRebirthThread otherwise throws an exception.
     * 
     * @throws JRebirthThreadException if the we are into the wrong thread
     */
    public static void checkJRebirthThread() throws JRebirthThreadException {
        if (!isJRebirthThread()) {
            throw new JRebirthThreadException();
        }

    }

    /**
     * Run a task as soon as possible.
     * 
     * @param runnable the runnable to run
     */
    public static void runLater(final Runnable runnable) {
        internalThread.runAsap(runnable);
    }

}
