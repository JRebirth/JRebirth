package org.jrebirth.af.core.application;

import javafx.application.Platform;

import org.jrebirth.af.api.application.JRebirthApplication;
import org.jrebirth.af.core.concurrent.JRebirthThread;

import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

//@Ignore("Only one application at the same time")
public class ApplicationTest<A extends DefaultApplication<?>> extends FxRobot {

    private final Class<A> appClass;

    protected A application;

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

    // @Before
    // public void setup() throws Exception {
    // ApplicationTest.launch(DemoApplication.class);
    // }

    @After
    public void cleanup() throws Exception {
        FxToolkit.cleanupStages();
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
            final Class<APP> applicationClass) throws Exception {

        // globalFacade = new GlobalFacadeBase(new TestApplication());
        // JRebirthThread.getThread().launch(globalFacade.getApplication());
        // final Thread launcherThread = new Thread(new Runnable() {
        //
        // @Override
        // public void run() {
        // Application.launch(applicationClass);
        // }
        // });
        // launcherThread.start();

        org.testfx.framework.junit.ApplicationTest.launch(applicationClass);

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
