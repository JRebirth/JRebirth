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
package org.jrebirth.core.service;

import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.wave.Wave;

/**
 * 
 * The interface <strong>Service</strong>.
 * 
 * The contract for the service layer.
 * 
 * @author Sébastien Bordes
 */
public interface Service extends FacadeReady<Service> {

    /**
     * Do a specific action and process the wave.
     * 
     * @param wave
     */
    <T extends Object> void returnData(Wave wave);

}
