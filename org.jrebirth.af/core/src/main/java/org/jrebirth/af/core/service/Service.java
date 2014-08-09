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
package org.jrebirth.af.core.service;

import java.util.Collection;

import javafx.collections.ObservableMap;

import org.jrebirth.af.core.facade.WaveReady;
import org.jrebirth.af.core.wave.Wave;

/**
 * The interface <strong>Service</strong>.
 *
 * The contract for the service layer.
 *
 * @author Sébastien Bordes
 */
public interface Service extends WaveReady<Service> {

    /**
     * Do a specific action by processing the wave.
     *
     * @param wave the wave that hold all related data
     *
     * @return the service task created that will perform the job
     *
     * @param <T> The type of the object to return must be compatible with the WaveType contract that call this service
     */
    <T extends Object> ServiceTask<T> returnData(final Wave wave);

    /**
     * Return The Pending tasks map.<br/>
     * Key is the wave UID, value is the {@link ServiceTask}
     *
     * @return the pending tasks map
     */
    ObservableMap<String, ServiceTask<?>> pendingTasksProperty();

    /**
     * Get pending task list.
     *
     * @return the list of pending tasks
     */
    Collection<ServiceTask<?>> getPendingTaskList();

    /**
     * Remove a task from the pending list.
     *
     * @param taskKey the key of the task, commonly the WaveUID
     */
    void removePendingTask(final String taskKey);

    /**
     * Retrieve a task from the pending list.
     *
     * @param taskKey the key of the task, commonly the WaveUID
     *
     * @return the pending task for the given key (basically the Wave UID)
     */
    ServiceTask<?> getPendingTask(final String taskKey);

}
