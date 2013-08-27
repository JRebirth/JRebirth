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

import org.jrebirth.core.application.AbstractApplication;
import org.jrebirth.core.application.JRebirthApplication;
import org.jrebirth.core.concurrent.JRebirthThreadPoolExecutor;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.Notifier;
import org.jrebirth.core.link.NotifierBase;
import org.jrebirth.core.resource.provided.JRebirthParameters;
import org.jrebirth.core.util.ClassUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    /** The <code>EVENT_TRACKED</code> field is used to log a JRebirth Event. */
    public static final String EVENT_TRACKED = "JREvent: ";

    /** The JRebirth Thread Pool base name [JTP]. */
    public static final String JTP_BASE_NAME = "JTP Slot ";

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalFacadeBase.class);

    /** The factory used to build all component. */
    private final ComponentFactory componentFactory;

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

    /** The default executor. */
    private final ExecutorService executorService;

    /** The index of JRebirth events. */
    private int eventSequence;

    /**
     * Default Constructor. Initialize all facades.
     * 
     * @param application the current application launched
     */
    public GlobalFacadeBase(final JRebirthApplication<?> application) {
        super();

        ComponentFactory factory;
        try {
            factory = (ComponentFactory) JRebirthParameters.COMPONENT_FACTORY.get().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Impossible to load the custom ComponentFactory, will use the default one", e);
            factory = new DefaultComponentFactory();
        }
        // Attach the right Component Factory
        this.componentFactory = factory;

        // Link the application
        this.application = application;
        trackEvent(org.jrebirth.core.facade.JRebirthEventType.CREATE_APPLICATION, null, getApplication().getClass());

        LOGGER.trace("Create the JRebirth Thread Pool");
        // Launch the default executor
        this.executorService = new JRebirthThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
                new NamedThreadBuilder(((AbstractApplication<?>) application).getPoolUncaughtExceptionHandler(), JTP_BASE_NAME));

        trackEvent(org.jrebirth.core.facade.JRebirthEventType.CREATE_GLOBAL_FACADE, getApplication().getClass(), this.getClass());

        // Build the notifier manager
        this.notifier = buildNotifier();
        trackEvent(JRebirthEventType.CREATE_NOTIFIER, getClass(), this.notifier.getClass());

        // Build the Command Facade
        this.commandFacade = buildCommandFacade();
        trackEvent(JRebirthEventType.CREATE_COMMAND_FACADE, getClass(), this.commandFacade.getClass());

        // Build the Service Facade
        this.serviceFacade = buildServiceFacade();
        trackEvent(JRebirthEventType.CREATE_SERVICE_FACADE, getClass(), this.serviceFacade.getClass());

        // Build the Ui Facade
        this.uiFacade = buildUiFacade();
        trackEvent(JRebirthEventType.CREATE_UI_FACADE, getClass(), this.uiFacade.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void trackEvent(final JRebirthEventType eventType, final Class<?> source, final Class<?> target, final String... eventData) {
        if (LOGGER.isInfoEnabled()) {
            final StringBuilder sb = new StringBuilder();
            sb.append(EVENT_TRACKED);
            sb.append(this.eventSequence++);
            sb.append(ClassUtility.SEPARATOR);
            sb.append(eventType);
            sb.append(ClassUtility.SEPARATOR);
            sb.append(source == null ? null : source.getCanonicalName());
            sb.append(ClassUtility.SEPARATOR);
            sb.append(target == null ? null : target.getCanonicalName());
            sb.append(ClassUtility.SEPARATOR);
            sb.append(eventData.length > 0 ? eventData[0] : "");
            LOGGER.info(sb.toString());
        }
    }

    /**
     * @return Returns the componentFactory.
     */
    @Override
    public ComponentFactory getComponentFactory() {
        return this.componentFactory;
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
    @Override
    public final void stop() throws CoreException {

        // Manage design for extension
        customStop();

        // Stop the thread pool
        if (getExecutorService() != null) {
            getExecutorService().shutdown();
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
