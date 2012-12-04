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
import java.util.concurrent.Executors;

import org.jrebirth.core.application.AbstractApplication;
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
 * The class <strong>GlobalFacadeBase</strong>.
 * 
 * This class is a facade taht helps to retrive any JRebirth RIA pattern component of the application.
 * 
 * It uses 3 facades that store services, commands and models of the application. It allows to dispatch waves to all required components.
 * 
 * 
 * @author Sébastien Bordes
 */
public class GlobalFacadeBase implements GlobalFacade {

    /** The JRebirth Thread Pool base name [JTP]. */
    public static final String JTP_BASE_NAME = "JTP Slot ";

    /** The application. */
    private final transient JRebirthApplication<?> application;

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
    public GlobalFacadeBase(final JRebirthApplication<?> application) {
        super();

        // Launch the default executor
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2,
                new NamedThreadBuilder(((AbstractApplication<?>) application).getPoolUncaughtExceptionHandler(this), JTP_BASE_NAME));

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
    public final JRebirthApplication<?> getApplication() {
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
    // @Override
    // public final JRebirthLogger getLogger() {
    // return JRebirthLogger.getInstance();
    // }

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

        // Stop the thread pool
        if (getExecutorService() != null) {
            getExecutorService().shutdown();
        }

        // Close log files or streams
        // if (getLogger() != null) {
        // getLogger().closeOutputStream();
        // }

        // Close the JRebirth events logged
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
     * Build the CommandFacade singleton to use.
     * 
     * Can be overridden by sub class to customize its behaviors.
     * 
     * @return the command facade
     */
    protected CommandFacade buildCommandFacade() {
        return new CommandFacade(this);
    }

    /**
     * Build the ServiceFacade singleton to use.
     * 
     * Can be overridden by sub class to customize its behaviors.
     * 
     * @return the service facade
     */
    protected ServiceFacade buildServiceFacade() {
        return new ServiceFacade(this);
    }

    /**
     * Build the UiFacade singleton to use.
     * 
     * Can be overridden by sub class to customize its behaviors.
     * 
     * @return the ui facade
     */
    protected UiFacade buildUiFacade() {
        return new UiFacade(this);
    }

    /**
     * Build the notifier singleton to use.
     * 
     * Can be overridden by sub class to customize its behaviors.
     * 
     * @return the notifier
     */
    protected NotifierBase buildNotifier() {
        return new NotifierBase(this);
    }

}
