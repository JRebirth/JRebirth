package org.jrebirth.af.core.concurrent;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.concurrent.JRebirthRunnable;
import org.jrebirth.af.api.exception.JRebirthThreadException;
import org.jrebirth.af.core.application.JRebirthApplicationTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;

/**
 * The class <strong>ThreadTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class ThreadTest extends JRebirthApplicationTest<ThreadApplication> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest.class);

    @BeforeClass
    public static void startUp() throws Exception {
        ApplicationTest.launch(ThreadApplication.class);
    }

    @Rule
    public final ExpectedException jrte = ExpectedException.none();

    @Test
    public void checkJATok() {
        JRebirth.runIntoJAT("Check JAT OK", () -> JRebirth.checkJAT());
    }

    @Test(expected = JRebirthThreadException.class)
    public void checkJATko() {
        JRebirth.checkJAT();
    }

    // @Test
    public void checkJATko2() {
        JRebirth.runIntoJIT("Check JAT KO", () -> {
            jrte.expect(JRebirthThreadException.class);
            JRebirth.checkJAT();
        });
    }

    @Test
    public void checkJITok() {
        JRebirth.runIntoJIT("Check JIT OK", () -> JRebirth.checkJIT());
    }

    @Test(expected = JRebirthThreadException.class)
    public void checkJITko() {
        JRebirth.checkJIT();
    }

    // @Test(expected=JRebirthThreadException.class)
    public void checkJITko2() {
        JRebirth.runIntoJTP("Check JIT KO", () -> JRebirth.checkJIT());
    }

    @Test
    public void checkJTPok() {
        JRebirth.runIntoJTP("Check JTP OK", () -> JRebirth.checkJTPSlot());
    }

    @Test(expected = JRebirthThreadException.class)
    public void checkJTPko() {
        JRebirth.checkJTPSlot();
    }

    // @Test(expected=JRebirthThreadException.class)
    public void checkJTPko2() {
        JRebirth.runIntoJAT("Check JTP KO", () -> JRebirth.checkJTPSlot());
    }

    @Test
    public void testJAT() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJAT("Jat test", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJIT1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT(new JrbReferenceRunnable("JIT test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        }));

        checkBoolean(ok);
    }

    @Test
    public void testJIT2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT("JIT test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJIT3() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT("JIT test 3", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJTP1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP(new JrbReferenceRunnable("JTP test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        }));

        checkBoolean(ok);
    }

    @Test
    public void testJTP2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP("JTP test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJTP3() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP("JTP test 3", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        checkBoolean(ok);
    }

    @Test
    public void testPHTP() {

        try {
            // final AtomicBoolean ok = new AtomicBoolean(false);

            JRebirth.runIntoJTP(new LongTask("1", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("2", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("3", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("4", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("5", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("6", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("7", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("8", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("9", PriorityLevel.Lowest));
            JRebirth.runIntoJTP(new LongTask("10", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("11", PriorityLevel.Low));
            JRebirth.runIntoJTP(new LongTask("12", PriorityLevel.Normal));
            JRebirth.runIntoJTP(new LongTask("13", PriorityLevel.High));
            JRebirth.runIntoJTP(new LongTask("14", PriorityLevel.Highest));
            JRebirth.runIntoJTP(new LongTask("15", PriorityLevel.Lowest));
            JRebirth.runIntoJTP(new LongTask("16", PriorityLevel.Low));

        } catch (final Exception e) {
            e.printStackTrace();
        }
        // checkBoolean(ok);
    }

    private class LongTask implements JRebirthRunnable {

        String name;
        PriorityLevel priority;
        Instant time;

        public LongTask(final String string, final PriorityLevel normal) {
            this.name = string;
            this.priority = normal;
            this.time = Instant.now();

            LOGGER.info("Creating " + this.name + "(" + this.priority.name() + ") into " + Thread.currentThread().getName());
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Running " + this.name + "(" + this.priority.name() + ") into " + Thread.currentThread().getName());
        }

        @Override
        public PriorityLevel priority() {
            return this.priority;
        }

        @Override
        public Instant creationTime() {
            return this.time;
        }
    }

    /**
     * TODO To complete.
     *
     * @param ok
     */
    private void checkBoolean(final AtomicBoolean ok) {
        int i = 0;
        while (!ok.get() && i < 10) {
            try {
                Thread.sleep(200);
            } catch (final InterruptedException e) {
            }
            i++;
        }
        Assert.assertEquals(true, ok.get());
    }

}
