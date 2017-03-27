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
package org.jrebirth.af.core.key;

import java.io.Serializable;

import org.jrebirth.af.api.key.UniqueKey;

/**
 * The class <strong>Key</strong> used as a key factory.
 *
 * @author Sébastien Bordes
 */
public interface Key extends Serializable {

    /**
     * Build an unique key.
     *
     * @param <C> The type of the object registered by this ClassKey
     *
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     *
     * @return the unique key for the given class and keyParts array
     */
    static <C> UniqueKey<C> create(final Class<C> clazz, final Object... keyPart) {

        UniqueKey<C> uniqueKey;
        if (keyPart == null || keyPart.length == 0) {
            uniqueKey = createSingle(clazz);
        } else {
            uniqueKey = createMulti(clazz, keyPart);
        }
        return uniqueKey;
    }

    /**
     * Build an unique key.
     *
     * @param <C> The type of the object registered by this ClassKey
     *
     * @param clazz the class type of the component
     * @param optionalData the optional data to be transmit to the component
     * @param keyPart all complementary part of the key
     *
     * @return the unique key for the given class and keyParts array
     */
    static <C> UniqueKey<C> create(final Class<C> clazz, final Object[] optionalData, final Object... keyPart) {

        UniqueKey<C> uniqueKey;
        if (keyPart == null || keyPart.length == 0) {
            uniqueKey = createSingle(clazz, optionalData);
        } else {
            uniqueKey = createMulti(clazz, keyPart, optionalData);
        }
        return uniqueKey;
    }

    /**
     * Build a singleton key.
     *
     * @param <C> The type of the object registered by this ClassKey
     *
     * @param clazz the class type of the component
     * @param optionalData the optional data to be transmit to the component
     *
     * @return the unique key for a singleton
     */
    static <C> UniqueKey<C> createSingle(final Class<C> clazz, final Object... optionalData) {
        return new ClassKey<>(clazz, optionalData);
    }

    /**
     * Build a multiton key.
     *
     * @param <C> The type of the object registered by this ClassKey
     *
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     *
     * @return the unique key for a multiton
     */
    static <C> UniqueKey<C> createMulti(final Class<C> clazz, final Object... keyPart) {
        return new MultitonKey<>(clazz, keyPart);
    }

    /**
     * Build a multiton key.
     *
     * @param <C> The type of the object registered by this ClassKey
     *
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     * @param optionalData the optional data to be transmit to the component
     *
     * @return the unique key for a multiton
     */
    static <C> UniqueKey<C> createMulti(final Class<C> clazz, final Object[] keyPart, final Object... optionalData) {
        return new MultitonKey<>(clazz, keyPart, optionalData);
    }

}
