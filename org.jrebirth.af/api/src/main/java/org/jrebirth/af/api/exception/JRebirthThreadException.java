/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.api.exception;

/**
 *
 * The class <strong>JRebirthThreadException</strong>.
 *
 * This is the exception that can be thrown if JRebirth code is executed outside the right JRebirth Thread.
 *
 * @author Sébastien Bordes
 */
public class JRebirthThreadException extends Exception {

    /**
     * The constant used for serialization.
     */
    private static final long serialVersionUID = 112036725331588469L;

    /**
     * The type of thread exception.
     */
    public enum Type {
        /** The related code must be run into JAT. */
        NOT_RUN_INTO_JAT,
        /** The related code must be run into JIT. */
        NOT_RUN_INTO_JIT,
        /** The related code must be run into JTP. */
        NOT_RUN_INTO_JTP
    }

    /** The local thread type. */
    private final Type threadType;

    /**
     * Default Constructor.
     *
     * @param threadType the type of error to thrown.
     */
    public JRebirthThreadException(final Type threadType) {
        super("Current code must be executed into: " + getThreadName(threadType));
        this.threadType = threadType;
    }

    /**
     * Return the concerned thread name.
     *
     * @param threadType the thread type concerned
     *
     * @return the thread name concerned
     */
    private static String getThreadName(final Type threadType) {
        String threadName;

        switch (threadType) {
            case NOT_RUN_INTO_JAT:
                threadName = "JavaFX Application Thread";
                break;
            case NOT_RUN_INTO_JIT:
                threadName = "JRebirth Internal Thread";
                break;
            case NOT_RUN_INTO_JTP:
                threadName = "JRebirth Thread Pool";
                break;
            default:
                threadName = "";
        }
        return threadName;
    }

    /**
     * @return Returns the threadType.
     */
    Type getThreadType() {
        return this.threadType;
    }

}
