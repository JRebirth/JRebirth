package org.jrebirth.core.facade;

import org.jrebirth.core.application.JRebirthApplication;
import org.jrebirth.core.event.EventImpl;
import org.jrebirth.core.event.EventTracker;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.event.JRebirthLogger;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.Notifier;
import org.jrebirth.core.link.NotifierImpl;

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
 * 
 * @version $Revision: 171 $ $Author: sbordes $
 * @since $Date: 2011-11-09 13:17:31 +0100 (mer., 09 nov. 2011) $
 */
public class GlobalFacadeImpl implements GlobalFacade {

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
    // private final ExecutorService executorService;

    /**
     * Default Constructor. Initialize all facades.
     * 
     * @param application the current application launched
     */
    public GlobalFacadeImpl(final JRebirthApplication application) {
        super();

        // Launch the default executor
        // this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        // this.executorService.

        // Manage internal logging
        JRebirthLogger.getInstance().setEnabled(application.isLoggerEnabled());

        // Manage internal event tracking
        this.eventTracker = application.isEventTrackerEnabled() ? new EventTracker() : null;

        // Link the application
        this.application = application;
        trackEvent(org.jrebirth.core.event.EventType.CREATE_APPLICATION, null, getApplication().getClass());
        trackEvent(org.jrebirth.core.event.EventType.CREATE_GLOBAL_FACADE, getApplication().getClass(), this.getClass());

        // Build singletons
        this.notifier = new NotifierImpl(this);
        this.uiFacade = new UiFacade(this);
        this.serviceFacade = new ServiceFacade(this);
        this.commandFacade = new CommandFacade(this);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void trackEvent(final EventType eventType, final Class<?> source, final Class<?> target, final String... eventData) {
        if (getEventTracker() != null) {
            getEventTracker().track(new EventImpl(eventType, source, target, eventData));
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

}
