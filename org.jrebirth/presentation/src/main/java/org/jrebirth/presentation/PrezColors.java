/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.presentation;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.color.ColorBuilder;
import org.jrebirth.core.resource.color.ColorEnum;
import org.jrebirth.core.resource.color.ColorParams;
import org.jrebirth.core.resource.color.RGB255Color;
import org.jrebirth.core.resource.color.WebColor;

/**
 * The class <strong>PrezColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum PrezColors implements ColorEnum {

    /** Color for slide title, white. */
    SLIDE_TITLE(new WebColor("FFFFFF", 1.0)),

    /** Color for blue shape, xxx. */
    SHAPE_BLUE(new WebColor("3495CE", 1.0)),

    /** Color for drop shadow, black. */
    DROP_SHADOW(new WebColor("000000", 0.8)),
    /** Color for inner shadow, white. */
    INNER_SHADOW(new WebColor("FFFFFF", 0.3)),

    /** Color for first gradient, xxx. */
    GRADIENT_1(new WebColor("1AA2AC", 1.0)),
    /** Color for second gradient, xxx. */
    GRADIENT_2(new WebColor("F04F24", 1.0)),
    /** Color for third gradient, xxxx. */
    GRADIENT_3(new WebColor("FFF200", 1.0)),

    /** Color for splash text, xxx. */
    SPLASH_TEXT(new RGB255Color(60, 60, 70));

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private PrezColors(final ColorParams colorParams) {
        factory().storeParams(this, colorParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color get() {
        return factory().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorBuilder factory() {
        return (ColorBuilder) ResourceBuilders.COLOR_BUILDER.use();
    }
}
