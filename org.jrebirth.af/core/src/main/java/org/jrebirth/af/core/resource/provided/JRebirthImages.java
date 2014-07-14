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
package org.jrebirth.af.core.resource.provided;

import org.jrebirth.af.core.resource.image.ImageItem;

import static org.jrebirth.af.core.resource.Resources.create;

/**
 * The class <strong>JRebirthImages</strong>.
 *
 * Images used by JRebirth Application Framework itself
 *
 * @author Sébastien Bordes
 */
public interface JRebirthImages {

    /**************************************************************************************/
    /** _____________________________Application Core Images.____________________________ */
    /**************************************************************************************/

    /** The Not/Available default image. */
    ImageItem NOT_AVAILABLE = create(JRebirthParameters.NOT_AVAILABLE_IMAGE.get());

}
