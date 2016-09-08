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
package org.jrebirth.af.core.link;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.facade.FacadeReady;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.api.facade.LocalFacade;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.key.MultitonKey;

/**
 *
 * The class <strong>AbstractReady</strong>.
 *
 * It allow to retrieve any of available singleton.
 *
 * @param <R> the class type of the subclass
 */
public abstract class AbstractReady<R extends FacadeReady<R>> implements FacadeReady<R> {

    /**
     * The facade that manage same kind of object (from Service, Command and Model).
     */
    private LocalFacade<R> localFacade;

    /** The key that is used to register this component. */
    private UniqueKey<R> key;

    /**
     * {@inheritDoc}
     */
    @Override
    public final LocalFacade<R> localFacade() {
        return this.localFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void localFacade(final LocalFacade<R> localFacade) {
        this.localFacade = localFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<R> key() {
        return this.key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void key(final UniqueKey<R> key) {
        this.key = key;
    }

    /**
     * Return the first key part of the {@link MultitonKey} or the component class type for {@link UniqueKey}.
     *
     * @return Returns the first key part or the component type.
     */
    @SuppressWarnings("unchecked")
    public Object getFirstKeyPart() {
        Object keyPart = null;
        if (this.key.value() instanceof List && !((List<Object>) this.key.value()).isEmpty()) {
            keyPart = ((List<Object>) this.key.value()).get(0);
        } else {
            // UniqueKey or MultitontKey with only one key part
            keyPart = this.key.value();
        }
        return keyPart;
    }

    /**
     * Return the second key part of the {@link MultitonKey} if any or null.
     *
     * @return Returns the second key part or null.
     */
    @SuppressWarnings("unchecked")
    public Object getSecondKeyPart() {
        Object keyPart = null;
        if (this.key.value() instanceof List && ((List<Object>) this.key.value()).size() > 1) {
            keyPart = ((List<Object>) this.key.value()).get(1);
        }
        return keyPart;
    }

    /**
     * Return the third key part of the {@link MultitonKey} if any or null.
     *
     * @return Returns the third key part or null.
     */
    @SuppressWarnings("unchecked")
    public Object getThirdKeyPart() {
        Object keyPart = null;
        if (this.key.value() instanceof List && ((List<Object>) this.key.value()).size() > 1) {
            keyPart = ((List<Object>) this.key.value()).get(2);
        }
        return keyPart;
    }

    /**
     * Return the list of key parts (considered as model objects).
     *
     * If the key value is an instance of List it will return it as is otherwise it will create a new list and add the unique key part object
     *
     * @return Returns a list composed by all model object (= component key parts).
     */
    @SuppressWarnings("unchecked")
    public List<Object> getListKeyPart() {
        List<Object> listModelObject = null;
        if (this.key.value() instanceof List) {
            listModelObject = (List<Object>) this.key.value();
        } else {
            listModelObject = new ArrayList<>();
            listModelObject.add(this.key.value());
        }
        return listModelObject;
    }

    /**
     * Return the first object assignable from te given class.
     *
     * @param keyPartClass the class used to search the first instance
     *
     * @return the first instance found or raise a NoSuchElementException
     */
    @SuppressWarnings("unchecked")
    public <KP extends Object> KP getKeyPart(final Class<KP> keyPartClass) {
        return (KP) getListKeyPart().stream()
                                    .filter(kp -> kp != null && keyPartClass.isAssignableFrom(kp.getClass()))
                                    .findFirst()
                                    .get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <S extends Service> S getService(final Class<S> clazz, final Object... keyPart) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_SERVICE, this.getClass(), clazz);
        return localFacade().getGlobalFacade().serviceFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <S extends Service> S getService(final UniqueKey<S> serviceKey) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_SERVICE, this.getClass(), serviceKey.classField());
        return localFacade().getGlobalFacade().serviceFacade().retrieve(serviceKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <S extends Service> List<S> getServices(final Class<S> clazz, final Object... keyPart) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_SERVICE, this.getClass(), clazz);
        return localFacade().getGlobalFacade().serviceFacade().retrieveMany(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <S extends Service> List<S> getServices(final UniqueKey<S> serviceKey) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_SERVICE, this.getClass(), serviceKey.classField());
        return localFacade().getGlobalFacade().serviceFacade().retrieveMany(serviceKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> C getCommand(final Class<C> clazz, final Object... keyPart) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_COMMAND, this.getClass(), clazz);
        return localFacade().getGlobalFacade().commandFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> C getCommand(final UniqueKey<C> commandKey) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_COMMAND, this.getClass(), commandKey.classField());
        return localFacade().getGlobalFacade().commandFacade().retrieve(commandKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> List<C> getCommands(final Class<C> clazz, final Object... keyPart) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_COMMAND, this.getClass(), clazz);
        return localFacade().getGlobalFacade().commandFacade().retrieveMany(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> List<C> getCommands(final UniqueKey<C> commandKey) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_COMMAND, this.getClass(), commandKey.classField());
        return localFacade().getGlobalFacade().commandFacade().retrieveMany(commandKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> M getModel(final Class<M> clazz, final Object... keyPart) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_MODEL, this.getClass(), clazz);
        return localFacade().getGlobalFacade().uiFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> M getModel(final UniqueKey<M> modelKey) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_MODEL, this.getClass(), modelKey.classField());
        return localFacade().getGlobalFacade().uiFacade().retrieve(modelKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> List<M> getModels(final Class<M> clazz, final Object... keyPart) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_MODEL, this.getClass(), clazz);
        return localFacade().getGlobalFacade().uiFacade().retrieveMany(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> List<M> getModels(final UniqueKey<M> modelKey) {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_MODEL, this.getClass(), modelKey.classField());
        return localFacade().getGlobalFacade().uiFacade().retrieveMany(modelKey);
    }

}
