/**
 * Copyright JRebirth.org © 2011-2012 
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
import java.util.concurrent.Executors;

import org.jrebirth.core.application.JRebirthApplication;
import org.jrebirth.core.event.EventBase;
import org.jrebirth.core.event.EventTracker;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.event.JRebirthLogger;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.Notifier;
import org.jrebirth.core.link.NotifierBase;

/**
 * 
 * The class <strong>GlobalFacadeImpl</strong>.
 * 
 * This class is a facade taht helps to retrive any JRebirth RIA pattern component of the application.
 * 
 * It uses 3 facades that store services, commands and models of the application. It allows to dispatch waves to all required components.
 * 
 * 
 * @author Sébastien Bordes
 */
public class GlobalFacadeBase implements GlobalFacade {

    /** The application. */
    private final transient JRebirthApplication application;

    /** The notifier that dispatch waves. */
    private final transient Notifier notifier;

    /** The facade for UI components. */
    private final transient UiFacade uiFacade;

    /** The facade for Services components. */
    private final transient ServiceFacade serviceFacade;

    /** The facade for Commands components. */
    private final transient CommandFacade commandFacade;

    /** The EventTracker to track application events. */
    private final transient EventTracker eventTracker;

    /** The default executor. */
    private final ExecutorService executorService;

    /**
     * Default Constructor. Initialize all facades.
     * 
     * @param application the current application launched
     */
    public GlobalFacadeBase(final JRebirthApplication application) {
        super();

        // Launch the default executor
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        // Manage internal logging
        JRebirthLogger.getInstance().setEnabled(application.isLoggerEnabled());

        // Manage internal event tracking
        this.eventTracker = application.isEventTrackerEnabled() ? new EventTracker() : null;

        // Link the application
        this.application = application;
        trackEvent(org.jrebirth.core.event.EventType.CREATE_APPLICATION, null, getApplication().getClass());
        trackEvent(org.jrebirth.core.event.EventType.CREATE_GLOBAL_FACADE, getApplication().getClass(), this.getClass());

        // Build singletons
        this.notifier = buildNotifier();
        this.uiFacade = buildUiFacade();
        this.serviceFacade = buildServiceFacade();
        this.commandFacade = buildCommandFacade();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void trackEvent(final EventType eventType, final Class<?> source, final Class<?> target, final String... eventData) {
        if (getEventTracker() != null) {
            getEventTracker().track(new EventBase(eventType, source, target, eventData));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final JRebirthApplication getApplication() {
        return this.application;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Notifier getNotifier() {
        return this.notifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final UiFacade getUiFacade() {
        return this.uiFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ServiceFacade getServiceFacade() {
        return this.serviceFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final CommandFacade getCommandFacade() {
        return this.commandFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final JRebirthLogger getLogger() {
        return JRebirthLogger.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EventTracker getEventTracker() {
        return this.eventTracker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stop() throws CoreException {

        // Manage design for extension
        customStop();

        if (getExecutorService() != null) {
            getExecutorService().shutdown();
        }

        if (getLogger() != null) {
            getLogger().closeOutputStream();
        }

        if (getEventTracker() != null) {
            getEventTracker().closeOutputStream();
        }

    }

    /**
     * Method to override to do some stuff when application stops.
     */
    protected void customStop() {
        // Must be overridden
    }

    /**
     * TODO To complete.
     * 
     * @return
     */
    protected CommandFacade buildCommandFacade() {
        return new CommandFacade(this);
    }

    /**
     * TODO To complete.
     * 
     * @return
     */
    protected ServiceFacade buildServiceFacade() {
        return new ServiceFacade(this);
    }

    /**
     * TODO To complete.
     * 
     * @return
     */
    protected UiFacade buildUiFacade() {
        return new UiFacade(this);
    }

    /**
     * TODO To complete.
     * 
     * @return
     */
    protected NotifierBase buildNotifier() {
        return new NotifierBase(this);
    }

}
