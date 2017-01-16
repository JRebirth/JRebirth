package org.jrebirth.af.core.command.basic;

import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.Wave.Status;
import org.jrebirth.af.core.application.JRebirthApplicationTest;
import org.jrebirth.af.core.application.apps.EmptyTestApplication;
import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.jrebirth.af.core.wave.DefaultWaveListener;

import org.junit.Before;
import org.junit.BeforeClass;
import org.testfx.framework.junit.ApplicationTest;

/**
 * The class <strong>StageTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class BasicCommandTest extends JRebirthApplicationTest<EmptyTestApplication> {

    private GlobalFacade globalFacade;

    private final AtomicBoolean wait = new AtomicBoolean(false);

    @BeforeClass
    public static void startUp() throws Exception {
        ApplicationTest.launch(EmptyTestApplication.class);
    }

    /**
     *
     * @throws java.lang.Exception
     */
    @Before
    public void localSetUp() throws Exception {
        this.globalFacade = JRebirthThread.getThread().getFacade();
    }

    public void runCommand(final Class<? extends Command> commandClass, final Object... keyPart) {

        this.wait.set(true);

        final Wave wave = this.globalFacade.commandFacade().retrieve(commandClass, keyPart).run();

        wave.addWaveListener(new DefaultWaveListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void waveHandled(final Wave wave) {
                BasicCommandTest.this.wait.set(false);
            }

        });

        if (wave.status() == Status.Handled) {
            this.wait.set(false);
        }

        while (this.wait.get()) {
            try {
                Thread.sleep(200);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
