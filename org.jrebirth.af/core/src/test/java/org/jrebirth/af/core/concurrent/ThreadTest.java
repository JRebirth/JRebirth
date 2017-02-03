package org.jrebirth.af.core.concurrent;

import java.lang.reflect.Constructor;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.concurrent.JRebirthRunnable;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.exception.JRebirthThreadException;
import org.jrebirth.af.core.application.JRebirthApplicationTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;

/**
 * The class <strong>ThreadTest</strong>.
 *
 * @author Sébastien Bordes
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void testValidatesThatClassFooIsNotInstantiable() {
        try {
            final Constructor<JRebirth> c = JRebirth.class.getDeclaredConstructor();
            c.setAccessible(true);
            c.newInstance(); // exception here
        } catch (final Exception e) {
            Assert.assertTrue(false);
        }
    }

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
            this.jrte.expect(JRebirthThreadException.class);
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

    /////////////////////
    // SAME
    /////////////////////

    @Test
    public void testSAME1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.SAME, new JrbReferenceRunnable("SAME test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(true);
        }));

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testSAME2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runSync(RunType.SAME, new JrbReferenceRunnable("SAME test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(true);
        }));

        Assert.assertEquals(true, ok.get());
    }

    /////////////////////
    // JAT
    /////////////////////

    @Test
    public void testJAT1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JAT, new JrbReferenceRunnable("JAT test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        }));

        checkBoolean(ok);
    }

    @Test
    public void testJAT2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JAT, "JAT test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJAT3() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JAT, "JAT test 3", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJAT4() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJAT(new JrbReferenceRunnable("JAT test 4", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        }));

        checkBoolean(ok);
    }

    @Test
    public void testJAT5() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJAT("JAT test 5", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });

        checkBoolean(ok);
    }

    // @Test
    // public void testJAT6() {
    //
    // final AtomicBoolean ok = new AtomicBoolean(false);
    //
    // JRebirth.runIntoJAT("JAT test 6", PriorityLevel.Highest, () -> {
    // LOGGER.info("Running into " + Thread.currentThread().getName());
    // ok.set(JRebirth.isJAT());
    // });
    //
    // checkBoolean(ok);
    // }

    /////////////////////
    // JTP_Sync
    /////////////////////

    @Test
    public void testJAPSync1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JAT_SYNC, new JrbReferenceRunnable("JAT_Sync test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        }));

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJATSync2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JAT_SYNC, "JAT_Sync test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJATSync3() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JAT_SYNC, "JAT_Sync test 3", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJATSync4() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJATSync(new JrbReferenceRunnable("JAT_Sync test 4", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        }));

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJATSync55() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJATSync("JAT_Sync test 51", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });
        checkBoolean(ok);

        ok.set(false);
        JRebirth.runIntoJAT("JAT_Sync test 52", () -> {
            JRebirth.runIntoJAT("JAT_Sync test 521", () -> {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJAT());
            });
        });
        checkBoolean(ok);

        ok.set(false);
        JRebirth.runIntoJAT("JAT_Sync test 53", () -> {
            JRebirth.runIntoJATSync("JAT_Sync test 531", () -> {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJAT());
            });
        });
        checkBoolean(ok);
    }

    @Test
    public void testJATSync6() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runSync(RunType.JAT, "JAT_Sync test 61", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JAT_SYNC, "JAT_Sync test 62", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JAT, "JAT_Sync test 63", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JAT_SYNC, "JAT_Sync test 64", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JAT, new JrbReferenceRunnable("JAT_Sync test 65", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        }));
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JAT_SYNC, new JrbReferenceRunnable("JAT_Sync test 66", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJAT());
        }));
        Assert.assertEquals(true, ok.get());

    }

    /////////////////////
    // JIT
    /////////////////////

    @Test
    public void testJIT1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JIT, new JrbReferenceRunnable("JIT test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        }));

        checkBoolean(ok);
    }

    @Test
    public void testJIT2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JIT, "JIT test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJIT3() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JIT, "JIT test 3", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJIT4() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT(new JrbReferenceRunnable("JIT test 4", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        }));

        checkBoolean(ok);
    }

    @Test
    public void testJIT5() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT("JIT test 5", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJIT6() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT("JIT test 6", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        checkBoolean(ok);
    }

    /////////////////////
    // JIT Sync
    /////////////////////

    @Test
    public void testJIT_Sync1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JIT_SYNC, new JrbReferenceRunnable("JIT_Sync test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        }));

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJIT_Sync2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JIT_SYNC, "JIT_Sync test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJIT_Sync3() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JIT_SYNC, "JIT_Sync test 3", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJIT_Sync4() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJITSync(new JrbReferenceRunnable("JIT_Sync test 4", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        }));

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJIT_Sync5() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJITSync("JIT_Sync test 5", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJIT_Sync6() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJITSync("JIT_Sync test 6", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJITSync7() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runSync(RunType.JIT, "JIT_Sync test 71", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JIT_SYNC, "JIT_Sync test 72", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JIT, "JIT_Sync test 73", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JIT_SYNC, "JIT_Sync test 74", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JIT, new JrbReferenceRunnable("JIT_Sync test 75", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        }));
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JIT_SYNC, new JrbReferenceRunnable("JIT_Sync test 76", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJIT());
        }));
        Assert.assertEquals(true, ok.get());

    }

    @Test
    public void testJITSync8() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJIT("JIT_Sync test 8", () -> {
            JRebirth.runIntoJITSync("JIT_Sync test 81", () -> {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJIT());
            });
        });
        checkBoolean(ok);
    }

    /////////////////////
    // JTP
    /////////////////////

    @Test
    public void testJTP1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JTP, new JrbReferenceRunnable("JTP test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        }));

        checkBoolean(ok);
    }

    @Test
    public void testJTP2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JTP, "JTP test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJTP3() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JTP, "JTP test 3", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJTP4() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP(new JrbReferenceRunnable("JTP test 4", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        }));

        checkBoolean(ok);
    }

    @Test
    public void testJTP5() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP("JTP test 5", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        checkBoolean(ok);
    }

    @Test
    public void testJTP6() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP("JTP test 6", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        checkBoolean(ok);
    }

    /////////////////////
    // JTP_Sync
    /////////////////////

    @Test
    public void testJTPSync1() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JTP_SYNC, new JrbReferenceRunnable("JTP_Sync test 1", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        }));

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJTPSync2() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JTP_SYNC, "JTP_Sync test 2", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJTPSync3() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.run(RunType.JTP_SYNC, "JTP_Sync test 3", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJTPSync4() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTPSync(new JrbReferenceRunnable("JTP_Sync test 4", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        }));

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJTPSync5() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTPSync("JTP_Sync test 5", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJTPSync6() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTPSync("JTP_Sync test 6", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });

        Assert.assertEquals(true, ok.get());
    }

    @Test
    public void testJTPSync7() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runSync(RunType.JTP, "JTP_Sync test 71", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JTP_SYNC, "JTP_Sync test 72", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JTP, "JTP_Sync test 73", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JTP_SYNC, "JTP_Sync test 74", () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        });
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JTP, new JrbReferenceRunnable("JTP_Sync test 75", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        }));
        Assert.assertEquals(true, ok.get());

        ok.set(false);
        JRebirth.runSync(RunType.JTP_SYNC, new JrbReferenceRunnable("JTP_Sync test 76", PriorityLevel.Highest, () -> {
            LOGGER.info("Running into " + Thread.currentThread().getName());
            ok.set(JRebirth.isJTPSlot());
        }));
        Assert.assertEquals(true, ok.get());

    }

    @Test
    public void testJTPSync8() {

        final AtomicBoolean ok = new AtomicBoolean(false);

        JRebirth.runIntoJTP("JTP_Sync test 8", () -> {
            JRebirth.runIntoJTPSync("JTP_Sync test 81", () -> {
                LOGGER.info("Running into " + Thread.currentThread().getName());
                ok.set(JRebirth.isJTPSlot());
            });
        });
        checkBoolean(ok);
    }

    /////////////////////
    // HPTP
    /////////////////////

    @Test
    public void testzzzHPTP() {

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
                if (JRebirth.isJTPSlot()) {
                    Thread.sleep(5000);
                }
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
        while (!ok.get() && i < 20) {
            try {
                Thread.sleep(200);
            } catch (final InterruptedException e) {
            }
            i++;
        }
        Assert.assertEquals(true, ok.get());
    }

}
