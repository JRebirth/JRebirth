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
package org.jrebirth.af.core.resource.font;

/**
 * The interface <strong>FontName</strong>.
 * 
 * The font name used is the enum String name() method to retrieved the font name string.<br />
 * It will be transformed (or not) by a custom mechanism that replace '_' by space.
 * 
 * @author Sébastien Bordes
 */
public interface FontName {

    /**
     * Return the unique name of the font.
     * 
     * When a enumeration implements this interface the name() method is automatically provided.
     * 
     * @return the system name of the font
     */
    String name();

}
