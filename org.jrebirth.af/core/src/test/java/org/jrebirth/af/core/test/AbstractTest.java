package org.jrebirth.af.core.test;

import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.core.application.TestApplication;
import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * The class <strong>AbstractTest</strong>. TODO To complete
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractTest {

    protected static GlobalFacade globalFacade;

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        // new TestApplication().start(new Stage());
        JRebirthThread.getThread().prepare(new TestApplication());
        globalFacade = JRebirthThread.getThread().getFacade();
    }

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        do {
            // Try to stop the JRebirth Thread
            JRebirthThread.getThread().close();

            // Wait parameterized delay before retrying to close if the thread is still alive
            Thread.sleep(500);
        } while (JRebirthThread.getThread().isAlive());
    }

}
