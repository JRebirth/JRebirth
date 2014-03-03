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
package org.jrebirth.af.core.command.basic.showmodel;

import org.jrebirth.af.core.command.DefaultMultiBeanCommand;

/**
 * The class <strong>ShowModelCommand</strong>.
 * 
 * Run into JIT and is sequential
 * 
 * @author Sébastien Bordes
 */
public class ShowModelCommand extends DefaultMultiBeanCommand<DisplayModelWaveBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void manageSubCommand() {
        addCommandClass(PrepareModelCommand.class);
        addCommandClass(AttachModelCommand.class);

    }

}
