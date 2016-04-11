/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUIBeanCommand;
import org.jrebirth.af.core.exception.CommandException;

/**
 * The Class FadeInOutCommand is used to fade in, wait then fade out the given node.
 *
 * The node is retrieved from {@link DisplayModelWaveBean}.showModel root node.
 */
public class FadeInOutCommand extends DefaultUIBeanCommand<FadeInOutWaveBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {
        // Nothing to do yet
        super.initCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) throws CommandException {

        final Node node = getNode(wave);

        final FadeInOutWaveBean waveBean = waveBean(wave);

        final FadeTransition fadeIn = new FadeTransition();
        fadeIn.setAutoReverse(false);
        fadeIn.setCycleCount(1);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1.0);
        fadeIn.setDuration(waveBean.fadingInDuration());
        fadeIn.setNode(node);

        final PauseTransition pause = new PauseTransition();
        pause.setDuration(waveBean.showDuration());

        final FadeTransition fadeOut = new FadeTransition();
        fadeOut.setAutoReverse(false);
        fadeOut.setCycleCount(1);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0);
        fadeOut.setDuration(waveBean.fadingOutDuration());
        fadeOut.setNode(node);

        final SequentialTransition pt = new SequentialTransition();
        pt.getChildren().addAll(fadeIn, pause, fadeOut);

        pt.play();
    }

    /**
     * Gets the node.
     *
     * @param wave the wave
     * @return the node
     */
    private Node getNode(final Wave wave) {
        Node node = waveBean(wave).node();

        if (node == null && wave.hasWaveBean(DisplayModelWaveBean.class)) {
            node = wave.waveBean(DisplayModelWaveBean.class).showModel().node();
        }
        return node;
    }
}
