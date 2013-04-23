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
package org.jrebirth.core.concurrent;

import javafx.application.Platform;

import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.exception.JRebirthThreadException.Type;
import org.jrebirth.core.facade.GlobalFacadeBase;

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
    public static void run(final RunType runInto, final Runnable runnable) {
        switch (runInto) {
            case JAT:
                runIntoJAT(runnable);
                break;
            case JIT:
                runIntoJIT(runnable);
                break;
            case JTP:
                runIntoJTP(runnable);
                break;
            default:
        }
    }

    /**
     * Run the task into the JavaFX Application Thread [JAT].
     * 
     * @param runnable the task to run
     */
    public static void runIntoJAT(final Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            // We are into a JAT so just run it synchronously
            runnable.run();
        } else {
            // The runnable will be run into the JAT
            Platform.runLater(runnable);
        }
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
    public static void runIntoJIT(final Runnable runnable) {
        if (JRebirth.isJIT()) {
            // We are into JIT so just run it synchronously
            runnable.run();
        } else {
            // The runnable will be run into the JIT
            JRebirthThread.getThread().runLater(runnable);
        }
    }

    /**
     * Run into the JRebirth Thread Pool [JTP].
     * 
     * Be careful this method can be called through any thread.
     * 
     * @param runnable the task to run
     */
    public static void runIntoJTP(final Runnable runnable) {
        if (JRebirth.isJTPSlot()) {
            // We are into a JTP slot so just run it synchronously
            runnable.run();
        } else {
            // The runnable will be run into a JTP slot
            JRebirthThread.getThread().runIntoJTP(runnable);
        }
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
        return Thread.currentThread().getName().startsWith(GlobalFacadeBase.JTP_BASE_NAME);
    }

    /**
     * Check if the current code is executed into the JavaFX Application Thread otherwise throws an exception.
     * 
     * @throws JRebirthThreadException if we are into the wrong thread
     */
    public static void checkJAT() throws JRebirthThreadException {
        if (!Platform.isFxApplicationThread()) {
            throw new JRebirthThreadException(Type.NOT_JAT);
        }
    }

    /**
     * Check if the current code is executed into the JRebirthThread otherwise throws an exception.
     * 
     * @throws JRebirthThreadException if we are into the wrong thread
     */
    public static void checkJIT() throws JRebirthThreadException {
        if (!isJIT()) {
            throw new JRebirthThreadException(Type.NOT_JIT);
        }
    }

    /**
     * Check if the current code is executed into one slot of the JRebirth Thread Pool otherwise throws an exception.
     * 
     * @throws JRebirthThreadException if we are into the wrong thread
     */
    public static void checkJTPSlot() throws JRebirthThreadException {
        if (!isJTPSlot()) {
            throw new JRebirthThreadException(Type.NOT_JTP);
        }
    }
}
