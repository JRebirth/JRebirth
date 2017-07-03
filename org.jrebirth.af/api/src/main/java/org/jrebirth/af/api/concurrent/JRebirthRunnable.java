/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.api.concurrent;

import java.time.Instant;

import org.jrebirth.af.api.annotation.PriorityLevel;

/**
 * The class <strong>JRebirthRunnable</strong>.
 *
 * @author Sébastien Bordes
 */
@FunctionalInterface
public interface JRebirthRunnable extends Runnable {

    /** The default text used for unnamed runnable. */
    String UNNAMED_RUNNABLE = "Unnamed Runnable";

    /**
     * Return the runnable name used to distinguish all runnable.
     *
     * @return the runnable name
     */
    default String runnableName() {
        return UNNAMED_RUNNABLE;
    }

    /**
     * Return the runnable priority.
     *
     * @return the runnable priority
     */
    default PriorityLevel priority() {
        return PriorityLevel.Normal;
    }

    /**
     * Return the creation time.
     *
     * @return the creation time in milliseconds
     */
    default Instant creationTime() {
        return Instant.now();
    }

}
