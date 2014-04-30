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
package org.jrebirth.af.core.resource;

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
 * @param <B> The resource builder to use
 */
public interface ResourceItem<R extends Object, I, P, B> {

    // /**
    // * Attach the resource parameter.
    // *
    // * @param params the primitive values for this resource
    // */
    default I set(P params) {
        return (I) this;
    }

    // Commented otherwise AbstractMethod error is raised !!!

    /**
     * Return the resource unique instance.
     * 
     * @return the resource
     */
    default R get() {
        return null;
    };

    /**
     * Return the resource builder.
     * 
     * @return the resource builder
     */
    B builder();
}
