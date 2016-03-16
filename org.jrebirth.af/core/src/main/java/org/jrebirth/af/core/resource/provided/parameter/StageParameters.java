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

//import org.jrebirth.af.api.resource.image.ImageParams;
import org.jrebirth.af.api.resource.parameter.ParameterItem;
import org.jrebirth.af.core.resource.color.WebColor;

/**
 * The class <strong>StageParameters</strong>.
 *
 * Parameters used by JRebirth Application Framework itself
 *
 * @author Sébastien Bordes
 */
public interface StageParameters {

    /**************************************************************************************/
    /** _________________________Application Stage Parameters.___________________________ */
    /**************************************************************************************/

    /**
     * The <code>APPLICATION_ICONS</code> field is used to define the icons used by the stage of the application. "JRebirth_16x16,JRebirth_32x32,JRebirth_64x64,JRebirth_128x128,JRebirth_256x256"
     */
    /*
     * ParameterItem<List<ImageParams>> APPLICATION_ICONS = create("applicationIcons", Arrays.asList( JRebirthImages.JR_LOGO_16.getParam(), JRebirthImages.JR_LOGO_32.getParam(),
     * JRebirthImages.JR_LOGO_64.getParam(), JRebirthImages.JR_LOGO_128.getParam(), JRebirthImages.JR_LOGO_256.getParam() ));
     */
    /**
     * The <code>APPLICATION_NAME</code> field is used to define title of the application displayed by stage window.<br />
     * The default value contains a <b>{}</b> that will be replaced by application class simple name.
     */
    ParameterItem<String> APPLICATION_NAME = create("applicationName", "{} powered by JRebirth");

    /**
     * The <code>APPLICATION_VERSION</code> field is used to define the application version.
     */
    ParameterItem<String> APPLICATION_VERSION = create("applicationVersion", "0.0.0");

    /** The application scene width. */
    ParameterItem<Integer> APPLICATION_SCENE_WIDTH = create("applicationSceneWidth", 800);

    /** The application scene height. */
    ParameterItem<Integer> APPLICATION_SCENE_HEIGHT = create("applicationSceneHeight", 600);

    /** The application scene background color. */
    ParameterItem<WebColor> APPLICATION_SCENE_BG_COLOR = create("applicationSceneBgColor", new WebColor("000000", 0.0));

}
