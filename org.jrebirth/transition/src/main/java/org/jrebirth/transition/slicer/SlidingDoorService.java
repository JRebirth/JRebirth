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
import javafx.animation.PauseTransition;
import javafx.animation.PauseTransitionBuilder;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.Transition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.WaveTypeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ImageSlicerService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class SlidingDoorService extends ServiceBase {

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_SLICE_NODE = WaveTypeBase.build("SLICE_NODE", TransitionWaves.NODE);

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_NODE_SLICED = WaveTypeBase.build("NODE_SLICED", TransitionWaves.NODE);

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SlidingDoorService.class);

    /** The delay between each node transition property. */
    private final IntegerProperty nodeDelayProperty = new SimpleIntegerProperty(4);

    /** The delay between each node transition property. */
    private final ObjectProperty<SlidingType> slidingTypeProperty = new SimpleObjectProperty<>(SlidingType.FromBottomLeft);

    /** . */
    private final ObjectProperty<Duration> translateDurationProperty = new SimpleObjectProperty<>(Duration.millis(500));

    /**
     * The <code>RANDOM</code> field is used to build a random integer.
     */
    private static final Random RANDOM = new Random();

    /**
     * The Enum SLIDING_TYPE.
     */
    private enum SlidingType {

        FromBottomLeft,
        FromBottomRight,
        FromTopLeft,
        FromTopRight,

        FromLeftBottom,
        FromRightBottom,
        FromLeftTop,
        FromRightTop,

        FromHorizontalMiddle,
        FromVerticalMiddle
    }

    /** The nodes. */
    private final ObservableList<Node> nodes = FXCollections.observableArrayList();

    /** The full transition. */
    private Transition fullTransition;

    /**
     * Sets the node delay.
     * 
     * @param column the new node delay
     */
    public void setNodeDelay(final Integer column) {
        this.nodeDelayProperty.set(column);
    }

    /**
     * Gets the node delay.
     * 
     * @return the node delay
     */
    public Integer getNodeDelay() {
        return this.nodeDelayProperty.get();
    }

    /**
     * Node delay property.
     * 
     * @return the integer property
     */
    public IntegerProperty nodeDelayProperty() {
        return this.nodeDelayProperty;
    }

    public void setSlidingType(final SlidingType slidingType) {
        this.slidingTypeProperty.set(slidingType);
    }

    public SlidingType getSlidingType() {
        return this.slidingTypeProperty.get();
    }

    public ObjectProperty<SlidingType> slidingTypeProperty() {
        return this.slidingTypeProperty;
    }

    public void setTranslateDuration(final Duration translateDuration) {
        this.translateDurationProperty.set(translateDuration);
    }

    public Duration getTranslateDuration() {
        return this.translateDurationProperty.get();
    }

    public ObjectProperty<Duration> translateDurationProperty() {
        return this.translateDurationProperty;
    }

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
        this.nodes.addAll(nodesToAdd);
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

        final ParallelTransition parallel = ParallelTransitionBuilder.create()
                .build();

        // // int i = 0;
        // // Collections.shuffle(nodes);
        // for (final Node node : this.nodes) {
        // node.setCache(true);
        // node.setCacheHint(CacheHint.SPEED);
        //
        // ((ParallelTransition) this.fullTransition).getChildren().add(
        // TranslateTransitionBuilder.create().delay(getRandomDuration()).node(node).toY(1000).duration(Duration.millis(500)).interpolator(Interpolator.EASE_IN).build());
        // // i++;
        // }

        int i = 0;
        for (final Node node : this.nodes) {

            parallel.getChildren().add(
                    TranslateTransitionBuilder.create()
                            .delay(Duration.millis(i * getNodeDelay()))
                            .node(node)
                            // .fromY(0)
                            // .toY(1000)
                            .byY(1000)
                            .duration(getTranslateDuration())
                            .interpolator(Interpolator.EASE_IN)
                            .build()
                    );
            // parallel.getChildren().add(
            // TranslateTransitionBuilder.create()
            // .delay(getRandomDuration())
            // .node(node)
            // .toY(1000)
            // .duration(Duration.millis(500))
            // .interpolator(Interpolator.EASE_IN)
            // .build()
            // );
            i++;
        }
        final PauseTransition pt = PauseTransitionBuilder.create()
                .duration(Duration.seconds(1))
                .build();

        this.fullTransition = SequentialTransitionBuilder.create()
                .children(
                        parallel
                )
                .autoReverse(true)
                .cycleCount(10)
                .build();

    }

    /**
     * Gets the random duration.
     * 
     * @return the random duration
     */
    private Duration getRandomDuration() {
        return Duration.millis(RANDOM.nextLong() % 3000 + 1000);
    }

}
