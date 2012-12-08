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
package org.jrebirth.core.link;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.EventType;
import org.jrebirth.core.facade.Facade;
import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.Model;

/**
 * 
 * The class <strong>AbstractReady</strong>.
 * 
 * It allow to retrieve any of available singleton.
 * 
 * @param <R> the class type of the subclass
 */
public abstract class AbstractReady<R extends FacadeReady<R>> implements FacadeReady<R>, ModelReady, CommandReady, ServiceReady {

    /**
     * The facade that manage same kind of object (from Service, Command and Model).
     */
    private Facade<R> localFacade;

    /** The key that is used to register this component. */
    private UniqueKey key;

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
    public UniqueKey getKey() {
        return this.key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKey(final UniqueKey key) {
        this.key = key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void release() {
        this.key = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <S extends Service> S getService(final Class<S> clazz, final Object... keyPart) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_SERVICE, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getServiceFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> C getCommand(final Class<C> clazz, final Object... keyPart) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_COMMAND, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getCommandFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> M getModel(final Class<M> clazz, final Object... keyPart) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_MODEL, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getUiFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void ready() throws CoreException;

}
