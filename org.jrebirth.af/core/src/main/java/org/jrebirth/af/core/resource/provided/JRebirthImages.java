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

import org.jrebirth.af.api.resource.image.ImageExtension;
import org.jrebirth.af.core.resource.image.ImageEnum;
import org.jrebirth.af.core.resource.provided.parameter.ResourceParameters;

/**
 * The class <strong>JRebirthImages</strong>.
 *
 * Images used by JRebirth Application Framework itself
 *
 * @author Sébastien Bordes
 */
public enum JRebirthImages implements ImageEnum {

    /**************************************************************************************/
    /** _____________________________Application Core Images.____________________________ */
    /**************************************************************************************/

    // @formatter:off

    /** The Not/Available default image. */
    NOT_AVAILABLE {{ set(ResourceParameters.NOT_AVAILABLE_IMAGE.get()); }},

    JR_LOGO_16 {{ rel("JRebirth_16x16", ImageExtension.PNG); }},

    JR_LOGO_32 {{ rel("JRebirth_32x32", ImageExtension.PNG); }},

    JR_LOGO_64 {{ rel("JRebirth_64x64", ImageExtension.PNG); }},

    JR_LOGO_128 {{ rel("JRebirth_128x128", ImageExtension.PNG); }},

    JR_LOGO_256 {{ rel("JRebirth_256x256", ImageExtension.PNG); }};

}
