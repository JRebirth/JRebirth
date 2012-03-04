package org.jrebirth.core.link.impl;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.CommandReady;
import org.jrebirth.core.facade.Facade;
import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.facade.ModelReady;
import org.jrebirth.core.facade.ServiceReady;
import org.jrebirth.core.facade.UniqueKey;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.Model;

/**
 * 
 * The class <strong>AbstractReady</strong>.
 * 
 * It allow to retrieve any of available singleton.
 * 
 * @author Sébastien Bordes
 * @version $Revision$ $Date$ $Name$
 * 
 * @since org.jrebirth.core 1.0
 * 
 * @param <R> the class type of the subclass
 */
public abstract class AbstractReady<R extends FacadeReady<R>> implements FacadeReady<R>, ModelReady, CommandReady, ServiceReady {

    /** The facade that manage same kind of object (from Service, Command and Model). */
    private Facade<R> localFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Facade<R> getLocalFacade() {
        return this.localFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setLocalFacade(final Facade<R> localFacade) {
        this.localFacade = localFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <S extends Service> S getService(final Class<S> clazz, final UniqueKey... key) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_SERVICE, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getServiceFacade().retrieve(clazz, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> C getCommand(final Class<C> clazz, final UniqueKey... key) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_COMMAND, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getCommandFacade().retrieve(clazz, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> M getModel(final Class<M> clazz, final UniqueKey... key) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_MODEL, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getUiFacade().retrieve(clazz, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void ready() throws CoreException;

}
