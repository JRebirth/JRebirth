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
package org.jrebirth.analyzer.ui.editor;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.color.ColorBuilder;
import org.jrebirth.core.resource.color.ColorEnum;
import org.jrebirth.core.resource.color.ColorParams;
import org.jrebirth.core.resource.color.WebColor;

/**
 * The class <strong>BallColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum BallColors implements ColorEnum {

    APPLICATION(new WebColor("70CD43")),

    NOTIFIER(new WebColor("FDFD89")),

    GLOBAL_FACADE(new WebColor("FC1A9C")),

    COMMAND_FACADE(new WebColor("963593")),

    SERVICE_FACADE(new WebColor("FD1CFD")),

    UI_FACADE(new WebColor("FD2C33")),

    COMMAND(new WebColor("D9E032")),

    SERVICE(new WebColor("FAED30")),

    MODEL(new WebColor("F1693A")),

    VIEW(new WebColor("4AACF2")),

    ;

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private BallColors(final ColorParams colorParams) {
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
