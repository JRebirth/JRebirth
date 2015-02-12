/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.concurrent;

import javafx.application.Platform;

import org.jrebirth.af.api.concurrent.JRebirthRunnable;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.exception.JRebirthThreadException;
import org.jrebirth.af.core.facade.GlobalFacadeBase;

/**
 * The class <strong>JRebirth</strong>.
 *
 * A set of useful static method to manage conveniently Threads.
 *
 * @author Sébastien Bordes
 */
public final class JRebirth {

    /**
     * Private Constructor.
     */
    private JRebirth() {
        super();
    }

    /**
     * Run the task into the appropriated thread.
     *
     * @param runInto the targeted thread
     * @param runnable the task to run
     */
    public static void run(final RunType runInto, final JRebirthRunnable runnable) {
        switch (runInto) {
            case JAT:
                runIntoJAT(runnable);
                break;
            case JAT_SYNC:
                runIntoJATSync(runnable);// TODO timeout
                break;
            case JIT:
                runIntoJIT(runnable);
                break;
            case JIT_SYNC:
                runIntoJITSync(runnable);// TODO timeout
                break;
            case JTP:
                runIntoJTP(runnable);
                break;
            case SAME:
            default:
                runnable.run();
        }
    }

    /**
     * Run the task into the JavaFX Application Thread [JAT].
     *
     * @param runnable the task to run
     */
    public static void runIntoJAT(final JRebirthRunnable runnable) {
        if (Platform.isFxApplicationThread()) {
            // We are into a JAT so just run it synchronously
            runnable.run();
        } else {
            // The runnable will be run into the JAT during the next round
            Platform.runLater(runnable);
        }
    }

    /**
     * Run the task into the JavaFX Application Thread [JAT].
     *
     * @param runnable the task to run
     */
    public static void runIntoJATSync(final JRebirthRunnable runnable, final long... timeout) {
        final SyncRunnable sync = new SyncRunnable(runnable);

        if (Platform.isFxApplicationThread()) {
            // We are into a JAT so just run it synchronously
            sync.run();
        } else {
            // The runnable will be run into the JAT during the next round
            Platform.runLater(sync);
        }

        // Wait the end of the runnable execution
        sync.waitEnd(timeout);
    }

    /**
     * Run into the JRebirth Internal Thread [JIT].
     *
     * Actually only few methods are allowed to execute themselves into JRebirthThread.
     * <ul>
     * <li>Uncaught Exception Handler initialization</li>
     * <li>Wave queuing</li>
     * <li>Run a default Command (neither UI nor Pooled)</li>
     * <li>Listen a Wave Type</li>
     * <li>UnListen a Wave Type</li>
     * </ul>
     *
     * Be careful this method can be called through any thread.
     *
     * @param runnable the task to run
     */
    public static void runIntoJIT(final JRebirthRunnable runnable) {
        // if (JRebirth.isJIT()) {
        // // We are into JIT so just run it synchronously
        // runnable.run();
        // } else {
        // The runnable will be run into the JIT during the next round
        JRebirthThread.getThread().runLater(runnable);
        // }
    }

    /**
     * Run the task into the JavaFX Application Thread [JAT].
     *
     * @param runnable the task to run
     */
    public static void runIntoJITSync(final JRebirthRunnable runnable, final long... timeout) {
        final SyncRunnable sync = new SyncRunnable(runnable);

        if (JRebirth.isJIT()) {
            // We are into a JIT so just run it synchronously
            sync.run();
        } else {
            // The runnable will be run into the JIT during the next round
            JRebirthThread.getThread().runLater(sync);
        }
        // Wait the end of the runnable execution
        sync.waitEnd(timeout);
    }

    /**
     * Run into the JRebirth Thread Pool [JTP].
     *
     * Be careful this method can be called through any thread.
     *
     * @param runnable the task to run
     */
    public static void runIntoJTP(final JRebirthRunnable runnable) {
        // if (JRebirth.isJTPSlot()) {
        // // We are into a JTP slot so just run it synchronously
        // runnable.run();
        // } else {
        // The runnable will be run into a JTP slot during the next round
        JRebirthThread.getThread().runIntoJTP(runnable);
        // }
    }

    /**
     * Run into the JRebirth Thread Pool [JTP].
     *
     * Be careful this method can be called through any thread.
     *
     * @param runnable the task to run
     */
    public static void runIntoJTPSync(final JRebirthRunnable runnable, final long... timeout) {
        final SyncRunnable sync = new SyncRunnable(runnable);

        if (JRebirth.isJTPSlot()) {
            // We are into a JTP slot so just run it synchronously
            sync.run();
        } else {
            // The runnable will be run into the JTP slot during the next round
            JRebirthThread.getThread().runIntoJTP(sync);
        }
        // Wait the end of the runnable execution
        sync.waitEnd(timeout);
    }

    /**
     * Return true if we are into the JavaFX Application Thread (JAT).
     *
     * @return true if currentThread == JRebirthThread
     */
    public static boolean isJAT() {
        return Platform.isFxApplicationThread();
    }

    /**
     * Return true if we are into the JRebirth Internal Thread (JIT).
     *
     * @return true if currentThread == JRebirthThread
     */
    public static boolean isJIT() {
        return JRebirthThread.JIT_NAME.equals(Thread.currentThread().getName());
    }

    /**
     * Return true if we are into a slot of the thread pool (JTP).
     *
     * @return true if currentThread == one of JTP slot
     */
    public static boolean isJTPSlot() {
        return Thread.currentThread().getName().startsWith(GlobalFacadeBase.JTP_BASE_NAME)
                || Thread.currentThread().getName().startsWith(GlobalFacadeBase.HPTP_BASE_NAME);
    }

    /**
     * Check if the current code is executed into the JavaFX Application Thread otherwise throws an exception.
     *
     * @throws JRebirthThreadException if we are into the wrong thread
     */
    public static void checkJAT() throws JRebirthThreadException {
        if (!Platform.isFxApplicationThread()) {
            throw new JRebirthThreadException(JRebirthThreadException.Type.NOT_RUN_INTO_JAT);
        }
    }

    /**
     * Check if the current code is executed into the JRebirthThread otherwise throws an exception.
     *
     * @throws JRebirthThreadException if we are into the wrong thread
     */
    public static void checkJIT() throws JRebirthThreadException {
        if (!isJIT()) {
            throw new JRebirthThreadException(JRebirthThreadException.Type.NOT_RUN_INTO_JIT);
        }
    }

    /**
     * Check if the current code is executed into one slot of the JRebirth Thread Pool otherwise throws an exception.
     *
     * @throws JRebirthThreadException if we are into the wrong thread
     */
    public static void checkJTPSlot() throws JRebirthThreadException {
        if (!isJTPSlot()) {
            throw new JRebirthThreadException(JRebirthThreadException.Type.NOT_RUN_INTO_JTP);
        }
    }
}
