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

import org.jrebirth.af.core.resource.parameter.ObjectParameter;
import org.jrebirth.af.core.resource.parameter.ParameterItem;

import static org.jrebirth.af.core.resource.Resources.create;

/**
 * The class <strong>TestParameters</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface TestParameters {

    /**************************************************************************************/
    /** ______________________________String Parameters._________________________________ */
    /**************************************************************************************/

    /** The web color. */
    ParameterItem<String> TEST_STRING_PARAM_1 = create(new ObjectParameter<String>("fonts"));
    ParameterItem<String> TEST_STRING_PARAM_2 = create(new ObjectParameter<String>("fontsFolder"));
    ParameterItem<String> TEST_STRING_PARAM_3 = create("fontsFolderFake", "font");
    ParameterItem<String> TEST_STRING_PARAM_4 = create("fontsFolder", "font");

    /**************************************************************************************/
    /** ______________________________Integer Parameters.________________________________ */
    /**************************************************************************************/

    ParameterItem<Integer> TEST_INTEGER_WIDTH = create("stageWidth", 800);
    ParameterItem<Integer> TEST_INTEGER_HEIGHT = create("stageHeight", 600);

    /**************************************************************************************/
    /** ______________________________Overridabl Parameters.________________________________ */
    /**************************************************************************************/

    ParameterItem<Integer> OVERRIDABLE_PARAM = create("minDuration", 1000);

}
