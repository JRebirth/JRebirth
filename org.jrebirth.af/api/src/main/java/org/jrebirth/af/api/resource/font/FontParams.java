/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.api.resource.font;

import org.jrebirth.af.api.resource.ResourceParams;

/**
 * The class <strong>FontParams</strong>.
 *
 * @author Sébastien Bordes
 */
public interface FontParams extends ResourceParams {

    /**
     * @return Returns the font name.
     */
    FontName name();

    /**
     * Return the font size.
     *
     * @return the font size
     */
    double size();

    /**
     * Return the font extension.
     *
     * @return the font extension
     */
    FontExtension extension();

}
