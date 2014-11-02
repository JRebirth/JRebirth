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

import org.jrebirth.af.api.wave.WaveBean;

/**
 * The class <strong>MultiCommand</strong>.
 *
 * The contract to chain multiple commands.
 *
 * @author Sébastien Bordes
 *
 * @param <WB> The WaveBean type used for this command (by default you can use the WaveBean interface)
 */
public interface MultiCommand<WB extends WaveBean> extends CommandBean<WB> {

    // /**
    // * Add a command to the queue.
    // *
    // * @param commandClass the class of the command to add
    // */
    // void addCommandClass(final Class<? extends Command> commandClass);

    /**
     * Build an {@link UniqueKey} for sub command.
     *
     * @param subCommandClass the class of the sub command to add
     * @param keyPart the optional key part of the sub command
     *
     * @return the sub command unique key
     */
    // UniqueKey<? extends Command> getCommandKey(final Class<? extends Command> subCommandClass, final Object... keyPart);

}
