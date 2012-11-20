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
package org.jrebirth.core.command.basic;

import org.jrebirth.core.command.DefaultPoolCommand;
import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>PrepareModelCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class PrepareModelCommand extends DefaultPoolCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        // Retrieved the model class
        final Class<? extends Model> modelClass = getWaveBean(wave).getModelClass();

        if (modelClass == null) {
            throw new CoreRuntimeException("ModelClass is null");
        }

        // Build the first root node into the thread pool and link it to the waveBean
        getWaveBean(wave).setCreatedNode(getLocalFacade().getGlobalFacade().getUiFacade().retrieve(modelClass).getView().getRootNode());
    }

    /**
     * Get the wave bean and cast it.
     * 
     * @param wave the wave that hold the bean
     * 
     * @return the casted wave bean
     */
    public ShowModelWaveBean getWaveBean(final Wave wave) {
        return (ShowModelWaveBean) wave.getWaveBean();
    }

}
