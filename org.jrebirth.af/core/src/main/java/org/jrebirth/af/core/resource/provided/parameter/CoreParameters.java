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

import org.jrebirth.af.api.annotation.Description;
import org.jrebirth.af.api.annotation.DisplayName;
//import org.jrebirth.af.api.resource.image.ImageParams;
import org.jrebirth.af.api.resource.parameter.ParameterItem;

/**
 * The class <strong>JRebirthParameters</strong>.
 *
 * Parameters used by JRebirth Application Framework itself
 *
 * @author Sébastien Bordes
 */
public interface CoreParameters {

    /** The name of the AUTO_REFRESH parameter which is quite special because it modify how other parameters will be processed. */
    String AUTO_REFRESH_NAME = "autoRefreshResource";

    /**************************************************************************************/
    /** __________________________Application Core Parameters.___________________________ */
    /**************************************************************************************/

    /** Allow to auto refresh resource when resource params is updated. */
    @DisplayName("Auto Refresh Resource")
    @Description("Used to automatically refresh parameters when they have been updated")
    ParameterItem<Boolean> AUTO_REFRESH_RESOURCE = create(AUTO_REFRESH_NAME, false);

    /** Developer provides more information when dynamic API is broken (Wave Contract). */
    ParameterItem<Boolean> DEVELOPER_MODE = create("developerMode", false);

    /** The handler used while running in developer mode to manage unprocessed wave. */
    ParameterItem<Boolean> FOLLOW_UP_SERVICE_TASKS = create("followUpServiceTasks", false);

    /**
     * When true log code will be resolved according to Message_rb properties files. <br />
     * Disable it to improve performances, log could be translated later.
     */
    ParameterItem<Boolean> LOG_RESOLUTION = create("logResolution", true);

    /** Flag that activates the module.xml file parsing to register components using annotations. */
    ParameterItem<Boolean> PARSE_MODULE_CONFIG_FILE = create("parseModuleConfigFile", false);

    /** First Close Retry Delay in milliseconds, time to wait when application try to close the first time. */
    ParameterItem<Integer> CLOSE_RETRY_DELAY_FIRST = create("closeRetryDelayFirst", 4000);

    /** Close Retry Delay in milliseconds, time to wait when application try to close all other time. */
    ParameterItem<Integer> CLOSE_RETRY_DELAY_OTHER = create("closeRetryDelayOther", 1000);

    /** Pool size of JRebirth Thread Pool. */
    ParameterItem<Float> THREAD_POOL_SIZE_RATIO = create("threadPoolSizeRatio", 0.5f);

    /**
     * The <code>WAVE_HANDLER_PREFIX</code> field is used to add a prefix to custom wave handler method of JRebirth components. They will be named like this : doMyAction(Wave) after being renamed in
     * camel case.
     */
    ParameterItem<String> WAVE_HANDLER_PREFIX = create("waveHandlerPrefix", "DO_");

}
