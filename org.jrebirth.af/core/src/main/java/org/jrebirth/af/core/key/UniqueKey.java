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
import java.util.List;

/**
 * The class <strong>UniqueKey</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <R> the type of the classfield managed by this key
 */
public interface UniqueKey<R> extends Serializable {

    /**
     * Return the unique key.
     *
     * @return the unique key
     */
    String getKey();

    /**
     * Return the unique object if any or null.
     *
     * @return the unique object or null if none
     */
    Object getValue();

    /**
     * Return the class type of the component.
     *
     * @return Returns the classField.
     */
    Class<R> getClassField();

    /**
     *
     * @return
     */
    List<Object> getOptionalData();

    /**
     * Build an unique key.
     *
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     *
     * @return the unique key for the given class and keyParts array
     *
     * @param <E> The type of the object registered by this ClassKey
     */
    static <R> UniqueKey<R> key(final Class<R> clazz, final Object... keyPart) {

        UniqueKey<R> uniqueKey;
        if (keyPart == null || keyPart.length == 0 || keyPart[0].toString().isEmpty()) {
            uniqueKey = singleKey(clazz);
        } else {
            uniqueKey = multiKey(clazz, keyPart);
        }
        return uniqueKey;
    }

    /**
     * Build an unique key.
     *
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     *
     * @return the unique key for the given class and keyParts array
     *
     * @param <E> The type of the object registered by this ClassKey
     */
    static <R> UniqueKey<R> key(final Class<R> clazz, final Object[] optionalData, final Object... keyPart) {

        UniqueKey<R> uniqueKey;
        if (keyPart == null || keyPart.length == 0 || keyPart[0].toString().isEmpty()) {
            uniqueKey = singleKey(clazz, optionalData);
        } else {
            uniqueKey = multiKey(clazz, keyPart, optionalData);
        }
        return uniqueKey;
    }

    /**
     * Build a singleton key.
     *
     * @param clazz the class type of the component
     *
     * @return the unique key for a singleton
     *
     * @param <E> The type of the object registered by this ClassKey
     */
    static <R> UniqueKey<R> singleKey(final Class<R> clazz, final Object... optionalData) {
        return new ClassKey<R>(clazz, optionalData);
    }

    /**
     * Build a multiton key.
     *
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     *
     * @return the unique key for a multiton
     *
     * @param <E> The type of the object registered by this ClassKey
     */
    static <R> UniqueKey<R> multiKey(final Class<R> clazz, final Object... keyPart) {
        return new MultitonKey<R>(clazz, keyPart);
    }

    /**
     * Build a multiton key.
     *
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     *
     * @return the unique key for a multiton
     *
     * @param <E> The type of the object registered by this ClassKey
     */
    static <R> UniqueKey<R> multiKey(final Class<R> clazz, final Object[] keyPart, final Object... optionalData) {
        return new MultitonKey<R>(clazz, keyPart, optionalData);
    }
}
