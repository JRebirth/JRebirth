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
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.Scene;

import org.jrebirth.af.api.application.JRebirthApplication;
import org.jrebirth.af.api.concurrent.JRebirthRunnable;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.JRebirthThreadException;
import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.basic.showmodel.DisplayModelWaveBean;
import org.jrebirth.af.core.command.basic.showmodel.ShowModelCommand;
import org.jrebirth.af.core.facade.GlobalFacadeBase;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;
import org.jrebirth.af.core.service.basic.StyleSheetTrackerService;
import org.jrebirth.af.core.wave.Builders;

/**
 * The class <strong>JRebirthThread</strong>.
 *
 * @author Sébastien Bordes
 */
public final class JRebirthThread extends Thread implements ConcurrentMessages {

    /** The JRebirth Internal Thread name [JIT]. */
    public static final String JIT_NAME = "JIT";

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(JRebirthThread.class);

    /** The unique instance of the current class. */
    private static JRebirthThread internalThread;

    /** The Global Facade object that handle other sub facade. */
    private transient GlobalFacade facade;

    /** The javaFX application that launch this thread. */
    private transient JRebirthApplication<?> application;

    /** The list of tasks being processed, all access MUST BE synchronized. */
    private final PriorityBlockingQueue<JRebirthRunnable> processingTasks;

    /** Flag indicating that current thread has started and is ready to process events. */
    private final AtomicBoolean hasStarted = new AtomicBoolean(false);

    /** Flag to stop the infinite loop that process JRebirth Events. */
    private final AtomicBoolean infiniteLoop = new AtomicBoolean(true);

    /** Flag that indicate that the closure must be forced. */
    private final AtomicBoolean forceClose = new AtomicBoolean(false);

    /**
     * private final boolean readyToShutdown = false;
     *
     * /** Build the JRebirth Thread.
     */
    private JRebirthThread() {
        super(JIT_NAME);

        // Daemon-ize this thread, thus it will be killed with the main JavaFX Thread
        setDaemon(true);

        // Initialize the queue
        this.processingTasks = new PriorityBlockingQueue<JRebirthRunnable>(10, new JRebirthRunnableComparator());
    }

    /**
     * Run into thread pool.
     *
     * If a slot is available the task will be run immediately.<br />
     * Otherwise it will run as soon as a slot will be available according to the existing task queue
     *
     * @param runnable the task to run
     */
    public void runIntoJTP(final JRebirthRunnable runnable) {

        if (getFacade().getExecutorService().checkAvailability(runnable.getPriority())) {
            getFacade().getExecutorService().execute(runnable);
            LOGGER.log(JTP_QUEUED, runnable.toString());
        } else {
            getFacade().getHighPriorityExecutorService().execute(runnable);
            LOGGER.log(HPTP_QUEUED, runnable.toString());
        }

    }

    /**
     * Run this task as soon as possible. Enqueue the task to be run at the next event pulse. Run into the JRebirth Thread
     *
     * @param runnable the task to run
     */
    public void runLater(final JRebirthRunnable runnable) {
        this.processingTasks.add(runnable);
    }

    /**
     * Launch the JRebirth thread.
     *
     * @param application the javaFX application instance
     */
    public void prepare(final JRebirthApplication<?> application) {

        // Link the current application
        this.application = application;
        // Build the global facade at startup
        this.facade = new GlobalFacadeBase(application);

        // Start the thread (infinite loop)
        // start();
    }

