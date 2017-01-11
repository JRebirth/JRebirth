package org.jrebirth.af.core.test;

import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.core.application.JRebirthApplicationTest;
import org.jrebirth.af.core.application.apps.EmptyTestApplication;
import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.jrebirth.af.core.ui.Showable;

import org.junit.BeforeClass;
import org.testfx.framework.junit.ApplicationTest;

/**
 * The class <strong>AbstractTest</strong>. TODO To complete
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractTest extends JRebirthApplicationTest<EmptyTestApplication> {

    public AbstractTest() {
        super(EmptyTestApplication.class);
    }

    protected static GlobalFacade globalFacade;

    @BeforeClass
    public static void startUp() throws Exception {
        ApplicationTest.launch(EmptyTestApplication.class);
        globalFacade = JRebirthThread.getThread().getFacade();
        Class.forName(Showable.class.getName());
    }

    // /**
    // * TODO To complete.
    // *
    // * @throws java.lang.Exception
    // */
    // @AfterClass
    // public static void tearDownAfterClass() throws Exception {
    // do {
    // // Try to stop the JRebirth Thread
    // JRebirthThread.getThread().close();
    //
    // // Wait parameterized delay before retrying to close if the thread is still alive
    // Thread.sleep(500);
    // } while (JRebirthThread.getThread().isAlive());
    // }

}
