/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.api.resource;

import org.jrebirth.af.api.resource.builder.ResourceBuilder;

/**
 * The interface <strong>ResourceItem</strong> designate a system resource.
 *
 * In example Color, Font resources that are heavy object managed by the toolkit.<br />
 * They must be released as soon as possible to limit memory consumption.
 *
 * @author Sébastien Bordes
 *
 * @param <I> the resource item
 * @param <R> The resource managed
 * @param <P> the resource parameter
 */
@FunctionalInterface
public interface ResourceItem<I, P, R> {

    default I module(Module module) {
        return null;
    }
	
    default Module module() {
        return null;
    }
    
    /**
     * Attach the resource parameter.
     *
     * @param params the primitive values for this resource
     *
     * @return the current item to chain method call
     */
    @SuppressWarnings("unchecked")
    default I set(final P params) {
        builder().storeParams((I) this, params);
        return (I) this;
    }

    /**
     * Return the resource unique instance.
     *
     * @return the resource
     */
    default R get() {
        return null;
    }

    /**
     * Return the registered {@link ResourceParams} element
     *
     * @return the resource params
     */
    @SuppressWarnings("unchecked")
    default P param() {
        return builder().getParam((I) this);
    }

    /**
     * Return the resource builder.
     *
     * @return the resource builder
     */
    ResourceBuilder<I, P, R> builder();
}
