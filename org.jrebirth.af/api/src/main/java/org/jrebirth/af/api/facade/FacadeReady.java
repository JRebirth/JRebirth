/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.api.facade;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.link.ModelReady;

/**
 * The interface <strong>FacadeReady</strong>.
 *
 * This interface let the object to be managed into its facade type.
 *
 * @author Sébastien Bordes
 *
 * @param <R> A type that implements FacadeReady
 */
public interface FacadeReady<R extends FacadeReady<R>> extends ModelReady {

    /**
     * Launch the initialization of the component.
     *
     * This method is always called into JIT
     *
     * @throws CoreException if the initialization fails
     */
    void setup() throws CoreException;

    /**
     * Return the local facade used to manage singleton.
     *
     * @return the local facade
     */
    LocalFacade<R> getLocalFacade();

    /**
     * Attach the local facade for this object type.
     *
     * @param localFacade the local facade to set
     */
    void setLocalFacade(final LocalFacade<R> localFacade);

    /**
     * @return Returns the key.
     */
    UniqueKey<R> getKey();

    /**
     * @param key The key to set.
     */
    void setKey(final UniqueKey<R> key);

    /**
     * Release the component by deleting this key used by the WeakHashMap.
     *
     * @return true if the release has been performed and false when the released has been rejected/delayed
     */
    boolean release();

}
