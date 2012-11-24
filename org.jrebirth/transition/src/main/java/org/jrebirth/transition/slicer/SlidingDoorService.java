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
package org.jrebirth.transition.slicer;

import java.util.List;
import java.util.Random;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.Transition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;

/**
 * The class <strong>ImageSlicerService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class SlidingDoorService extends ServiceBase {

    /**
     * The Enum SLIDING_TYPE.
     */
    private enum SLIDING_TYPE {

        /** The U p_ down. */
        UP_DOWN,

        /** The DOW n_ up. */
        DOWN_UP,

        /** The FRO m_ left. */
        FROM_LEFT,

        /** The FRO m_ right. */
        FROM_RIGHT,

        /** The T o_ center. */
        TO_CENTER

    }

    /** The nodes. */
    private final ObservableList<Node> nodes = FXCollections.observableArrayList();

    /** The full transition. */
    private Transition fullTransition;

    /**
     * Gets the full transition.
     * 
     * @return Returns the fullTransition.
     */
    public Transition getFullTransition() {
        return this.fullTransition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Nothing to do yet
    }

    /**
     * TODO To complete.
     * 
     * @param nodesToAdd the new nodes
     */
    public void setNodes(final List<? extends Node> nodesToAdd) {
        for (final Node n : nodesToAdd) {
            this.nodes.add(n);
        }
    }

    /**
     * Do it.
     */
    public void doIt() {

        // timeline.

        // final List<Animation> fades = new ArrayList<>();
        //
        // int i = 1;
        // for (Node node : nodes) {
        // i *= -1;
        // fades.add(TranslateTransitionBuilder.create().node(node)
        // .fromY(1000 * i)
        // .toY(node.getLayoutY())
        // .byY(1000 * i)
        // .duration(Duration.millis(500))
        // .build()
        // );
        // Bounds b = node.getBoundsInLocal();
        // node.setClip(new Rectangle(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight()));
        //
        // }
        //
        // fullTransition = ParallelTransitionBuilder.create()
        // .children(fades).build();

        // for (int i = 0; i < 20; i++) {
        // p.getChildren().add(RectangleBuilder.create().x(i * 40).y(0).width(40).height(600).fill(Color.AZURE).build());
        // }

        this.fullTransition = ParallelTransitionBuilder.create().autoReverse(true).cycleCount(2).build();

        // int i = 0;
        // Collections.shuffle(nodes);
        for (final Node node : this.nodes) {
            node.setCache(true);
            node.setCacheHint(CacheHint.SPEED);

            ((ParallelTransition) this.fullTransition).getChildren().add(
                    TranslateTransitionBuilder.create().delay(getRandomDuration()).node(node).toY(1000).duration(Duration.millis(500)).interpolator(Interpolator.EASE_IN).build());
            // i++;
        }

    }

    /**
     * Gets the random duration.
     * 
     * @return the random duration
     */
    private Duration getRandomDuration() {
        return Duration.millis(new Random().nextLong() % 3000 + 200);
    }

}
