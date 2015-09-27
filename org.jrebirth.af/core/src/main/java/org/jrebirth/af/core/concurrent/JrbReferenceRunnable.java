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

import org.jrebirth.af.api.concurrent.JRebirthRunnable;
import org.jrebirth.af.api.concurrent.RunnablePriority;
import org.jrebirth.af.api.exception.JRebirthThreadException;

/**
 * The class <strong>JrbReferenceRunnable</strong> use to wrap a method reference.
 *
 * @author Sébastien Bordes
 */
public class JrbReferenceRunnable extends AbstractJrbRunnable {

    private final JRebirthRunnable runnable;

    /**
     * @param runnableName
     * @param priority
     */
    public JrbReferenceRunnable(final String runnableName, final RunnablePriority priority, final JRebirthRunnable runnable) {
        super(runnableName, priority);
        this.runnable = runnable;
    }

    /**
     * @param runnableName
     */
    public JrbReferenceRunnable(final String runnableName, final JRebirthRunnable runnable) {
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
