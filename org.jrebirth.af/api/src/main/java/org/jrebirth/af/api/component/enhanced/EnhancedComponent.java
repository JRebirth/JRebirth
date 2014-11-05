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
package org.jrebirth.af.api.component.enhanced;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.wave.WaveType;
import org.jrebirth.af.api.wave.checker.WaveChecker;

/**
 * The interface <strong>EnhancedComponent</strong>.
 *
 * Define the contract used to manage waves.
 *
 * @author Sébastien Bordes
 *
 * @param <C> A type that implements Component
 */
public interface EnhancedComponent<C extends Component<C>> extends Component<C> {

    /**
     * Register a wave call back contract.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * @param waveChecker the wave checker used to forward the wave only if the checker return true
     * @param callType the wave type mapped to this service.
     * @param returnCommandClass the command class to call to process the service result
     */
    void registerCallback(final WaveChecker waveChecker, final WaveType callType, Class<? extends Command> returnCommandClass);

    /**
     * Register a wave call back contract.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * @param callType the wave type mapped to this service.
     * @param returnCommandClass the command class to call to process the service result
     */
    void registerCallback(final WaveType callType/* , final WaveType responseType */, Class<? extends Command> returnCommandClass);

    /**
     * Return the return Command to call for given wave type.
     *
     * @param waveType the source wave type
     *
     * @return Returns the Command class to call (or null.
     */
    Class<? extends Command> getReturnCommand(final WaveType waveType);

}
