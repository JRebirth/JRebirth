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
package org.jrebirth.core.facade;

import org.jrebirth.core.key.UniqueKey;

/**
 * The class <strong>Facade</strong>.
 * 
 * The interface to respect in order to implement a local facade (per pattern type)
 * 
 * @author Sébastien Bordes
 * 
 * @param <R> A type that implements FacadeReady
 */
public interface Facade<R extends FacadeReady<R>> {

    /**
     * Register a new ready object component.
     * 
     * @param readyObject the component to register
     * @param keyPart the unique key for multiton FacadeReady element, could be omitted for singleton
     * 
     * @param <E> the type of the ready object used
     */
    <E extends R> void register(final E readyObject, Object... keyPart);

    /**
     * Unregister a new ready object component.
     * 
     * @param readyObject the component to unregister
     * @param keyPart the unique key for multiton FacadeReady element, could be omitted for singleton
     * 
     * @param <E> the type of the ready object used
     */
    <E extends R> void unregister(final E readyObject, Object... keyPart);

    /**
     * Retrieve a ready object component.<br />
     * May allow to build it if it hadn't been done previously
     * 
     * @param clazz the component class
     * @param keyPart the unique key for multiton FacadeReady element, could be omitted for singleton
     * 
     * @return the singleton of the component
     * 
     * @param <E> the type of the ready object to retrieve
     */
    <E extends R> E retrieve(final Class<E> clazz, Object... keyPart);

    /**
     * Retrieve a ready object component.<br />
     * May allow to build it if it hadn't been done previously
     * 
     * @param uniqueKey the unique key for the component to get
     * 
     * @return the singleton of the component
     * 
     * @param <E> the type of the ready object to retrieve
     */
    <E extends R> E retrieve(final UniqueKey<E> uniqueKey);

    /**
     * Check if the component has already been created and stored.
     * 
     * @param clazz the component class to check
     * @param keyPart the unique key for multiton FacadeReady element, could be omitted for singleton
     * 
     * @return true if the component exists
     */
    boolean exists(final Class<? extends R> clazz, Object... keyPart);

}
