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

import javafx.scene.Node;

import org.jrebirth.core.command.DefaultPoolCommand;
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

        final Class<? extends Model> first = getWaveBean(wave).getModelClass();

        // ShowModelCommandItem.modelClass;//.getAnnotation(DataClass.class);
        /*
         * if (first == null) { throw new CoreException("No First Model Class defined."); }
         */

        // Build the first root node
        final Node modelNode = getLocalFacade().getGlobalFacade().getUiFacade().retrieve(first).getView().getRootNode();
        getWaveBean(wave).setCreatedNode(modelNode);
    }

    /**
     * Get the wave bean and cast it.
     * 
     * @param wave the wave that hold the bean
     * 
     * @return he casted wave bean
     */
    public ShowModelWaveBean getWaveBean(final Wave wave) {
        return (ShowModelWaveBean) wave.getWaveBean();
    }

}
