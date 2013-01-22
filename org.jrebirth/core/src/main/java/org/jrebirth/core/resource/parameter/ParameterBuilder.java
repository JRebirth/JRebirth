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
package org.jrebirth.core.resource.parameter;

import javafx.scene.text.Font;

import org.jrebirth.core.resource.factory.AbstractResourceBuilder;
import org.jrebirth.core.resource.font.FamilyFont;
import org.jrebirth.core.resource.font.RealFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>FontBuilder</strong>.
 * 
 * Class used to manage font with weak reference.
 * 
 * @author Sébastien Bordes
 */
public final class ParameterBuilder extends AbstractResourceBuilder<ParameterEnum, ParameterParams, Object> {

    /**
     * The <code>RESOURCE_SEPARATOR</code>.
     */
    private static final String R_SEP = "/";

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterBuilder.class);

    /** The root folder that store all application fonts. */
    private static String fontsFolder = "font";

    /**
     * {@inheritDoc}
     */
    @Override
    protected Font buildResource(final ParameterParams jrFont) {
        Font font = null;
        if (jrFont instanceof RealFont) {
            // Build the requested font
        } else if (jrFont instanceof FamilyFont) {
            // Build a family like font
        } else {
            // Return the default font
            font = Font.getDefault();
        }
        return font;
    }

}
