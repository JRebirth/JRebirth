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

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;

/**
 * The interface <strong>Command</strong> is used to run atomic and reusable action.
 *
 * @param <WB> the type of the {@link WaveBean} to use
 *
 * @author Sébastien Bordes
 */
public interface CommandBean<WB extends WaveBean> extends Command {

    /**
     * Get the wave bean and cast it.
     *
     * @param wave the wave that hold the bean
     *
     * @return the casted wave bean
     */
    @Override
    WB waveBean(final Wave wave);
}
