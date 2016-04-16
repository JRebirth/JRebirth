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
package org.jrebirth.af.core.component.basic;

import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.component.basic.InnerComponent;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.core.key.Key;

/**
 * The interface <strong>InnerModels</strong>.
 *
 * You may create an enumeration that implements this interface to manage inner models inside a root model.
 *
 * Or you can use The InnerModelBase class.
 *
 * @author Sébastien Bordes
 *
 * @param <C> the type of the component used
 */
public class InnerComponentBase<C extends Component<?>> implements InnerComponent<C> {

    /** The generator of unique id. */
    // private static int idGenerator;

    /** The unique identifier of the wave type. */
    private int uid;

    /** The unique key of the inner model. */
    private final UniqueKey<C> innerComponentKey;

    private Component<?> hostComponent;

    private C innerComponent;

    /**
     * Default constructor.
     *
     * @param componentClass the model class
     * @param keyPart the list of model keys
     */
    InnerComponentBase(final Class<C> componentClass, final Object... keyPart) {

        this.innerComponentKey = Key.create(componentClass, keyPart);
    }

    /**
     * Default constructor.
     *
     * @param innerComponentKey the inner component key
     */
    InnerComponentBase(final UniqueKey<C> innerComponentKey) {

        this.innerComponentKey = innerComponentKey;
    }

    // /**
    // * Build an InnerModel.
    // *
    // * @param modelClass the model class
    // * @param keyPart the list of model keys
    // *
    // * @return a new fresh InnerModel type object
    // */
    // public static InnerModelEntry build(final Class<? extends Model> modelClass, final Object... keyPart) {
    // final InnerModelEntry innerModel = new InnerModelEntry(modelClass, keyPart);
    //
    // // Ensure that the uid will be unique at runtime
    // synchronized (WaveType.class) {
    // innerModel.setUid(++idGenerator);
    // }
    // return innerModel;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<C> key() {
        return this.innerComponentKey;
    }

    /**
     * Gets the uid.
     *
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     *
     * @param uid The uid to set.
     */
    public InnerComponent<C> setUid(final int uid) {
        this.uid = uid;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object innerModel) {
        return innerModel instanceof InnerComponentBase && getUid() == ((InnerComponentBase<?>) innerModel).getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component<?> host() {
        return this.hostComponent;
    }

    /**
     * {@inheritDoc}
     */
    public InnerComponent<C> host(final Component<?> hostComponent) {
        this.hostComponent = hostComponent;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public C get() {
        if (this.innerComponent == null) {
            this.innerComponent = this.hostComponent.findInnerComponent(this);
        }
        return this.innerComponent;
    }

}
