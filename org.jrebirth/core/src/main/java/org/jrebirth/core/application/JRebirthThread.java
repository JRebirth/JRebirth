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
 * The class <strong>JRebirthThread</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public class JRebirthThread extends Thread {

    private static JRebirthThread jrebirthThread;

    /** The Global Facade object that handle other sub facade. */
    private transient GlobalFacade facade;

    private transient JRebirthApplication application;

    private final List<Runnable> tasks;

    /**
     * Build the JRebirth Thread.
     */
    private JRebirthThread() {
        super("JRebirth Thread");
        setDaemon(true);
        this.tasks = new Vector<>();
    }

    public synchronized void runAsap(final Runnable runnable) {
        this.tasks.add(runnable);
    }

    /**
     * 
     * TODO To complete.
     * 
     * @param application
     */
    public void launch(final JRebirthApplication application) {
        this.application = application;
        // Build the global facade at startup
        this.facade = new GlobalFacadeImpl(application);

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (true) {
            try {
                // Collections.sort(tasks);

                synchronized (this) {
                    for (final Runnable r : this.tasks) {
                        r.run();
                    }
                    this.tasks.clear();
                }
                Thread.sleep(20);
            } catch (final InterruptedException e) {
                // TODO Auto-generated catch block
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
    @SuppressWarnings("unchecked")
    protected final void launchFirstView() throws CoreException {

        final Class<? extends Model> first = this.application.getFirstModelClass();

        if (first == null) {
            throw new CoreException("No First Model Class defined.");
        }
        final Node firstNode = getFacade().getUiFacade().retrieve(first).getView().getRootNode();

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
    protected JRebirthApplication getApplication() {
        return this.application;
    }

    /**
     * @param application The application to set.
     */
    protected void setApplication(final JRebirthApplication application) {
        this.application = application;
    }

    /**
     * @return Returns the facade.
     */
    public final GlobalFacade getFacade() {
        return this.facade;
    }

    /**
     * TODO To complete.
     */
    public static JRebirthThread getThread() {
        if (jrebirthThread == null) {
            jrebirthThread = new JRebirthThread();
        }
        return jrebirthThread;
    }

}
