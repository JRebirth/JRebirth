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
package org.jrebirth.af.core.resource.provided.parameter;

import static org.jrebirth.af.core.resource.Resources.create;

import java.util.Collections;
import java.util.List;

import org.jrebirth.af.api.resource.image.ImageExtension;
//import org.jrebirth.af.api.resource.image.ImageParams;
import org.jrebirth.af.api.resource.parameter.ParameterItem;
import org.jrebirth.af.core.resource.image.RelImage;
import org.jrebirth.af.core.resource.style.StyleSheet;

/**
 * The class <strong>JRebirthParameters</strong>.
 *
 * Parameters used by JRebirth Application Framework itself
 *
 * @author Sébastien Bordes
 */
public interface ResourceParameters {

    /** The image name of the NOT_AVAILABLE_IMAGE parameter, this image can lead to StackOverFlowError when it was not available. */
    String NOT_AVAILABLE_IMAGE_NAME = "NotAvailableImage";

    /**************************************************************************************/
    /** _________________________Application Resource Parameters.___________________________ */
    /**************************************************************************************/

    /** Fonts default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<List<String>> FONT_FOLDER = create("fontsFolder", Collections.singletonList("fonts"));

    /** Images default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<List<String>> IMAGE_FOLDER = create("imagesFolder", Collections.singletonList("images"));

    /**
     * The <code>NOT_AVAILABLE_IMAGE</code> field is used to define the image to use when an image is missing.
     */
    ParameterItem<RelImage> NOT_AVAILABLE_IMAGE = create("notAvailableImage", new RelImage(NOT_AVAILABLE_IMAGE_NAME, ImageExtension.PNG));

    /** Styles default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<List<String>> STYLE_FOLDER = create("stylesFolder", Collections.singletonList("styles"));

    /**
     * The <code>DEFAULT_CSS</code> field is used to parameterize the name of the default style sheet.
     */
    ParameterItem<StyleSheet> DEFAULT_CSS = create("defaultStyleSheet", new StyleSheet("default"));

    /**
     * The <code>TRUE_TYPE_FONT_EXTENSION</code> field is used to define the font file extension .
     */
    ParameterItem<String> TRUE_TYPE_FONT_EXTENSION = create("trueTypeFontExtension", ".ttf");

}
