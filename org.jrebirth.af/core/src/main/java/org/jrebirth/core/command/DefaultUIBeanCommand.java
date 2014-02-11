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
package org.jrebirth.core.command;

import org.jrebirth.core.concurrent.RunInto;
import org.jrebirth.core.concurrent.RunType;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveBean;

/**
 * The class <strong>DefaultUIBeanCommand</strong>.
 * 
 * The default empty class for UI commands.
 * 
 * @param <WB> The WaveBean type used for this command (by default you can use the WaveBean interface)
 * 
 * @author Sébastien Bordes
 */
@RunInto(RunType.JAT)
public class DefaultUIBeanCommand<WB extends WaveBean> extends AbstractSingleCommand<WB> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        // Nothing to do yet by the default UI command, must be overridden
    }

    // @Override
    // public void setParentCommand(Command parentCommand) {
    // // Nothing to do yet
    //
    // }

}
