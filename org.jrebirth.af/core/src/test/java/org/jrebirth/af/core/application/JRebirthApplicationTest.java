package org.jrebirth.af.core.application;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.concurrent.JRebirthThread;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

public class JRebirthApplicationTest<A extends DefaultApplication<?>> extends FxRobot {

    protected A application;

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
    public static void close() throws InterruptedException {

        JRebirth.runIntoJAT(() -> {
            try {
                JRebirthThread.getThread().getApplication().stop();
            } catch (final CoreException e) {
                e.printStackTrace();
            }
        });

        while (JRebirthThread.getThread() != null && JRebirthThread.getThread().isAlive()) {
            Thread.sleep(250);
        }
    }

    protected void hold(long... time) {
        try {
            Thread.sleep(time.length > 0 ? time[0] : 100);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param wave
     */
    protected void waitWave(Wave wave) {
        while (wave.status() != Wave.Status.Handled) {
            try {
                Thread.sleep(10);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
