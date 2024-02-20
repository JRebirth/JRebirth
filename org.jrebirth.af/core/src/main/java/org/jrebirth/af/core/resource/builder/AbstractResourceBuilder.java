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
package org.jrebirth.af.core.resource.builder;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import org.jrebirth.af.api.resource.ResourceItem;
import org.jrebirth.af.api.resource.ResourceParams;
import org.jrebirth.af.api.resource.builder.ResourceBuilder;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;

/**
 * The abstract class <strong>AbstractResourceBuilder</strong>.
 *
 * The base abstract class used to manage resource weakly.
 *
 * @author Sébastien Bordes
 *
 * @param <I> The item used to wrap the resource
 * @param <P> The resource params type
 * @param <R> The real resource managed
 */
public abstract class AbstractResourceBuilder<I extends ResourceItem<?, ?, ?>, P extends ResourceParams, R> implements ResourceBuilder<I, P, R> {

    /** The resource weak Map. */
    private final Map<I, P> paramsMap = Collections.synchronizedMap(new WeakHashMap<>());

    /**
     * The resource weak Map.<br />
     * SoftReference can be kept longer in memory depending on the -client or -server jvm argument and on Xms and Xms values.
     */
    protected final Map<String, SoftReference<R>> resourceMap = Collections.synchronizedMap(new WeakHashMap<>());

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeParams(final I key, final P params) {
        // Store the resource into the map
        this.paramsMap.put(key, params);

        // Activate the AutoRefresh feature only for other parameters
        if (Resources.isNotAutoRefreshParam(params) && CoreParameters.AUTO_REFRESH_RESOURCE.get()) {
            params.activateAutoRefresh();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P getParam(final I key) {
        // Retrieve the resource from the map
        return this.paramsMap.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParamKey(final I key) {
        return getParam(key).getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R get(final I key) {

        final P params = getParam(key);

        final String paramsKey = params.getKey();

        // Retrieve the resource weak reference from the map
        final SoftReference<R> resourceSoftRef = this.resourceMap.get(paramsKey);

        // Warning the gc can collect the resource between the test and the getter call so we have to get the resource immediately
        // and test it instead of testing the reference value

        // The resourceSoftRef may be null if nobody use it
        // If the resource reference is not null try to grab its value
        R resource = resourceSoftRef == null ? null : resourceSoftRef.get();

        // When the reference is null (first access time) or the resource is null (the resource has been collected y gc)
        if (resourceSoftRef == null || resource == null) {
            // We must (re)build an instance and then store it weakly
            resource = buildResource(key, params);
            set(paramsKey, resource);
            params.hasChanged(false);
        }
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final String key, final R resource) {
        this.resourceMap.put(key, new SoftReference<>(resource));
    }

    /**
     * Build the resource requested.
     *
     * @param item the parameter item used to identify the resource element
     * @param params the primitive parameters used to build the resource
     *
     * @return the resource built and weakly stored with a WeakReference
     */
    protected abstract R buildResource(final I item, final P params);

}
