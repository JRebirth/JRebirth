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

import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.PauseTransition;
import javafx.animation.PauseTransitionBuilder;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.scene.Node;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUIBeanCommand;
import org.jrebirth.af.core.exception.CommandException;

/**
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

        final FadeInOutWaveBean waveBean = getWaveBean(wave);

        final FadeTransition fadeIn = FadeTransitionBuilder.create()
                                                           .autoReverse(false)
                                                           .cycleCount(1)
                                                           .fromValue(0)
                                                           .toValue(1.0)
                                                           .duration(waveBean.fadingInDuration())
                                                           .node(node)
                                                           .build();

        final PauseTransition pause = PauseTransitionBuilder.create().duration(waveBean.showDuration()).build();

        final FadeTransition fadeOut = FadeTransitionBuilder.create()
                                                            .autoReverse(false)
                                                            .cycleCount(1)
                                                            .fromValue(1.0)
                                                            .toValue(0)
                                                            .duration(waveBean.fadingOutDuration())
                                                            .node(node)
                                                            .build();

        final SequentialTransition pt = SequentialTransitionBuilder.create()
                                                                   .children(fadeIn, pause, fadeOut)
                                                                   .build();

        pt.play();
    }

    private Node getNode(final Wave wave) {
        Node node = getWaveBean(wave).node();

        if (node == null && wave.hasWaveBean(DisplayModelWaveBean.class)) {
            node = wave.waveBean(DisplayModelWaveBean.class).showModel().getRootNode();
        }
        return node;
    }
}
