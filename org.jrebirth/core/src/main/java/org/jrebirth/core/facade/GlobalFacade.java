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
package org.jrebirth.core.facade;

import java.util.concurrent.ExecutorService;

import org.jrebirth.core.application.JRebirthApplication;
import org.jrebirth.core.command.Command;
import org.jrebirth.core.event.EventTracker;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.Notifier;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.Model;

/**
 * The interface <strong>GlobalFacade</strong>.
 * 
 * Used to manage all local facade.
 * 
 * @author Sébastien Bordes
 */
public interface GlobalFacade {

    /**
     * @return Returns the notifier.
     */
    Notifier getNotifier();

    /**
     * @return Returns the uiFacade.
     */
    Facade<Model> getUiFacade();

    /**
     * @return Returns the serviceFacade.
     */
    Facade<Service> getServiceFacade();

    /**
     * @return Returns the commandFacade.
     */
    Facade<Command> getCommandFacade();

    /**
     * @return the application
     */
    JRebirthApplication getApplication();

    /**
     * @return Returns the executorService.
     */
    ExecutorService getExecutorService();

    /**
     * @return the logger
     */
    // JRebirthLogger getLogger();

    /**
     * @return the event tracker
     */
    EventTracker getEventTracker();

    /**
     * Track an event.
     * 
     * @param eventType the type of the event
     * @param source the source class of the event
     * @param target the target class of the event
     * @param eventData the data of the vent (like class name or other)
     */
    void trackEvent(EventType eventType, Class<?> source, Class<?> target, String... eventData);

    /**
     * Propagate the stop action into the global facade.
     * 
     * @throws CoreException if an error occurred while closing a stream
     */
    void stop() throws CoreException;

}
