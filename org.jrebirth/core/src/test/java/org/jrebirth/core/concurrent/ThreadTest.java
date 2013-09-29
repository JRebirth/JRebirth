package org.jrebirth.core.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.Assert;

import org.jrebirth.core.application.ApplicationTest;

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

    @Test
    public void testJAT() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJAT(new JRebirthRunnable() {

            @Override
            public void run() {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJAT());
            }

            @Override
            public RunnablePriority getPriority() {
                return RunnablePriority.Normal;
            }
        });

        checkBoolean(ok);
    }

    @Test
    public void testJIT() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT(new JRebirthRunnable() {

            @Override
            public void run() {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJIT());
            }

            @Override
            public RunnablePriority getPriority() {
                return RunnablePriority.Normal;
            }
        });

        checkBoolean(ok);
    }

    @Test
    public void testJTP() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP(new JRebirthRunnable() {

            @Override
            public void run() {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJTPSlot());
            }

            @Override
            public RunnablePriority getPriority() {
                return RunnablePriority.Normal;
            }
        });

        checkBoolean(ok);
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
