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
package org.jrebirth.af.api.resource.fxml;

import org.jrebirth.af.api.resource.ResourceItem;
import org.jrebirth.af.api.ui.fxml.FXMLComponent;

/**
 * The class <strong>FXMLItem</strong> is used to link a {@link FXMLComponent} resource.
 *
 * @author Sébastien Bordes
 */
public interface FXMLItem extends ResourceItem<FXMLItem, FXMLParams, FXMLComponent> {

    /**
     * {@inheritDoc}
     */
    @Override
    default FXMLItem set(final FXMLParams fxmlParams) {
        builder().storeParams(this, fxmlParams);
        return this;
    }

    /**
     * {@inheritDoc}
     * 
     * Depending on the isSingleton method, it will return a fresh instance or the singleton one
     */
    @Override
    default FXMLComponent get() {
        return isSingleton() ? builder().get(this) : getNew();
    }

    /**
     * Return the flag that indicates if the item will help to retrieve the same FXMLComponent instance or another instance at each call.
     * 
     * @return true for singleton,false for multiple
     */
    default boolean isSingleton() {
        return true;
    }

    /**
     * Build a new FXMLComponent without storing it.
     *
     * If you call 'n' times this method you will obtain 'n' {@link FXMLComponent} instances.
     *
     * @return a new {@link FXMLComponent} instance
     */
    FXMLComponent getNew();

}
