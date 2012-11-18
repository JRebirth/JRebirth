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
package org.jrebirth.core.resource.factory;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * The abstract class <strong>AbstractResourceBuilder</strong>.
 * 
 * The base abstract class used to manage resource weakly.
 * 
 * @author Sébastien Bordes
 * 
 * @param <E> The enumeration used to wrap the resource
 * @param <P> The params type
 * @param <R> The resource managed
 */
public abstract class AbstractResourceBuilder<E, P, R> implements ResourceBuilder<E, P, R> {

    /** The resource weak Map. */
    private final Map<E, P> paramsMap = new HashMap<E, P>();

    /** The resource weak Map. */
    private final Map<E, WeakReference<R>> resourceMap = new WeakHashMap<>();

    // public AbstractResourceFactory(Class<E> enumClass) {
    // paramsMap = new HashMap<E, P>();
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeParams(final E key, final P params) {
        // Store the resource into the map
        this.paramsMap.put(key, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P getParam(final E key) {
        // Retrieve the resource from the map
        return this.paramsMap.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R get(final E key) {
        // Retrieve the resource into the map
        WeakReference<R> resource = this.resourceMap.get(key);
        // The resource may be null if nobody use it
        if (resource == null || resource.get() == null) {
            // So we must rebuild an instance and then store it weakly
            set(key, buildResource(getParam(key)));

            // Get the WeakReference
            resource = this.resourceMap.get(key);
        }
        return resource.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final E key, final R resource) {
        this.resourceMap.put(key, new WeakReference<R>(resource));
    }

    /**
     * Build the resource requested.
     * 
     * @param params the primitive parameters used to build the resource
     * 
     * @return the resource built and weakly stored with a WeakReference
     */
    protected abstract R buildResource(P params);

}
