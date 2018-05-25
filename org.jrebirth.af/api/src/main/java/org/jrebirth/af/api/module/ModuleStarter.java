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
package org.jrebirth.af.api.module;

/**
 * The class <strong>ModuleStarter</strong> helps JRebirth engine to configure all modules.
 *
 * A module is a dependency (packaged as a jar) that used some JRebirth annotations to configure it.
 *
 * A class implementing {@link ModuleStarter} will be generated using annoation processor by adding this artifact within your build.
 *
 * @author Sébastien Bordes
 */
@FunctionalInterface
public interface ModuleStarter {

    /**
     * This method will be called when all modules are started during application boot up.
     */
    void start();

    /**
     * Priority used to launch Modules according to custom order.
     * 
     * The higher priority value will be called first.
     * 
     * @return the module priority from 0 to Integer.MAX_VALUE.
     */
    default int priority() {
        return 0;
    }
}
