package org.jrebirth.core.facade;

import org.jrebirth.core.application.JRebirthApplication;
import org.jrebirth.core.command.Command;
import org.jrebirth.core.event.EventTracker;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.event.JRebirthLogger;
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
 * 
 * @version $Revision$ $Author$
 * @since $Date$
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
     * @return the logger
     */
    JRebirthLogger getLogger();

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
