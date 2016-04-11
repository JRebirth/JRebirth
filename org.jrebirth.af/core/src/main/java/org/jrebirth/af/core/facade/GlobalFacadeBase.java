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
package org.jrebirth.af.core.facade;

import org.jrebirth.af.api.application.JRebirthApplication;
import org.jrebirth.af.api.component.factory.ComponentFactory;
import org.jrebirth.af.api.concurrent.IJRebirthThreadPoolExecutor;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.api.facade.JRebirthEvent;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.api.link.Notifier;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.core.application.AbstractApplication;
import org.jrebirth.af.core.component.factory.DefaultComponentFactory;
import org.jrebirth.af.core.concurrent.JRebirthThreadPoolExecutor;
import org.jrebirth.af.core.link.NotifierBase;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;
import org.jrebirth.af.core.resource.provided.parameter.ExtensionParameters;
import org.jrebirth.af.core.util.ParameterUtility;

/**
 *
 * The class <strong>GlobalFacadeBase</strong>.
 *
 * This class is a facade that helps to retrieve any JRebirth RIA pattern component of the application.
 *
 * It uses 3 facades that store services, commands and models of the application. It allows to dispatch waves to all required components.
 *
 *
 * @author Sébastien Bordes
 */
public class GlobalFacadeBase implements GlobalFacade, FacadeMessages {

    /** The JRebirth Thread Pool base name [JTP]. */
    public static final String JTP_BASE_NAME = "JTP Slot ";

    /** The High Priority Thread Pool base name [HPTP]. */
    public static final String HPTP_BASE_NAME = "HPTP Slot ";

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(GlobalFacadeBase.class);

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

    /** The facade for Behaviors components. */
    private final transient BehaviorFacade behaviorFacade;

    /** The default executor. */
    private final IJRebirthThreadPoolExecutor executorService;

    /** The high priority executor used only for most important runnable. */
    private final IJRebirthThreadPoolExecutor highPriorityExecutorService;

    /** The index of JRebirth events. */
    private int eventSequence;

    /**
     * Default Constructor. Initialize all facades.
     *
     * @param application the current application launched
     */
    public GlobalFacadeBase(final JRebirthApplication<?> application) {
        super();

        // Attach the right EnhancedComponent Factory
        this.componentFactory = (ComponentFactory) ParameterUtility.buildCustomizableClass(ExtensionParameters.COMPONENT_FACTORY, DefaultComponentFactory.class, ComponentFactory.class);

        // Link the application
        this.application = application;
        trackEvent(JRebirthEventType.CREATE_APPLICATION, null, application().getClass());

        LOGGER.trace(JTP_CREATION);

        final int poolSize = Math.max(1, Math.round(CoreParameters.THREAD_POOL_SIZE_RATIO.get() * Runtime.getRuntime().availableProcessors()));

        // Launch the default executor
        this.executorService = new JRebirthThreadPoolExecutor(poolSize,
                                                              new NamedThreadBuilder(((AbstractApplication<?>) application).getPoolUncaughtExceptionHandler(), JTP_BASE_NAME));

        // Launch the High Priority executor
        this.highPriorityExecutorService = new JRebirthThreadPoolExecutor(poolSize,
                                                                          new NamedThreadBuilder(((AbstractApplication<?>) application).getPoolUncaughtExceptionHandler(), HPTP_BASE_NAME));

        trackEvent(JRebirthEventType.CREATE_GLOBAL_FACADE, application().getClass(), this.getClass());

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

        // Build the Behavior Facade
        this.behaviorFacade = buildBehaviorFacade();
        trackEvent(JRebirthEventType.CREATE_BEHAVIOR_FACADE, getClass(), this.behaviorFacade.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void trackEvent(final JRebirthEventType eventType, final Class<?> source, final Class<?> target, final String... eventData) {
        if (LOGGER.isInfoEnabled()) {
            final JRebirthEvent event = new JRebirthEventBase(this.eventSequence++, eventType,
                                                              source == null ? null : source.getCanonicalName(),
                                                              target == null ? null : target.getCanonicalName(), eventData);
            LOGGER.info(JREBIRTH_EVENT, event);
        }
    }

    /**
     * @return Returns the componentFactory.
     */
    @Override
    public ComponentFactory componentFactory() {
        return this.componentFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final JRebirthApplication<?> application() {
        return this.application;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Notifier notifier() {
        return this.notifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final UiFacade uiFacade() {
        return this.uiFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ServiceFacade serviceFacade() {
        return this.serviceFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final CommandFacade commandFacade() {
        return this.commandFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final BehaviorFacade behaviorFacade() {
        return this.behaviorFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IJRebirthThreadPoolExecutor executorService() {
        return this.executorService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IJRebirthThreadPoolExecutor highPriorityExecutorService() {
        return this.highPriorityExecutorService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stop() throws CoreException {

        // Manage design for extension
        customStop();

        // Stop the thread pool
        if (executorService() != null) {
            executorService().shutdown();
        }

        // Stop the high priority thread pool
        if (highPriorityExecutorService() != null) {
            highPriorityExecutorService().shutdown();
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
     * Build the BehaviorFacade singleton to use.
     *
     * Can be overridden by sub class to customize its facade behaviors.
     *
     * @return the behaviors facade
     */
    protected BehaviorFacade buildBehaviorFacade() {
        return new BehaviorFacade(this);
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
