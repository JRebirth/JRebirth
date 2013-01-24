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

import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.wave.Wave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>DetachModelCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class DetachModelCommand extends DefaultUICommand {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DetachModelCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        getWaveBean(wave).getModel().hideView();

        // final Pane parentNode = getWaveBean(wave).getParentNode();
        Node createdNode = getWaveBean(wave).getCreatedNode();
        if (createdNode == null) {
            createdNode = getWaveBean(wave).getModel().getRootNode();
        }

        if (createdNode == null) {
            LOGGER.warn("Impossible to dettach model {} because the node is null", getWaveBean(wave).getModelClass().getSimpleName());
        } else {
            if (getWaveBean(wave).getUniquePlaceHolder() != null) {
                getWaveBean(wave).getUniquePlaceHolder().set(null);
            }
            if (getWaveBean(wave).getChidrenPlaceHolder() != null) {
                getWaveBean(wave).getChidrenPlaceHolder().remove(createdNode);
            }
            if (getWaveBean(wave).getUniquePlaceHolder() == null && getWaveBean(wave).getChidrenPlaceHolder() == null) {
                LOGGER.warn("Impossible to detach model {}, no place holder found", getWaveBean(wave).getModelClass().getSimpleName());
            }
        }
    }

    /**
     * Get the wave bean and cast it.
     * 
     * @param wave the wave that hold the bean
     * 
     * @return he casted wave bean
     */
    @Override
    public DisplayModelWaveBean getWaveBean(final Wave wave) {
        return (DisplayModelWaveBean) wave.getWaveBean();
    }

}
