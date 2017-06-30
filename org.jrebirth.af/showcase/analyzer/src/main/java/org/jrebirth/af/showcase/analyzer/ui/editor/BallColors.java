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
package org.jrebirth.af.showcase.analyzer.ui.editor;

import javafx.scene.paint.Color;

import org.jrebirth.af.api.resource.color.ColorParams;
import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.resource.color.ColorBuilder;
import org.jrebirth.af.core.resource.color.ColorItemBase;
import org.jrebirth.af.core.resource.color.WebColor;

/**
 * The class <strong>BallColors</strong>.
 *
 * @author Sébastien Bordes
 *
 */
public enum BallColors implements ColorItemBase {

    /** The application ball background color. */
    APPLICATION(new WebColor("70CD43")),

    /** The notifier ball background color. */
    NOTIFIER(new WebColor("FDFD89")),

    /** The global facade ball background color. */
    GLOBAL_FACADE(new WebColor("FC1A9C")),

    /** The command facade ball background color. */
    COMMAND_FACADE(new WebColor("963593")),

    /** The service facade ball background color. */
    SERVICE_FACADE(new WebColor("FD1CFD")),

    /** The UI Facade ball background color. */
    UI_FACADE(new WebColor("FD2C33")),

    /** The Command ball background color. */
    COMMAND(new WebColor("D9E032")),

    /** The Service ball background color. */
    SERVICE(new WebColor("FAED30")),

    /** The Model ball background color. */
    MODEL(new WebColor("F1693A")),

    /** The View ball background color. */
    VIEW(new WebColor("4AACF2"));

    /**
     * Private Constructor.
     *
     * @param colorParams the primitive values for the color
     */
    private BallColors(final ColorParams colorParams) {
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
