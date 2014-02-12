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
package org.jrebirth.af.core.resource.font;

import javafx.scene.text.Font;

import org.jrebirth.af.core.resource.ResourceBuilders;

/**
 * The class <strong>FontItemBase</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class FontItemBase implements FontItem {

    /** The unique identifier of the font item. */
    private int uid;

    /**
     * Default Constructor.
     * 
     * You should not use this constructor, see #{@link org.jrebirth.af.core.resource.Resources}
     * 
     * @param fontParams the primitive values for the font
     */
    public FontItemBase(final FontParams fontParams) {
        builder().storeParams(this, fontParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontBuilder builder() {
        return ResourceBuilders.FONT_BUILDER;
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
