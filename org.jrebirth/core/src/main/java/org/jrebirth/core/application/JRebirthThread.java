package org.jrebirth.core.application;

import java.util.List;
import java.util.Vector;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.GlobalFacade;
import org.jrebirth.core.facade.impl.GlobalFacadeImpl;
import org.jrebirth.core.ui.Model;

/**
 * The class <strong>JRebirthThread</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class JRebirthThread extends Thread {

    /** The unique instance of the current class. */
    private static JRebirthThread JREBIRTH_THREAD;

    /** The Global Facade object that handle other sub facade. */
    private transient GlobalFacade facade;

    /** The javaFX application that launch this thread. */
    private transient JRebirthApplication application;

    /** The list of tasks to execute, MUST BE synchronized. */
    private final List<Runnable> tasks;

    /**
     * Build the JRebirth Thread.
     */
    private JRebirthThread() {
        super("JRebirth Thread");
        setDaemon(true);
        this.tasks = new Vector<>();
    }

    /**
     * Run this task as soon as possible. Enqueue the task to be run at the next event pulse.
     * 
     * @param runnable the task to run
     */
    public void runAsap(final Runnable runnable) {
        // Synchronize the queue !
        synchronized (this) {
            this.tasks.add(runnable);
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
        this.facade = new GlobalFacadeImpl(application);

        // Start the thread (infinite loop)
        start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {

        try {
            launchFirstView();
        } catch (final CoreException e) {
            this.facade.getLogger().error(e.getMessage());
            e.printStackTrace();
        }

        while (true) {
            try {

                // Need to sort tasks to launch by priority
                // Collections.sort(tasks);

                // Synchronize the queue !
                synchronized (this) {
                    // Run all queued tasks
                    for (final Runnable r : this.tasks) {
                        r.run();
                    }
                    // Remove all task processed
                    this.tasks.clear();
                }
                // Pause this thread during 20ms to let other thread adding some task into the queue
                Thread.sleep(20);

            } catch (final InterruptedException e) {
                this.facade.getLogger().error(e.getMessage());
                e.printStackTrace();
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interrupt() {
        super.interrupt();

        try {
            this.facade.stop();
            this.facade = null;
        } catch (final CoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the first view by adding it into the root node.
     * 
     * @throws CoreException if the first class was not found
     */
    protected final void launchFirstView() throws CoreException {

        final Class<? extends Model> first = this.application.getFirstModelClass();

        if (first == null) {
            throw new CoreException("No First Model Class defined.");
        }
        // Build the first root node
        final Node firstNode = getFacade().getUiFacade().retrieve(first).getView().getRootNode();

        // Add the scene's root node into the JavaFX Application Thread
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                ((Pane) JRebirthThread.this.application.getScene().getRoot()).getChildren().add(firstNode);
                firstNode.requestFocus();
            }
        });
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
    public final GlobalFacade getFacade() {
        return this.facade;
    }

    /**
     * Return the JRebirthThread used to manage facades and waves.
     * 
     * @return the JRebirthThread
     */
    public static JRebirthThread getThread() {
        if (JREBIRTH_THREAD == null) {
            JREBIRTH_THREAD = new JRebirthThread();
        }
        return JREBIRTH_THREAD;
    }

}
