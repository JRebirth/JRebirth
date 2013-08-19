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
package org.jrebirth.core.resource.provided;

import org.jrebirth.core.resource.color.WebColor;
import org.jrebirth.core.resource.image.ImageExtension;
import org.jrebirth.core.resource.image.LocalImage;
import org.jrebirth.core.resource.parameter.ParameterItem;
import org.jrebirth.core.resource.style.StyleSheet;

import static org.jrebirth.core.resource.Resources.create;

/**
 * The class <strong>JRebirthParameters</strong>.
 * 
 * Parameters used by JRebirth Application Framework itself
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthParameters {

    /**************************************************************************************/
    /** __________________________Application Core Parameters.___________________________ */
    /**************************************************************************************/

    /** Developer provides more information when dynamic API is broken (Wave Contract). */
    ParameterItem<Boolean> DEVELOPER_MODE = create("developerMode", false);

    /** Fir Close Retry Delay in milliseconds, time to wait when application try to close the first time. */
    ParameterItem<Integer> CLOSE_RETRY_DELAY_FIRST = create("closeRetryDelayFirst", 4000);

    /** Close Retry Delay in milliseconds, time to wait when application try to close all other time. */
    ParameterItem<Integer> CLOSE_RETRY_DELAY_OTHER = create("closeRetryDelayOther", 1000);

    /** Pool size of JRebirth Thread Pool. */
    ParameterItem<Integer> THREAD_POOL_SIZE = create("threadPoolSize", 4);

    /** Fonts default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> FONT_FOLDER = create("fontsFolder", "fonts");

    /** Images default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> IMAGE_FOLDER = create("imagesFolder", "images");

    /** Styles default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> STYLE_FOLDER = create("stylesFolder", "styles");

    /**
     * The <code>DEFAULT_CSS</code> field is used to parameterize the name of the default style sheet.
     */
    ParameterItem<StyleSheet> DEFAULT_CSS = create("defaultStyleSheet", new StyleSheet("default"));

    /**
     * The <code>TRUE_TYPE_FONT_EXTENSION</code> field is used to define the font file extension .
     */
    ParameterItem<String> TRUE_TYPE_FONT_EXTENSION = create("trueTypeFontExtension", ".ttf");

    /**
     * The <code>WAVE_HANDLER_PREFIX</code> field is used to add a prefix to custom wave handler method of JRebirth components. They will be named like this : doMyAction(Wave) after being renamed in
     * camel case.
     */
    ParameterItem<String> WAVE_HANDLER_PREFIX = create("waveHandlerPrefix", "DO_");

    /**
     * The <code>NOT_AVAILABLE_IMAGE</code> field is used to define the image to use when an image is missing.
     */
    ParameterItem<LocalImage> NOT_AVAILABLE_IMAGE = create("notAvailableImage", new LocalImage("NotAvailableImage", ImageExtension.PNG));

    /**
     * The <code>APPLICATION_NAME</code> field is used to define title of the application displayed by stage window.<br />
     * The default value contains a <b>{}</b> that will be replaced by application class simple name.
     */
    ParameterItem<String> APPLICATION_NAME = create("applicationName", "{} powered by JRebirth");

    /**
     * The <code>APPLICATION_VERSION</code> field is used to define the application version.
     */
    ParameterItem<String> APPLICATION_VERSION = create("applicationVersion", "0.0.0");

    /**************************************************************************************/
    /** _________________________Application Stage Parameters.___________________________ */
    /**************************************************************************************/

    /** The application stage width. */
    ParameterItem<Integer> APPLICATION_STAGE_WIDTH = create("applicationStageWidth", 800);

    /** The application stage height. */
    ParameterItem<Integer> APPLICATION_STAGE_HEIGHT = create("applicationStageHeight", 600);

    /** The application stage height. */
    ParameterItem<WebColor> APPLICATION_STAGE_BG_COLOR = create("applicationStageBgColor", new WebColor("FFFFFF", 1.0));
}
