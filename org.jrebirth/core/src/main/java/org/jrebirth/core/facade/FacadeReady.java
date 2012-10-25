/**
 * Copyright JRebirth.org © 2011-2012 
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

import org.jrebirth.core.exception.CoreException;

/**
 * The interface <strong>FacadeReady</strong>.
 * 
 * This interface let the object to be managed into its facade type.
 * 
 * @author Sébastien Bordes
 * 
 * @param <R> A type that implements FacadeReady
 */
public interface FacadeReady<R extends FacadeReady<R>> extends WaveReady {

    /**
     * Launch the initialization of the component.
     * 
     * @throws CoreException if the initialization fails
     */
    void ready() throws CoreException;

    /**
     * Return the local facade used to manage singleton.
     * 
     * @return the local facade
     */
    Facade<R> getLocalFacade();

    /**
     * Attach the local facade for this object type.
     * 
     * @param localFacade the local facade to set
     */
    void setLocalFacade(Facade<R> localFacade);

    /**
     * @return Returns the key.
     */
    UniqueKey getKey();

    /**
     * @param key The key to set.
     */
    void setKey(UniqueKey key);

    /**
     * Release the component by deleting this key used by the WeakHashMap.
     */
    void release();

}
