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
package org.jrebirth.af.core.resource.parameter;

import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.resource.ResourceItem;

/**
 * The class <strong>ParameterItem</strong>.
 * 
 * The name of the parameter.
 * 
 * The name will be transformed : camelCase => CAMEL_CASE
 * 
 * @param <T> the object type of the parameter
 * 
 * @author Sébastien Bordes
 */
public interface ParameterItem<T extends Object> extends ResourceItem<T, ParameterItem<T>, ParameterParams, ParameterBuilder> {

    /**
     * Define a new forced value.
     * 
     * This forced value will be used instead of default programmatic one and all other retrieved from properties files.
     * 
     * @param forcedValue the new value for this parameter
     */
    void define(final T forcedValue);

    /**
     * Persist a parameter value.
     */
    void persist();

    /**
     * {@inheritDoc}
     */
    default ParameterItem<T> set(final ParameterParams parameterParams) {
        builder().storeParams(this, parameterParams);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    default T get() {
        return (T) builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default ParameterBuilder builder() {
        return ResourceBuilders.PARAMETER_BUILDER;
    }
}
