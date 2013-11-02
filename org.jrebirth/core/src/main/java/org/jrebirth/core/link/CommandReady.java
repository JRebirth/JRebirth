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
package org.jrebirth.core.link;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.command.CommandBean;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveBean;
import org.jrebirth.core.wave.WaveData;

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
     * @param waveBean the WaveBean that holds all required wave data
     *
     * @param <WB> the type of the wave bean to used
     *
     * @return the wave created and sent to JIT, be careful when you use a strong reference it can hold a lot of objects
     */
    <WB extends WaveBean> Wave callCommand(final Class<? extends CommandBean<WB>> commandClass, final WB waveBean);

}
