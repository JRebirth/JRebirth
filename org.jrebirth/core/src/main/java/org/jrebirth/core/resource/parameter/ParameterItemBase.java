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
package org.jrebirth.core.resource.parameter;

import org.jrebirth.core.resource.ResourceBuilders;

/**
 * The class <strong>ParameterItemBase</strong> is used to build Parameterized Object.
 * 
 * @param <T> the object type of the parameter
 * 
 * @author Sébastien Bordes
 */
public final class ParameterItemBase<T> implements ParameterItem<T> {

    /** The unique identifier of the color item. */
    private int uid;

    /**
     * Default Constructor.
     * 
     * You should not use this constructor, see #{@link org.jrebirth.core.resource.Resources}
     * 
     * @param parameterParams the params for parameter object
     */
    public ParameterItemBase(final ParameterParams parameterParams) {

        builder().storeParams(this, parameterParams);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get() {
        return (T) builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void define(final T forcedValue) {
        builder().set(this, forcedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParameterBuilder builder() {
        return ResourceBuilders.PARAMETER_BUILDER;
    }

    /**
     * Gets the uid.
     * 
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     * 
     * @param uid The uid to set.
     */
    public void setUid(final int uid) {
        this.uid = uid;
    }

}
