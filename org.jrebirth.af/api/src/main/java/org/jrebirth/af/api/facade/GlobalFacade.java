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
package org.jrebirth.af.api.facade;

import org.jrebirth.af.api.application.JRebirthApplication;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.component.factory.ComponentFactory;
import org.jrebirth.af.api.concurrent.IJRebirthThreadPoolExecutor;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.link.Notifier;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;

/**
 * The interface <strong>GlobalFacade</strong>.
 *
 * Used to manage all local facade.
 *
 * @author Sébastien Bordes
 */
public interface GlobalFacade {

    /**
     * @return Returns the componentFactory.
     */
    ComponentFactory componentFactory();

    /**
     * @return Returns the notifier.
     */
    Notifier notifier();

    /**
     * @return Returns the uiFacade.
     */
    LocalFacade<Model> uiFacade();

    /**
     * @return Returns the serviceFacade.
     */
    LocalFacade<Service> serviceFacade();

    /**
     * @return Returns the commandFacade.
     */
    LocalFacade<Command> commandFacade();

    /**
     * @return Returns the behaviorFacade.
     */
    LocalFacade<Behavior<?, ?>> behaviorFacade();

    /**
     * @return the application
     */
    JRebirthApplication<?> application();

    /**
     * @return Returns the executorService.
     */
    IJRebirthThreadPoolExecutor executorService();

    /**
     * @return Returns the highPriorityExecutorService.
     */
    IJRebirthThreadPoolExecutor highPriorityExecutorService();

    /**
     * Track an event.
     *
     * @param eventType the type of the event
     * @param source the source class of the event
     * @param target the target class of the event
     * @param eventData the data of the vent (like class name or other)
     */
    void trackEvent(final JRebirthEventType eventType, final Class<?> source, final Class<?> target, final String... eventData);

    /**
     * Propagate the stop action into the global facade.
     *
     * @throws CoreException if an error occurred while closing a stream
     */
    void stop() throws CoreException;

}
