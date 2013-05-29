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

import org.jrebirth.core.resource.color.ColorBuilder;
import org.jrebirth.core.resource.font.FontBuilder;
import org.jrebirth.core.resource.image.ImageBuilder;
import org.jrebirth.core.resource.parameter.ParameterBuilder;

/**
 * The interface <strong>ResourceBuilders</strong> is used to retrieve resource builder singleton.
 * 
 * @author Sébastien Bordes
 */
public interface ResourceBuilders {

    /** The factory used to manage application parameters. */
    ParameterBuilder PARAMETER_BUILDER = new ParameterBuilder();

    /** The factory used to manage colors. */
    ColorBuilder COLOR_BUILDER = new ColorBuilder();

    /** The factory used to manage fonts. */
    FontBuilder FONT_BUILDER = new FontBuilder();

    /** The factory used to manage image. */
    ImageBuilder IMAGE_BUILDER = new ImageBuilder();

}
