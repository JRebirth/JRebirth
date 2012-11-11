/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.presentation;

import org.jrebirth.core.resource.font.FontName;

/**
 * The class <strong>FontsLoader</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum FontsLoader implements FontName {

    /** . */
    BRLNSB,

    /** . */
    DINk,

    /** . */
    OliJo,

    /** . */
    Harabara,

    /** . */
    Report_1942,

    /** . */
    arfmoochikncheez,

    /** . */
    Neuton_Cursive,

    /** . */
    BorisBlackBloxx;

    /**
     * {@inheritDoc}
     */
    @Override
    public String get() {
        return name();
    }

}
