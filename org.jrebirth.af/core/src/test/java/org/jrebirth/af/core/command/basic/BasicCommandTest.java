package org.jrebirth.af.core.command.basic;

import javafx.application.Application;

import org.jrebirth.af.core.application.TestApplication;
import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.jrebirth.af.core.facade.CommandFacade;
import org.jrebirth.af.core.facade.GlobalFacadeBase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * The class <strong>StageTest</strong>.
 * 
 * @author Sébastien Bordes
 */
@Ignore("JavaFX can't be run in headless mode yet")
public class BasicCommandTest {

    private static GlobalFacadeBase globalFacade;

    private CommandFacade commandFacade;

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // globalFacade = new GlobalFacadeBase(new TestApplication());
        // JRebirthThread.getThread().launch(globalFacade.getApplication());
        new Thread(new Runnable() {

            @Override
            public void run() {
                Application.launch(TestApplication.class);

            }
        }).start();
        Thread.sleep(1000);
        globalFacade = (GlobalFacadeBase) JRebirthThread.getThread().getFacade();
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // new TestApplication().start(new Stage());
        this.commandFacade = globalFacade.getCommandFacade();
    }

    @Test
    public void openDefaultStage() {

    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.commandFacade = null;
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalFacade.stop();
        globalFacade = null;
    }

}
