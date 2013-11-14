package org.jrebirth.core.concurrent;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.Assert;

import org.jrebirth.core.application.ApplicationTest;
import org.jrebirth.core.exception.JRebirthThreadException;

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

            JRebirth.runIntoJTP(new LongTask("1", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("2", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("3", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("4", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("5", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("6", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("7", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("8", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("9", RunnablePriority.Lowest));
            JRebirth.runIntoJTP(new LongTask("10", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("11", RunnablePriority.Low));
            JRebirth.runIntoJTP(new LongTask("12", RunnablePriority.Normal));
            JRebirth.runIntoJTP(new LongTask("13", RunnablePriority.High));
            JRebirth.runIntoJTP(new LongTask("14", RunnablePriority.Highest));
            JRebirth.runIntoJTP(new LongTask("15", RunnablePriority.Lowest));
            JRebirth.runIntoJTP(new LongTask("16", RunnablePriority.Low));

        } catch (Exception e) {
            e.printStackTrace();
        }
        // checkBoolean(ok);
    }

    private class LongTask implements JRebirthRunnable {

        String name;
        RunnablePriority priority;
        long time;

        public LongTask(String string, RunnablePriority normal) {
            name = string;
            priority = normal;
            time = Calendar.getInstance().getTimeInMillis();

            LOGGER.info("Creating " + name + "(" + priority.name() + ") into " + Thread.currentThread().getName());
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Running " + name + "(" + priority.name() + ") into " + Thread.currentThread().getName());
        }

        @Override
        public RunnablePriority getPriority() {
            return priority;
        }

        @Override
        public long getCreationTime() {
            return time;
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
