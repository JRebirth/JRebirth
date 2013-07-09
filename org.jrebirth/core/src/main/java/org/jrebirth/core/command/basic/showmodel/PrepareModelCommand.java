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
package org.jrebirth.core.command.basic.showmodel;

import org.jrebirth.core.command.DefaultPoolCommand;
import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>PrepareModelCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class PrepareModelCommand extends DefaultPoolCommand {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PrepareModelCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        // Retrieved the model class
        // final Class<? extends Model> modelClass = (Class<? extends Model>) getWaveBean(wave).getShowModelKey().getClass();

        // final Object[] keyPart = getWaveBean(wave).getKeyPart() == null ? null : getWaveBean(wave).getKeyPart().toArray();

        final UniqueKey<Model> showModelKey = getWaveBean(wave).getShowModelKey();

        if (showModelKey == null) {
            LOGGER.error("ModelClass is null");
            throw new CoreRuntimeException("Illegal action : Model Class is null");
        }
        // Retrieve the mode according to its keyPart
        final Model modelInstance = getLocalFacade().getGlobalFacade().getUiFacade().retrieve(showModelKey);

        //
        // if (keyPart == null) {
        // modelInstance = getLocalFacade().getGlobalFacade().getUiFacade().retrieve(modelClass);
        // } else {
        // modelInstance = getLocalFacade().getGlobalFacade().getUiFacade().retrieve(modelClass, keyPart);
        // }

        if (modelInstance == null) {
            LOGGER.error("Model " + showModelKey.getClassField().getSimpleName() + " couldn't be created");
            throw new CoreRuntimeException("Illegal action : Model Instance is null: " + showModelKey.getClassField().getName());
        }

        // Attach the model to allow reuse later in the process
        getWaveBean(wave).setShowModel(modelInstance);

        // Build the first root node into the thread pool and link it to the waveBean
        // getWaveBean(wave).setCreatedNode(modelInstance.getRootNode());
    }

    /**
     * Get the wave bean and cast it.
     * 
     * @param wave the wave that hold the bean
     * 
     * @return the casted wave bean
     */
    @Override
    public DisplayModelWaveBean getWaveBean(final Wave wave) {
        return (DisplayModelWaveBean) wave.getWaveBean();
    }

}
