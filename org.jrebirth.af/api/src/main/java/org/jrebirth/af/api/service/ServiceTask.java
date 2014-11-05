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
package org.jrebirth.af.api.service;

import javafx.concurrent.Worker;

/**
 * The class <strong>ServiceTask</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <T> the current Service Task type
 */
public interface ServiceTask<T> extends Worker<T> {

    /**
     * Return the full service handler name.
     *
     * ServiceName + method + ( parameters types )
     *
     * @return the full service handler name
     */
    String getServiceHandlerName();

    /**
     * The task has been terminated because the source wave was consumed or has failed.
     *
     * Remove the task from the service pending list
     */
    void taskAchieved();

    /**
     * Check if the task has enough progressed according to the given threshold.
     *
     * This method can be called outside the JAT, it's useful to filter useless call to JAT
     *
     * @param newWorkDone the amount of work done
     * @param totalWork the total amount of work
     * @param amountThreshold the minimum threshold amount to return true; range is [0.0 - 100.0] (typically 1.0 for 1%)
     *
     * @return true if the threshold is reached
     */
    boolean checkProgressRatio(final double newWorkDone, final double totalWork, final double amountThreshold);

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // RunnablePriority getPriority() ;
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // Instant getCreationTime() ;

}
