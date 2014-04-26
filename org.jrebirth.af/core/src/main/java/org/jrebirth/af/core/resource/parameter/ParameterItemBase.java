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

import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.resource.AbstractResourceItem;

/**
 * The class <strong>ParameterItemBase</strong> is used to build Parameterized Object.
 * 
 * @param <T> the object type of the parameter
 * 
 * @author Sébastien Bordes
 */
public final class ParameterItemBase<T> extends AbstractResourceItem<T, ParameterItem<T>, ParameterParams, ParameterBuilder> implements ParameterItem<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void define(final T forcedValue) {
        // The default programmatic value (stored into ObjectParameter) is not updated but overridden into the local map
        builder().define(this, forcedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void persist() {
        throw new CoreRuntimeException("Not Implemented yet");
    }

    public static <O extends Object> ParameterItemBase<O> create(O o) {
        return new ParameterItemBase<O>();
    }

}
