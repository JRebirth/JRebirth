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
    ParameterItemBase<String> TEST_STRING_PARAM_1 = ParameterItemBase.build(new ObjectParameter<String>("fonts"));
    ParameterItemBase<String> TEST_STRING_PARAM_2 = ParameterItemBase.build(new ObjectParameter<String>("fontsFolder"));
    ParameterItemBase<String> TEST_STRING_PARAM_3 = ParameterItemBase.build("fontsFolder", "fonts");

    /**************************************************************************************/
    /** ______________________________Integer Parameters.________________________________ */
    /**************************************************************************************/
    ParameterItemBase<Integer> TEST_INTEGER_WIDTH = ParameterItemBase.build("stageWidth", 800);

}
