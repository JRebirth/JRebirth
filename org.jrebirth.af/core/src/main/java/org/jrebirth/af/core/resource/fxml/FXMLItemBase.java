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

import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.ui.fxml.FXMLComponent;

/**
 * The class <strong>FXMLItemBase</strong>.
 *
 * @author Sébastien Bordes
 */
public final class FXMLItemBase implements FXMLItem {

    /** The unique identifier of the image item. */
    private int uid;

    /**
     * Default Constructor.
     *
     * You should not use this constructor, see #{@link org.jrebirth.af.core.resource.Resources}
     *
     * @param fxmlParams the primitive values for the fxmlComponent
     */
    public FXMLItemBase(final FXMLParams fxmlParams) {
        builder().storeParams(this, fxmlParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FXMLComponent get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FXMLBuilder builder() {
        return ResourceBuilders.FXML_BUILDER;
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
