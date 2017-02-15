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
package org.jrebirth.af.api.link;

import java.util.List;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.command.CommandBean;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.contract.WaveData;

/**
 *
 * The class <strong>CommandReady</strong>.
 *
 * @author Sébastien Bordes
 */
public interface CommandReady {

    /**
     * Return the command singleton or part of multiton.
     *
     * @param clazz the service class to find
     * @param keyPart the unique key (in option)
     *
     * @param <C> a sub class of command
     *
     * @return a command instance
     */
    <C extends Command> C getCommand(final Class<C> clazz, final Object... keyPart);

    /**
     * Return the command singleton or part of multiton according to {@link UniqueKey}.
     *
     * @param commandKey the key that describe the searched {@link Command} component
     *
     * @param <C> a sub class of {@link Command}
     *
     * @return a command instance
     */
    <C extends Command> C getCommand(final UniqueKey<C> commandKey);

    /**
     * Return the list of command singleton or part of multiton.
     *
     * @param clazz the service class to find
     * @param keyPart the unique key (in option)
     *
     * @param <C> a sub class of command
     *
     * @return a list of command instance
     */
    <C extends Command> List<C> getCommands(final Class<C> clazz, final Object... keyPart);

    /**
     * Return the list of command singleton or part of multiton according to {@link UniqueKey}.
     *
     * @param commandKey the key that describe the searched {@link Command} component
     *
     * @param <C> a sub class of {@link Command}
     *
     * @return a list of command instance
     */
    <C extends Command> List<C> getCommands(final UniqueKey<C> commandKey);

    /**
     * Send a wave used to call a command.
     *
     * The command will be called from JRebirthThread and could execute itself from another thread.
     *
     * @param commandClass the command class to call
     * @param data the data to transport
     *
     * @return the wave created and sent to JIT, be careful when you use a strong reference it can hold a lot of objects
     */
    Wave callCommand(final Class<? extends Command> commandClass, final WaveData<?>... data);

    /**
     * Send a wave used to call a command.
     *
     * The command will be called from JRebirthThread and could execute itself from another thread.
     *
     * @param commandClass the command class to call
     * @param waveBean the WaveBean that holds all required data
     * @param waveBeans the extra Wave Beans that holds all other required data
     *
     * @param <WB> the type of the wave bean to used
     *
     * @return the wave created and sent to JIT, be careful when you use a strong reference it can hold a lot of objects
     */
    Wave callCommand(final Class<? extends CommandBean<? extends WaveBean>> commandClass, final WaveBean waveBean, final WaveBean... waveBeans);

}
