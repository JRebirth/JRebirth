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
package org.jrebirth.core.wave;

/**
 * 
 * The interface <strong>WaveType</strong>.
 * 
 * A WaveType could be an enumeration that return an action name.
 * 
 * @author Sébastien Bordes
 */
public interface WaveType {

    /**
     * Return the name of the wave type, commonly bound on enum.name() method.
     * 
     * @return the name of the wave type
     */
    String getName();

    /**
     * Return the method to process in the processor class.
     * 
     * @return a method name or null
     */
    String getAction();

}
