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
package org.jrebirth.af.api.command;

import org.jrebirth.af.api.component.behavior.BehavioredComponent;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;

/**
 * The interface <strong>Command</strong> is used to run atomic and reusable action.
 *
 * @author Sébastien Bordes
 */
public interface Command extends BehavioredComponent<Command> {

    /**
     * Run the command.
     *
     * @return the wave created to launch the command, it allows to track its internal process
     */
    Wave run();

    /**
     * Run the command.
     *
     * @param wave the wave that have triggered this command
     *
     * @return the wave created to launch the command, it allows to track its internal process
     */
    Wave run(final Wave wave);

    // /**
    // * Link a parent command.
    // *
    // * @param parentCommand the parent command
    // */
    // void setParentCommand(final Command parentCommand);

    /**
     * Get the wave bean and cast it.
     *
     * @param wave the wave that hold the bean
     *
     * @return the casted wave bean
     */
    WaveBean waveBean(final Wave wave);

}
