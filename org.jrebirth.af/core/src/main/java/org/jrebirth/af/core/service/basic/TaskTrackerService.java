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
package org.jrebirth.af.core.service.basic;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import org.jrebirth.af.core.service.DefaultService;
import org.jrebirth.af.core.service.ServiceTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>TaskTrackerService</strong>.
 *
 * @author Sébastien Bordes
 */
public class TaskTrackerService extends DefaultService {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskTrackerService.class);

    /** The list of all running service tasks. */
    private final ObservableList<ServiceTask<?>> serviceTasks = FXCollections.observableList(new ArrayList<ServiceTask<?>>());

    /** The unique handler used to remove a pending task. */
    private final EventHandler<WorkerStateEvent> workerHandler = new EventHandler<WorkerStateEvent>() {

        @Override
        public void handle(final WorkerStateEvent event) {

            TaskTrackerService.this.serviceTasks.remove(event.getSource());
        }
    };

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void ready() throws CoreException {
    // super.ready(); // Can be omitted but it's a bad practice
    //
    // }

    /**
     * Track a task progression.
     *
     * @param task the task to track
     */
    public void trackTask(final ServiceTask<?> task) {

        LOGGER.trace("track a Task");

        this.serviceTasks.add(task);

        task.setOnCancelled(this.workerHandler);
        task.setOnSucceeded(this.workerHandler);
        task.setOnFailed(this.workerHandler);
    }

    /**
     * @return Returns the serviceTasks.
     */
    public ObservableList<ServiceTask<?>> getServiceTasks() {
        return this.serviceTasks;
    }

}
