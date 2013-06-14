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
package org.jrebirth.core.key;

import org.jrebirth.core.facade.FacadeReady;

/**
 * The class <strong>KeyBuilder</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class KeyBuilder {

    /**
     * Private constructor to avoid instantiation.
     */
    private KeyBuilder() {
        // Nothing to initialize
    }

    /**
     * Build an unique key.
     * 
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     * 
     * @return the unique key for the given class and keyParts array
     * 
     * @param <R> The type of the object registered by this ClassKey
     */
    public static <R extends FacadeReady<R>> UniqueKey<R> buildKey(final Class<R> clazz, final Object... keyPart) {

        UniqueKey<R> uniqueKey;
        if (keyPart == null || keyPart.length == 0) {
            uniqueKey = buildClassKey(clazz);
        } else {
            uniqueKey = buildMultitonKey(clazz, keyPart);
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
     * @param <R> The type of the object registered by this ClassKey
     */
    private static <R extends FacadeReady<R>> UniqueKey<R> buildClassKey(final Class<R> clazz) {
        return new ClassKey<R>(clazz);
    }

    /**
     * Build a multiton key.
     * 
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     * 
     * @return the unique key for a multiton
     * 
     * @param <R> The type of the object registered by this ClassKey
     */
    private static <R extends FacadeReady<R>> UniqueKey<R> buildMultitonKey(final Class<R> clazz, final Object... keyPart) {
        return new MultitonKey<R>(clazz, keyPart);
    }
}
