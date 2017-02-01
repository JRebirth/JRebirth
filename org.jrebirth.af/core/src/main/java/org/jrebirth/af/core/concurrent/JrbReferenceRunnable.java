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

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.exception.JRebirthThreadException;

/**
 * The class <strong>JrbReferenceRunnable</strong> use to wrap a method reference.
 *
 * @author Sébastien Bordes
 */
public class JrbReferenceRunnable extends AbstractJrbRunnable {

    /** The runnable to run into the right thread. */
    private final Runnable runnable;

    /**
     * Default constructor using name and priority.
     *
     * @param runnableName the name of the runnable for log purpose
     * @param priority the runnable priority to try to apply
     * @param runnable the runnable to perform into the right thread
     */
    public JrbReferenceRunnable(final String runnableName, final PriorityLevel priority, final Runnable runnable) {
        super(runnableName, priority);
        this.runnable = runnable;
    }

    /**
     * Default constructor using name and Normal priority.
     *
     * @param runnableName the name of the runnable for log purpose
     * @param runnable the runnable to perform into the right thread
     */
    public JrbReferenceRunnable(final String runnableName, final Runnable runnable) {
        super(runnableName);
        this.runnable = runnable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runInto() throws JRebirthThreadException {
        this.runnable.run();
    }

}
