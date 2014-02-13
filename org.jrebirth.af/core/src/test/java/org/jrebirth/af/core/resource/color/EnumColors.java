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
package org.jrebirth.af.core.resource.color;

import javafx.scene.paint.Color;

import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.resource.color.ColorBuilder;
import org.jrebirth.af.core.resource.color.ColorItem;
import org.jrebirth.af.core.resource.color.ColorParams;
import org.jrebirth.af.core.resource.color.WebColor;

/**
 * The class <strong>EnumColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum EnumColors implements ColorItem {

    /** Color for slide title, white. */
    SLIDE_TITLE(new WebColor("FFFFFF", 1.0)),

    /** Color for blue shape, xxx. */
    SHAPE_BLUE(new WebColor("3495CE", 1.0)),

    /** Color for drop shadow, black. */
    DROP_SHADOW(new WebColor("000000", 0.8)),
    /** Color for inner shadow, white. */
    INNER_SHADOW(new WebColor("FFFFFE", 0.3));

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private EnumColors(final ColorParams colorParams) {
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
}
