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
import org.jrebirth.af.core.resource.ResourceItem;

/**
 * The class <strong>ColorEnum</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface ColorItem extends ResourceItem<Color, ColorBuilder> {

    /**
     * Attach the color param.
     *
     * @param colorParams the primitive values for the color
     */
    default void set(final ColorParams colorParams) {
        builder().storeParams(this, colorParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default Color get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default ColorBuilder builder() {
        return ResourceBuilders.COLOR_BUILDER;
    }

    /**
     * .
     * 
     * @param red
     * @param green
     * @param blue
     * @param opacity
     */
    default void rgb255(int red, int green, int blue, double opacity) {
        set(new RGB255Color(red, green, blue, opacity));
    }

}
