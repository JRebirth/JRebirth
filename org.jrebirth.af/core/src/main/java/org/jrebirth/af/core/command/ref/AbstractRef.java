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
package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.concurrent.RunnablePriority;

/**
 * The Class AbstractRef.
 *
 * @param <R> the generic type
 */
public abstract class AbstractRef<R extends AbstractRef<R>> implements Ref {

    /** The run type. */
    private RunType runType;

    /** The priority. */
    private RunnablePriority priority;

    /**
     * Run into.
     *
     * @return the run type
     */
    public RunType runInto() {
        return this.runType;
    }

    /**
     * Run into.
     *
     * @param runType the run type
     * @return the r
     */
    public R runInto(final RunType runType) {
        this.runType = runType;
        return (R) this;
    }

    /**
     * Priority.
     *
     * @return the runnable priority
     */
    public RunnablePriority priority() {
        return this.priority;
    }

    /**
     * Priority.
     *
     * @param priority the priority
     * @return the r
     */
    public R priority(final RunnablePriority priority) {
        this.priority = priority;
        return (R) this;
    }

}
