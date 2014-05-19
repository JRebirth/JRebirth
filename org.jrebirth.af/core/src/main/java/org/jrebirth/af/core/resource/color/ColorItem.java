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
public interface ColorItem extends ResourceItem<Color, ColorItem, ColorParams, ColorBuilder> {

    /**
     * {@inheritDoc}
     */
    @Override
    default ColorItem set(final ColorParams colorParams) {
        builder().storeParams(this, colorParams);
        return this;
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

    public interface RGB255 extends ColorItem {

        /**
         * .
         *
         * @param red
         * @param green
         * @param blue
         * @param opacity
         */
        default void rgb255(final int red, final int green, final int blue, final double opacity) {
            set(new RGB255Color(red, green, blue, opacity));
        }

        /**
         * .
         *
         * @param red
         * @param green
         * @param blue
         * @param opacity
         */
        default void rgb255(final int red, final int green, final int blue) {
            set(new RGB255Color(red, green, blue));
        }
    }

    public interface RGB01 extends ColorItem {

        /**
         * .
         *
         * @param red
         * @param green
         * @param blue
         * @param opacity
         */
        default void rgb(final double red, final double green, final double blue, final double opacity) {
            set(new RGB01Color(red, green, blue, opacity));
        }

        /**
         * .
         *
         * @param red
         * @param green
         * @param blue
         */
        default void rgb(final double red, final double green, final double blue) {
            set(new RGB01Color(red, green, blue));
        }
    }

    public interface HSB extends ColorItem {

        /**
         * .
         *
         * @param hue
         * @param saturation
         * @param brightness
         * @param opacity
         */
        default void hsb(final double hue, final double saturation, final double brightness, final double opacity) {
            set(new HSBColor(hue, saturation, brightness, opacity));
        }

        /**
         * .
         *
         * @param hue
         * @param saturation
         * @param brightness
         */
        default void hsb(final double hue, final double saturation, final double brightness) {
            set(new HSBColor(hue, saturation, brightness));
        }
    }

    public interface Web extends ColorItem {

        /**
         * .
         *
         * @param hex
         * @param opacity
         */
        default void web(final String hex, final double opacity) {
            set(new WebColor(hex, opacity));
        }

        /**
         * .
         *
         * @param hex
         */
        default void web(final String hex) {
            set(new WebColor(hex));
        }
    }

    public interface Gray extends ColorItem {

        /**
         * .
         *
         * @param gray
         * @param opacity
         */
        default void gray(final double gray, final double opacity) {
            set(new GrayColor(gray, opacity));
        }

        /**
         * .
         *
         * @param gray
         */
        default void gray(final double gray) {
            set(new GrayColor(gray));
        }
    }
}
