package org.jrebirth.af.core.command.basic;

import javafx.application.Application;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.Wave.Status;
import org.jrebirth.af.core.application.TestApplication;
import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.jrebirth.af.core.facade.GlobalFacadeBase;
import org.jrebirth.af.core.wave.DefaultWaveListener;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * The class <strong>StageTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class BasicCommandTest {

    private static GlobalFacadeBase globalFacade;

    // private CommandFacade commandFacade;.

    private boolean wait = false;

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
        // this.commandFacade = globalFacade.getCommandFacade();
    }

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        // this.commandFacade = null;
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

    public void runCommand(final Class<? extends Command> commandClass, final Object... keyPart) {

        this.wait = true;

        final Wave wave = globalFacade.getCommandFacade().retrieve(commandClass, keyPart).run();

        wave.addWaveListener(new DefaultWaveListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void waveConsumed(final Wave wave) {
                BasicCommandTest.this.wait = false;
            }

        });

        if (wave.status() == Status.Consumed) {
            this.wait = false;
        }

        while (this.wait) {
            try {
                Thread.sleep(200);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
