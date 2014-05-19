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
package org.jrebirth.af.core.exception.handler;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * The class <strong>JRebirthUncaughtExceptionHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public interface JrbUncaughtExceptionHandler extends UncaughtExceptionHandler {

    /**
     * The Enum UncaughtExceptionHandlerType.
     */
    enum UncaughtExceptionHandlerType {

        /** JRebirth Internal Thread Uncaught Exception Handler. */
        JITHandler,

        /** JavaFX Application Thread Uncaught Exception Handler. */
        JATHandler,

        /** JRebirth Thread Pool Uncaught Exception Handler. */
        PoolHandler,

        /** Default Thread Uncaught Exception Handler. */
        DefaultHandler
    }

    /**
     * Gets the uncaught exception handler type.
     *
     * @return the uncaught exception handler type
     */
    UncaughtExceptionHandlerType getUncaughtExceptionHandlerType();

}
