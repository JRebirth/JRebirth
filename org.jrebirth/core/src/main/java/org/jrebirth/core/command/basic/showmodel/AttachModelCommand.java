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

import javafx.scene.Node;

import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.wave.Wave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>AttachModelCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class AttachModelCommand extends DefaultUICommand {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachModelCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        // final Pane parentNode = getWaveBean(wave).getParentNode();
        final Node createdNode = getWaveBean(wave).getCreatedNode();

        if (createdNode == null) {
            LOGGER.warn("Impossible to attach model {} because the created node is null", getWaveBean(wave).getModelClass().getSimpleName());
        } else {
            if (getWaveBean(wave).getUniquePlaceHolder() != null) {
                getWaveBean(wave).getUniquePlaceHolder().set(createdNode);
            }
            if (getWaveBean(wave).getChidrenPlaceHolder() != null) {
                if (getWaveBean(wave).isAppendChild()) {
                    getWaveBean(wave).getChidrenPlaceHolder().add(createdNode);
                } else {
                    getWaveBean(wave).getChidrenPlaceHolder().add(0, createdNode);
                }
            }
            if (getWaveBean(wave).getUniquePlaceHolder() == null && getWaveBean(wave).getChidrenPlaceHolder() == null) {
                LOGGER.warn("Impossible to attach model {}, no place holder found", getWaveBean(wave).getModelClass().getSimpleName());
            }

            // Try to give focus to the new node added (Could be managed by a boolean ??)
            // createdNode.requestFocus();

            getWaveBean(wave).getModel().showView();
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
