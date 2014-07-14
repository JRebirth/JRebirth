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

import org.jrebirth.af.core.command.Command;
import org.jrebirth.af.core.facade.FacadeReady;
import org.jrebirth.af.core.facade.JRebirthEventType;
import org.jrebirth.af.core.facade.LocalFacade;
import org.jrebirth.af.core.key.MultitonKey;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.service.Service;
import org.jrebirth.af.core.ui.Model;

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
    public final LocalFacade<R> getLocalFacade() {
        return this.localFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setLocalFacade(final LocalFacade<R> localFacade) {
        this.localFacade = localFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<R> getKey() {
        return this.key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKey(final UniqueKey<R> key) {
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
        if (this.key.getValue() instanceof List && !((List<Object>) this.key.getValue()).isEmpty()) {
            keyPart = ((List<Object>) this.key.getValue()).get(0);
        } else {
            // UniqueKey or MultitontKey with only one key part
            keyPart = this.key.getValue();
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
        if (this.key.getValue() instanceof List && ((List<Object>) this.key.getValue()).size() > 1) {
            keyPart = ((List<Object>) this.key.getValue()).get(1);
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
        if (this.key.getValue() instanceof List && ((List<Object>) this.key.getValue()).size() > 1) {
            keyPart = ((List<Object>) this.key.getValue()).get(1);
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
        if (this.key.getValue() instanceof List) {
            listModelObject = (List<Object>) this.key.getValue();
        } else {
            listModelObject = new ArrayList<>();
            listModelObject.add(this.key.getValue());
        }
        return listModelObject;
    }

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
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_SERVICE, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getServiceFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <S extends Service> S getService(final UniqueKey<S> serviceKey) {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_SERVICE, this.getClass(), serviceKey.getClassField());
        return getLocalFacade().getGlobalFacade().getServiceFacade().retrieve(serviceKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> C getCommand(final Class<C> clazz, final Object... keyPart) {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_COMMAND, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getCommandFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> C getCommand(final UniqueKey<C> commandKey) {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_COMMAND, this.getClass(), commandKey.getClassField());
        return getLocalFacade().getGlobalFacade().getCommandFacade().retrieve(commandKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> M getModel(final Class<M> clazz, final Object... keyPart) {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_MODEL, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getUiFacade().retrieve(clazz, keyPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> M getModel(final UniqueKey<M> modelKey) {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.ACCESS_MODEL, this.getClass(), modelKey.getClassField());
        return getLocalFacade().getGlobalFacade().getUiFacade().retrieve(modelKey);
    }

}
