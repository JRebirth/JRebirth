package org.jrebirth.core.application;

import javafx.application.Application;
import javafx.application.Platform;

import org.jrebirth.af.core.application.AbstractApplication;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.core.application.JRebirthApplication;
import org.jrebirth.af.core.concurrent.JRebirthThread;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

@Ignore("Only one application at the same time")
public class ApplicationTest<A extends DefaultApplication<?>> {

    private final Class<A> appClass;

    private A application;

    public ApplicationTest(final Class<A> appClass) {
        this.appClass = appClass;
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.application = launchApplication(this.appClass);
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        closeApplication(this.application);
    }

    public static <APP extends AbstractApplication<?>> APP launchApplication(
            final Class<APP> applicationClass) {

        // globalFacade = new GlobalFacadeBase(new TestApplication());
        // JRebirthThread.getThread().launch(globalFacade.getApplication());
        final Thread launcherThread = new Thread(new Runnable() {

            @Override
            public void run() {
                Application.launch(applicationClass);
            }
        });
        launcherThread.start();

        JRebirthApplication<?> app;
        do {
            app = JRebirthThread.getThread().getApplication();
            try {
                Thread.sleep(100);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        } while (app == null);

        return (APP) app;
    }

    public static void closeApplication(final AbstractApplication<?> application) {

        final JRebirthThread jit = JRebirthThread.getThread();
        Platform.setImplicitExit(true);
        Platform.exit();
        // JRebirth.runIntoJAT(new
        // AbstractJrbRunnable("CloseApplication-"+application.getClass().getSimpleName())
        // {
        //
        // @Override
        // protected void runInto() throws JRebirthThreadException {
        // Platform.exit();
        //
        // }
        // } ) ;

        while (jit == JRebirthThread.getThread()) {
            try {
                Thread.sleep(100);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Closed");
        // JRebirthThread has now been renewed
    }

}