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
package org.jrebirth.af.core.command;

import org.jrebirth.af.core.wave.DefaultWaveBean;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBase;
import org.jrebirth.af.core.wave.WaveBean;
import org.jrebirth.af.core.wave.WaveBuilder;
import org.jrebirth.af.core.wave.WaveGroup;

/**
 * The class <strong>WaveBuilder</strong>.
 * 
 * Base builder used to build a custom wave.
 * 
 * @author Sébastien Bordes
 * 
 * @param <B> The builder generic type
 * @param <WB> the waveBean sub type to use
 */
public class CommandWaveBuilder<B extends CommandWaveBuilder<B, WB>, WB extends WaveBean> extends WaveBuilder<B> {

    /** The class of the command to launch. */
    private final Class<? extends Command> commandClass;

    /** The class of the wave bean to use. */
    private final Class<WB> waveBeanClass;

    /**
     * Default Constructor.
     * 
     * @param commandClass the command class to use
     */
    @SuppressWarnings("unchecked")
    public CommandWaveBuilder(final Class<? extends Command> commandClass) {
        this(commandClass, (Class<WB>) DefaultWaveBean.class);
    }

    /**
     * Default Constructor.
     * 
     * @param commandClass the class of the command to launch
     * @param waveBeanClass the class of the wave bean to use
     */
    public CommandWaveBuilder(final Class<? extends Command> commandClass, final Class<WB> waveBeanClass) {
        super();
        this.commandClass = commandClass;
        this.waveBeanClass = waveBeanClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(final WaveBase paramWave) {

        paramWave.setWaveGroup(WaveGroup.CALL_COMMAND);
        paramWave.setRelatedClass(this.commandClass);
        paramWave.setWaveBeanClass(this.waveBeanClass);

    }

    /**
     * Get the wave Bean from the wave and cast it.
     * 
     * @param wave the wave that hold the bean
     * 
     * @return the casted wavebean
     */
    @SuppressWarnings("unchecked")
    protected WB getWaveBean(final Wave wave) {
        return (WB) wave.getWaveBean();
    }

}
