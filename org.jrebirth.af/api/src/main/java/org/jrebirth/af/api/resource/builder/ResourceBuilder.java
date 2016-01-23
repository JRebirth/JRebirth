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
package org.jrebirth.af.api.resource.builder;

import org.jrebirth.af.api.resource.ResourceParams;

/**
 * The interface <strong>ResourceBuilder</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <I> The item used to wrap the resource
 * @param
 *        <P>
 *        The params object that store primitive resources values
 * @param <R> The resource managed
 */
public interface ResourceBuilder<I, P, R> {

    /**
     * Store a parameter.
     *
     * @param item the parameter key
     * @param params the parameter value
     */
    void storeParams(final I item, final P params);

    /**
     * Retrieve a parameter value from its key.
     *
     * @param item the parameter key
     * @return the parameter value
     */
    P getParam(final I item);

    /**
     * Retrieve the parameter key.
     *
     * @param item the parameter key
     *
     * @return the parameter key
     */
    String getParamKey(final I item);

    /**
     * Retrieve the resource. And build it if it didn't be done before.
     *
     * @param item the item as a key
     *
     * @return the resource
     */
    R get(final I item);

    /**
     * Store a new resource.
     *
     * @param key the serialized {@link ResourceParams} used
     * @param resource the resource to weakly store
     */
    void set(final String key, final R resource);
}
