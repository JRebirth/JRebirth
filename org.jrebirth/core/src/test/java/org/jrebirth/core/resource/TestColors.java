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
package org.jrebirth.core.resource;

import org.jrebirth.core.resource.color.ColorItem;
import org.jrebirth.core.resource.color.GrayColor;
import org.jrebirth.core.resource.color.HSBColor;
import org.jrebirth.core.resource.color.RGB01Color;
import org.jrebirth.core.resource.color.RGB255Color;
import org.jrebirth.core.resource.color.WebColor;

/**
 * The class <strong>TestColors</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface TestColors {

    /** The web color. */
    ColorItem TEST_COLOR_WEB_1 = ColorItem.build(new WebColor("0088D3"));

    /** The web color. */
    ColorItem TEST_COLOR_WEB_2 = ColorItem.build(new WebColor("0088D3", 1.0));

    /** The gray color. */
    ColorItem TEST_COLOR_GRAY_1 = ColorItem.build(new GrayColor(0.3));

    /** The gray color. */
    ColorItem TEST_COLOR_GRAY_2 = ColorItem.build(new GrayColor(0.5, 1.0));

    /** The hsb color. */
    ColorItem TEST_COLOR_HSB_1 = ColorItem.build(new HSBColor(45.0, 0.5, 0.5));

    /** The hsb color. */
    ColorItem TEST_COLOR_HSB_2 = ColorItem.build(new HSBColor(45.0, 0.5, 0.5, 1.0));

    /** The rgb 0-1 color. */
    ColorItem TEST_COLOR_RGB01_1 = ColorItem.build(new RGB01Color(0.2, 0.3, 0.4));

    /** The rgb 0-1 color. */
    ColorItem TEST_COLOR_RGB01_2 = ColorItem.build(new RGB01Color(0.2, 0.3, 0.4, 1.0));

    /** The rgb 255 color. */
    ColorItem TEST_COLOR_RGB255_1 = ColorItem.build(new RGB255Color(100, 60, 250));

    /** The rgb 255 color. */
    ColorItem TEST_COLOR_RGB255_2 = ColorItem.build(new RGB255Color(100, 60, 250, 1.0));

}
