package org.jrebirth.core.command.basic.stage;

import javafx.application.Application;
import javafx.stage.Stage;

import org.jrebirth.core.application.TestApplication;
import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.concurrent.JRebirthThread;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.facade.CommandFacade;
import org.jrebirth.core.facade.GlobalFacadeBase;
import org.jrebirth.core.service.basic.StageService;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The class <strong>FacadeTest</strong>.
 * 
 * @author Sébastien Bordes
 */
public class StageTest {

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

        final String stageKey = "defaultStage";
        final Wave wave = StageWaveBuilder.create()
                .action(StageAction.show)
                .key(stageKey)
                .build();

        wave.addWaveListener(new WaveListener() {

            @Override
            public void waveSent(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveProcessed(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveFailed(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveDestroyed(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveCreated(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveConsumed(final Wave wave) {
                final Stage stage = StageTest.globalFacade.getServiceFacade().retrieve(StageService.class).getStage(stageKey);
                Assert.assertNotNull(stage);
                System.out.println("dddd");
            }

            @Override
            public void waveCancelled(final Wave wave) {
                // Nothing to do yet

            }
        });

        JRebirth.runIntoJIT(new AbstractJrbRunnable("Send Wave " + wave.toString()) {
            @Override
            public void runInto() throws JRebirthThreadException {
                StageTest.globalFacade.getNotifier().sendWave(wave);
            }
        });

        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

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
