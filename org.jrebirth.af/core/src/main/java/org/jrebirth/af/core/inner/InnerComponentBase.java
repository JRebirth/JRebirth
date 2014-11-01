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
package org.jrebirth.af.core.inner;

import org.jrebirth.af.api.facade.Component;
import org.jrebirth.af.api.inner.InnerComponent;
import org.jrebirth.af.api.key.UniqueKey;

/**
 * The interface <strong>InnerModels</strong>.
 *
 * You may create an enumeration that implements this interface to manage inner models inside a root model.
 *
 * Or you can use The InnerModelBase class.
 *
 * @author Sébastien Bordes
 *
 */
public interface InnerComponentBase<C extends Component<?>> extends InnerComponent<C> {

    // /**
    // * {@inheritDoc}
    // */
    // default InnerModelRegistry registry() {
    // return InnerModelRegistry.getInstance();
    // }

    /**
     * Return the key of the model.
     *
     * Will return a {@link org.jrebirth.af.core.key.ClassKey} instance for a unique model or a {@link org.jrebirth.af.core.key.MultitonKey} for non unique model
     *
     * @return the unique key used to load the right Model, must be not null
     */
    @Override
    UniqueKey<C> getKey();

    static <CC extends Component<?>> InnerComponentBase<CC> create(final Class<CC> modelClass, final Object... keyPart) {
        return new InnerComponentEntry<CC>(modelClass, keyPart);
    }

    // default UniqueKey getKey(){
    // return registry().getKey(this);
    // }

    // /**
    // *
    // * @param key
    // */
    // @SuppressWarnings("unchecked")
    // default void key(UniqueKey<? extends Model> key) {
    // registry().storeKey((InnerModel)this, (UniqueKey)key);
    // }

    // /**
    // *
    // * @param modelClass
    // * @param keyPart
    // */
    // @SuppressWarnings("unchecked")
    // default void key(final Class<? extends Model> modelClass, final Object... keyPart) {
    // registry().storeKey((InnerModel<IM>)this, (UniqueKey<IM>)UniqueKey.key(modelClass, keyPart));
    // }

}
