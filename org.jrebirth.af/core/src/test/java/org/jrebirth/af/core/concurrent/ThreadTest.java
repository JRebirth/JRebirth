package org.jrebirth.af.core.concurrent;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.concurrent.JRebirthRunnable;
import org.jrebirth.af.api.exception.JRebirthThreadException;
import org.jrebirth.af.core.application.ApplicationTest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ThreadTest</strong>.
 *
 * @author Sébastien Bordes
 */
@Ignore("JavaFX can't be run in headless mode yet")
public class ThreadTest extends ApplicationTest<ThreadApplication> {

    public ThreadTest() {
        super(ThreadApplication.class);
    }

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest.class);

    // @Test
    public void testJAT() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJAT(new AbstractJrbRunnable("Jat test") {

            @Override
            protected void runInto() throws JRebirthThreadException {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJAT());

            }
        });

        checkBoolean(ok);
    }

    // @Test
    public void testJIT() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT(new AbstractJrbRunnable("JIT test") {

            @Override
            protected void runInto() throws JRebirthThreadException {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJIT());
            }

        });

        checkBoolean(ok);
    }

    // @Test
    public void testJTP() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP(new AbstractJrbRunnable("JTP test") {

            @Override
            protected void runInto() throws JRebirthThreadException {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJTPSlot());
            }

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
        public PriorityLevel getPriority() {
            return this.priority;
        }

        @Override
        public Instant getCreationTime() {
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
