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

import org.jrebirth.af.api.resource.builder.ResourceBuilder;
import org.jrebirth.af.api.resource.color.ColorItem;
import org.jrebirth.af.api.resource.color.ColorParams;
import org.jrebirth.af.core.resource.ResourceBuilders;

/**
 * The interface <strong>ColorItemReal</strong> used to provided convenient shortcuts for initializing a {@link Color}.
 *
 * @author Sébastien Bordes
 */
public interface ColorItemReal extends org.jrebirth.af.api.resource.color.ColorItem {

    /**
     * {@inheritDoc}
     */
    @Override
    default ResourceBuilder<ColorItem, ColorParams, Color> builder() {
        return ResourceBuilders.COLOR_BUILDER;
    }

    /**
     * The interface <strong>RGB255</strong> provides shortcuts method used to build and register a {@link RGB255Color}.
     */
    interface RGB255 extends ColorItemReal {

        /**
         * Build and register a {@link RGB255Color} {@link ColorParams}.
         *
         * @param red the red component [0-255]
         * @param green the green component [0-255]
         * @param blue the blue component [0-255]
         * @param opacity the color opacity [0.0-1.0]
         */
        default void rgb255(final int red, final int green, final int blue, final double opacity) {
            set(new RGB255Color(red, green, blue, opacity));
        }

        /**
         * Build and register a {@link RGB255Color} {@link ColorParams}.
         *
         * @param red the red component [0-255]
         * @param green the green component [0-255]
         * @param blue the blue component [0-255]
         */
        default void rgb255(final int red, final int green, final int blue) {
            set(new RGB255Color(red, green, blue));
        }

    }

    /**
     * The interface <strong>RGB01</strong> provides shortcuts method used to build and register a {@link RGB01Color}.
     */
    interface RGB01 extends ColorItemReal {

        /**
         * Build and register a {@link RGB01Color} {@link ColorParams}.
         *
         * @param red the red value [0.0-1.0]
         * @param green the green value [0.0-1.0]
         * @param blue the blue value [0.0-1.0]
         * @param opacity the color opacity component [0.0-1.0]
         */
        default void rgb(final double red, final double green, final double blue, final double opacity) {
            set(new RGB01Color(red, green, blue, opacity));
        }

        /**
         * Build and register a {@link RGB01Color} {@link ColorParams}.
         *
         * @param red the red value [0.0-1.0]
         * @param green the green value [0.0-1.0]
         * @param blue the blue value [0.0-1.0]
         */
        default void rgb(final double red, final double green, final double blue) {
            set(new RGB01Color(red, green, blue));
        }

    }

    /**
     * The interface <strong>HSB</strong> provides shortcuts method used to build and register a {@link HSBColor}.
     */
    interface HSB extends ColorItemReal {

        /**
         * Build and register a {@link HSBColor} {@link ColorParams}.
         *
         * @param hue the color hue [0.0-360.0] in degrees
         * @param saturation the color saturation [0.0-1.0]
         * @param brightness the color brightness [0.0-1.0]
         * @param opacity the color opacity [0.0-1.0]
         */
        default void hsb(final double hue, final double saturation, final double brightness, final double opacity) {
            set(new HSBColor(hue, saturation, brightness, opacity));
        }

        /**
         * Build and register a {@link HSBColor} {@link ColorParams}.
         *
         * @param hue the color hue [0.0-360.0] in degrees
         * @param saturation the color saturation [0.0-1.0]
         * @param brightness the color brightness [0.0-1.0]
         */
        default void hsb(final double hue, final double saturation, final double brightness) {
            set(new HSBColor(hue, saturation, brightness));
        }

    }

    /**
     * The interface <strong>Web</strong> provides shortcuts method used to build and register a {@link WebColor}.
     */
    interface Web extends ColorItemReal {

        /**
         * Build and register a {@link WebColor} {@link ColorParams}.
         *
         * @param hex the hexadecimal value [0-9A-F]{6} (without 0x or #)
         * @param opacity the color opacity [0.0-1.0]
         */
        default void web(final String hex, final double opacity) {
            set(new WebColor(hex, opacity));
        }

        /**
         * Build and register a {@link WebColor} {@link ColorParams}.
         *
         * @param hex the hexadecimal value [0-9A-F]{6} (without 0x or #)
         */
        default void web(final String hex) {
            set(new WebColor(hex));
        }

    }

    /**
     * The interface <strong>Gray</strong> provides shortcuts method used to build and register a {@link GrayColor}.
     */
    interface Gray extends ColorItemReal {

        /**
         * Build and register a {@link GrayColor} {@link ColorParams}.
         *
         * @param gray the gray component [0.0-1.0]
         * @param opacity the color opacity [0.0-1.0]
         */
        default void gray(final double gray, final double opacity) {
            set(new GrayColor(gray, opacity));
        }

        /**
         * Build and register a {@link GrayColor} {@link ColorParams}.
         *
         * @param gray the gray component [0.0-1.0]
         */
        default void gray(final double gray) {
            set(new GrayColor(gray));
        }

    }
}
