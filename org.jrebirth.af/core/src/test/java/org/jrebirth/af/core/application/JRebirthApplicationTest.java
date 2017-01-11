package org.jrebirth.af.core.application;

import org.jrebirth.af.core.concurrent.JRebirthThread;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

public class JRebirthApplicationTest<A extends DefaultApplication<?>> extends FxRobot {

    private final Class<A> appClass;

    protected A application;

    public JRebirthApplicationTest(final Class<A> appClass) {
        this.appClass = appClass;
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        if (this.application == null) {
            // ApplicationTest.launch(this.appClass);

            this.application = (A) JRebirthThread.getThread().getApplication();
        }
    }

    @After
    public void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    @AfterClass
    public static void close() {
        // JRebirth.runIntoJAT(() ->
        // Platform.exit()
        // JRebirthThread.getThread().getApplication().stage().close()
        // );
    }

    // /**
    // * TODO To complete.
    // *
    // * @throws java.lang.Exception
    // */
    // @After
    // public void tearDown() throws Exception {
    // closeApplication(this.application);
    // }

    // @SuppressWarnings("unchecked")
    // public static <APP extends AbstractApplication<?>> APP launchApplication(
    // final Class<APP> applicationClass) throws Exception {
    //
    // // globalFacade = new GlobalFacadeBase(new EmptyTestApplication());
    // // JRebirthThread.getThread().launch(globalFacade.getApplication());
    //// final Thread launcherThread = new Thread(new Runnable() {
    ////
    //// @Override
    //// public void run() {
    //// Application.launch(applicationClass);
    //// }
    //// });
    //// launcherThread.start();
    //
    // ApplicationTest.launch(applicationClass);
    //
    //// JRebirthApplication<?> app;
    //// do {
    //// app = JRebirthThread.getThread().getApplication();
    //// try {
    //// Thread.sleep(100);
    //// } catch (final InterruptedException e) {
    //// e.printStackTrace();
    //// }
    //// } while (app == null);
    //
    // return (APP) JRebirthThread.getThread().getApplication();
    // }

    // public static void closeApplication(final AbstractApplication<?> application) {
    //
    // final JRebirthThread jit = JRebirthThread.getThread();
    // Platform.setImplicitExit(true);
    // Platform.exit();
    // // JRebirth.runIntoJAT(new
    // // AbstractJrbRunnable("CloseApplication-"+application.getClass().getSimpleName())
    // // {
    // //
    // // @Override
    // // protected void runInto() throws JRebirthThreadException {
    // // Platform.exit();
    // //
    // // }
    // // } ) ;
    //
    // while (jit == JRebirthThread.getThread()) {
    // try {
    // Thread.sleep(100);
    // } catch (final InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // System.out.println("Closed");
    // // JRebirthThread has now been renewed
    // }

}
