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
package org.jrebirth.presentation.resources;

import org.jrebirth.core.resource.color.ColorItem;
import org.jrebirth.core.resource.color.RGB255Color;
import org.jrebirth.core.resource.color.WebColor;

import static org.jrebirth.core.resource.Resources.create;

/**
 * The class <strong>PrezColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public interface PrezColors {

    /** Color for slide title, white. */
    ColorItem SLIDE_TITLE = create(new WebColor("FFFFFF", 1.0));

    /** Color for slide title, white. */
    ColorItem SUB_TITLE = create(new WebColor("FFF6EF", 1.0));

    /** Color for blue shape, xxx. */
    ColorItem SHAPE_BLUE = create(new WebColor("3495CE", 1.0));

    /** Color for drop shadow, black. */
    ColorItem DROP_SHADOW = create(new WebColor("000000", 0.8));

    /** Color for inner shadow, white. */
    ColorItem INNER_SHADOW = create(new WebColor("FFFFFE", 0.3));

    /** Color for first gradient, xxx. */
    ColorItem GRADIENT_1 = create(new WebColor("1AA2AC", 1.0));

    /** Color for second gradient, xxx. */
    ColorItem GRADIENT_2 = create(new WebColor("F04F24", 1.0));

    /** Color for third gradient, xxxx. */
    ColorItem GRADIENT_3 = create(new WebColor("FFF200", 1.0));

    /** Color for splash text, xxx. */
    ColorItem SPLASH_TEXT = create(new RGB255Color(60, 60, 70));

}
