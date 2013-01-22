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
package org.jrebirth.core.resource.color;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.parameter.ParameterName;

/**
 * The class <strong>ColorItemBase</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ColorItemBase implements ColorItem {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the color item. */
    private int uid;

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private ColorItemBase(final ColorParams colorParams) {
        builder().storeParams(this, colorParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorBuilder builder() {
        return ResourceBuilders.COLOR_BUILDER;
    }

    /**
     * Build a color item.
     * 
     * @param colorParams the primitive values for the color
     * 
     * @return a new fresh color item object
     */
    public static ColorItemBase build(final ColorParams colorParams) {
        final ColorItemBase colorItem = new ColorItemBase(colorParams);

        // Ensure that the uid will be unique at runtime
        synchronized (ColorItemBase.class) {
            colorItem.setUid(++idGenerator);
        }
        return colorItem;
    }

    /**
     * Build a color item.
     * 
     * @param colorName the name used to define uniqueness
     * @param colorParams the primitive values for the color
     * 
     * @return a new fresh color item object
     */
    public static ColorItemBase build(final ParameterName colorName, final ColorParams colorParams) {
        final ColorItemBase colorItem = new ColorItemBase(colorParams);

        // Ensure that the uid will be unique at runtime
        synchronized (ColorItemBase.class) {
            colorItem.setUid(++idGenerator);
        }
        return colorItem;
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
