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

import javafx.animation.FadeTransitionBuilder;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.AbstractSingleCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FadeTransitionCommand.
 *
 * Add a fading transition to switch 2 model node hold into a stackpane (or equivalent).
 */
@RunInto(RunType.JAT)
public class FadeTransitionCommand extends AbstractSingleCommand<DisplayModelWaveBean> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FadeTransitionCommand.class);

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
    protected void perform(final Wave wave) {

        // The old node is the one that exists into the parent container (or null if none)
        Node oldNode = getWaveBean(wave).hideModel() == null ? null : getWaveBean(wave).hideModel().getRootNode();

        if (oldNode == null) {
            final ObservableList<Node> parentContainer = getWaveBean(wave).childrenPlaceHolder();
            oldNode = parentContainer.size() > 1 ? parentContainer.get(getWaveBean(wave).childrenPlaceHolder().size() - 1) : null;
        }

        // The new node is the one create by PrepareModelCommand
        final Node newNode = getWaveBean(wave).showModel() == null ? null : getWaveBean(wave).showModel().getRootNode();

        if (oldNode != null || newNode != null) {
            final ParallelTransition animation = ParallelTransitionBuilder.create()
                                                                          .build();

            if (oldNode != null) {
                animation.getChildren().add(
                                            FadeTransitionBuilder.create()
                                                                 .duration(Duration.millis(600))
                                                                 .node(oldNode)
                                                                 .fromValue(1.0)
                                                                 .toValue(0.0)
                                                                 .build());
            }
            if (newNode != null) {
                animation.getChildren().add(
                                            FadeTransitionBuilder.create()
                                                                 .duration(Duration.millis(600))
                                                                 .node(newNode)
                                                                 .fromValue(0.0)
                                                                 .toValue(1.0)
                                                                 .build());
            }

            final Node oldNodeLink = oldNode;

            // When animation is finished remove the hidden node from the stack to let only one node at the same time
            animation.setOnFinished(new EventHandler<ActionEvent>() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void handle(final ActionEvent arg0) {
                    if (oldNodeLink != null) {
                        // remove the old nod from the stack to hide it
                        getWaveBean(wave).childrenPlaceHolder().remove(oldNodeLink);

                        LOGGER.info("Remove " + oldNodeLink.toString() + " from stack container");
                    }
                    // FIXME do it in the right way
                    getWaveBean(wave).showModel().doShowView(wave);
                }
            });
            animation.playFromStart();
        }
    }

    @Override
    protected void initInnerComponents() {
    }

}
