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
package org.jrebirth.af.core.resource.fxml;

import org.jrebirth.af.api.resource.fxml.FXMLItem;
import org.jrebirth.af.api.resource.fxml.FXMLParams;
import org.jrebirth.af.api.ui.fxml.FXMLComponent;
import org.jrebirth.af.core.resource.AbstractResourceItem;

/**
 * The class <strong>FXMLItemBase</strong>.
 *
 * @author Sébastien Bordes
 */
public final class FXMLItemImpl extends AbstractResourceItem<FXMLItem, FXMLParams, FXMLComponent> implements FXMLItemBase {

    /** Property mapped to FXMLItem.isSingleton getter. */
    private final boolean isSingleton;

    /**
     * Build a fresh instance of {@link FXMLItemImpl}.
     *
     * @return a {@link FXMLItemImpl} instance
     */
    public static FXMLItemImpl create() {
        return create(true);
    }

    /**
     * Build a fresh instance of {@link FXMLItemImpl}.
     *
     * @param isSingleton flag that indicate if the FXML Component is unique or multiple.
     *
     * @return a {@link FXMLItemImpl} instance
     */
    public static FXMLItemImpl create(final boolean isSingleton) {
        return new FXMLItemImpl(isSingleton);
    }

    /**
     * Default Constructor.
     *
     * @param isSingleton flag that indicate if the FXML Component is unique or multiple.
     */
    public FXMLItemImpl(final boolean isSingleton) {
        super();
        this.isSingleton = isSingleton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSingleton() {
        return this.isSingleton;
    }

}
