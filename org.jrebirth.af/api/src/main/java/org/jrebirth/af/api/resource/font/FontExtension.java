/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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

/**
 * The enumeration <strong>FontExtension</strong> is used to provides all possible font extension supported by JavaFX toolkit.
 *
 * @author Sébastien Bordes
 */
public enum FontExtension {

    /** The .ttf file extension. */
    TTF,

    /** The .otf image file extension. */
    OTF;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