    /**
     * Return true if JRebirth has been correctly started (boot action is done).
     *
     * @return true if JRebirth has been correctly started
     */
    public boolean hasStarted() {
        return this.hasStarted.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {

        manageStyleSheetReloading(this.application.getScene());

        // Attach the first view and run pre and post command
        try {
            bootUp();
        } catch (final JRebirthThreadException e) {
            LOGGER.error(BOOT_UP_ERROR, e);
        }

        // JRebirth thread has boot up and is ready to process events
        this.hasStarted.set(true);

        while (this.infiniteLoop.get()) {
            try {
                if (!this.forceClose.get()) {
                    final JRebirthRunnable jrr = this.processingTasks.poll(100, TimeUnit.MILLISECONDS);
                    if (jrr != null) {
                        jrr.run();
                    }
                }
            } catch (final InterruptedException e) {
                LOGGER.error(JIT_ERROR, e);
            }
        }
        // Shutdown the application more properly
        shutdown();
    }

    /**
     * Manage style sheet reloading by using a custom service provide by JRebirth Core.
     *
     * @param scene the scene to reload in case of Style Sheet update
     */
    private void manageStyleSheetReloading(final Scene scene) {
        if (CoreParameters.DEVELOPER_MODE.get() && scene != null) {

            for (final String styleSheet : scene.getStylesheets()) {

                getFacade().getServiceFacade().retrieve(StyleSheetTrackerService.class).listen(styleSheet, this.application.getScene());
            }
            getFacade().getServiceFacade().retrieve(StyleSheetTrackerService.class).start();
        }
    }

    /**
     * Attach the first view and run pre and post command.
     *
     * @throws JRebirthThreadException if a problem occurred while calling the command
     */
    public void bootUp() throws JRebirthThreadException {

        final List<Wave> chainedWaveList = new ArrayList<>();

        // Manage waves to run before the First node creation
        final List<Wave> preBootList = getApplication().getPreBootWaveList();
        if (preBootList != null && !preBootList.isEmpty()) {
            chainedWaveList.addAll(preBootList);
        }

        // Manage the creation of the first node and show it !

        final Wave firstViewWave = getLaunchFirstViewWave();
        if (firstViewWave != null) {
            chainedWaveList.add(firstViewWave);
        }

        // Manage waves to run after the First node creation
        final List<Wave> postBootList = getApplication().getPostBootWaveList();
        if (postBootList != null && !postBootList.isEmpty()) {
            chainedWaveList.addAll(postBootList);
        }

        if (!chainedWaveList.isEmpty()) {
            getFacade().getNotifier().sendWave(Builders.chainWaveCommand(chainedWaveList));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interrupt() {
        super.interrupt();

        // Release all resources
        shutdown();
    }

    /**
     * This method can be called a lot of time while application is running.
     *
     * The first time to stop the infinite loop, then to purge all queues and let the thread terminate itself.
     */
    public void close() {

        // Infinite loop is still active
        if (this.infiniteLoop.get()) {
            // First attempt to close the application
            this.infiniteLoop.set(false);
        } else {
            // N-th attempt to close the application
            this.forceClose.set(true);

            // All Task Queues are cleared
            // this.queuedTasks.clear();
            this.processingTasks.clear();
        }

    }

    /**
     * Release all resources.
     */
    private void shutdown() {
        try {
            this.facade.stop();
            this.facade = null;
            // Destroy the static reference
            destroyInstance();
        } catch (final CoreException e) {
            LOGGER.log(SHUTDOWN_ERROR, e);
        }
    }

    /**
     * Destroy the singleton that hold the thread.
     */
    private static void destroyInstance() {
        internalThread = null;
    }

    /**
     * Launch the first view by adding it into the root node.
     *
     * @return the wave responsible of the creation of the first view
     */
    @SuppressWarnings("unchecked")
    protected Wave getLaunchFirstViewWave() {
        Wave firstWave = null;
        // Generates the command wave directly to win a Wave cycle
        if (this.application != null && this.application.getRootNode() != null && this.application.getFirstModelClass() != null) {

            firstWave = Builders.callCommand(ShowModelCommand.class).waveBean(
                                                                              DisplayModelWaveBean.create()
                                                                                                  .childrenPlaceHolder(this.application.getRootNode().getChildren())
                                                                                                  .showModelKey(Key.create(this.application.getFirstModelClass())));
            //
            //
            // ShowModelWaveBuilder.create()
            // .childrenPlaceHolder(this.application.getRootNode().getChildren())
            // .showModelKey(getFacade().getUiFacade().buildKey((Class<Model>) this.application.getFirstModelClass()))
            // .build();
        }
        return firstWave;

    }

    /**
     * @return Returns the application.
     */
    public JRebirthApplication<?> getApplication() {
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

}
