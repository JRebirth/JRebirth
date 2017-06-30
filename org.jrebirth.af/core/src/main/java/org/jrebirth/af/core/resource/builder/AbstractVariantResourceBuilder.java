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

import org.jrebirth.af.api.resource.ResourceItem;
import org.jrebirth.af.api.resource.ResourceParams;

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
public abstract class AbstractVariantResourceBuilder<I extends ResourceItem<?, ?, ?>, P extends ResourceParams, R, V> extends AbstractResourceBuilder<I, P, R> {

    public R get(final I key, final V variant) {

        final P params = getParam(key);

        final String paramsKey = params.getKey() + variant.toString();

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
            resource = buildResource(key, params, variant);
            set(paramsKey, resource);
            params.hasChanged(false);
        }
        return resource;
    }

    /**
     * Build the resource requested.
     *
     * @param item the parameter item used to identify the resource element
     * @param params the primitive parameters used to build the resource
     *
     * @return the resource built and weakly stored with a WeakReference
     */
    protected abstract R buildResource(final I item, final P params, final V variant);

}
