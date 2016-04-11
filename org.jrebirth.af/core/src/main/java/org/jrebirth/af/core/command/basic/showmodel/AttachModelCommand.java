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

import javafx.scene.Node;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUIBeanCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>AttachModelCommand</strong>.
 *
 * @author Sébastien Bordes
 */
public class AttachModelCommand extends DefaultUIBeanCommand<DisplayModelWaveBean> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachModelCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) {

        // final Pane parentNode = getWaveBean(wave).getParentNode();
        final Node createdNode = waveBean(wave).showModel().node();

        if (createdNode == null) {
            LOGGER.warn("Impossible to attach model {} because the created node is null", waveBean(wave).showModelKey().toString());
        } else {
            if (waveBean(wave).uniquePlaceHolder() != null) {
                waveBean(wave).uniquePlaceHolder().set(createdNode);
            }
            if (waveBean(wave).childrenPlaceHolder() != null) {
                if (waveBean(wave).appendChild()) {
                    waveBean(wave).childrenPlaceHolder().add(createdNode);
                } else {
                    waveBean(wave).childrenPlaceHolder().add(0, createdNode);
                }
            }
            if (waveBean(wave).uniquePlaceHolder() == null && waveBean(wave).childrenPlaceHolder() == null) {
                LOGGER.warn("Impossible to attach model {}, no place holder found", waveBean(wave).showModelKey().toString());
            }

            // Try to give focus to the new node added (Could be managed by a boolean ??)
            // createdNode.requestFocus();

            // FIXME do it in the right way
            if (waveBean(wave).appendChild()) {
                waveBean(wave).showModel().doShowView(wave);
                // sendWave(Builders.wave().waveType(JRebirthWaves.SHOW_VIEW_WT).componentClass(getWaveBean(wave).showModel().getClass()));
            }

        }
    }

}
