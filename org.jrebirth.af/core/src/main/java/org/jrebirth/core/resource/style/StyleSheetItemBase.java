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
package org.jrebirth.core.resource.style;

import java.net.URL;

import org.jrebirth.core.resource.ResourceBuilders;

/**
 * The class <strong>StyleSheetItemBase</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class StyleSheetItemBase implements StyleSheetItem {

    /** The unique identifier of the image item. */
    private int uid;

    /**
     * Default Constructor.
     * 
     * You should not use this constructor, see #{@link org.jrebirth.core.resource.Resources}
     * 
     * @param imageParams the primitive values for the image
     */
    public StyleSheetItemBase(final StyleSheetParams imageParams) {
        builder().storeParams(this, imageParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StyleSheetBuilder builder() {
        return ResourceBuilders.STYLE_SHEET_BUILDER;
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
