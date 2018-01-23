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

import org.jrebirth.af.api.resource.builder.ResourceBuilder;
import org.jrebirth.af.api.resource.parameter.ParameterItem;
import org.jrebirth.af.api.resource.parameter.ParameterParams;
import org.jrebirth.af.core.resource.ResourceBuilders;

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
public interface ParameterItemBase<T extends Object> extends ParameterItem<T> {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    default ResourceBuilder<ParameterItem<?>, ParameterParams, T> builder() {
        return (ResourceBuilder<ParameterItem<?>, ParameterParams, T>) ResourceBuilders.PARAMETER_BUILDER;
    }
}
