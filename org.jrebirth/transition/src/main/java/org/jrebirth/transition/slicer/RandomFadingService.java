package org.jrebirth.transition.slicer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;

/**
 * The class <strong>ImageSlicerService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class RandomFadingService extends ServiceBase {

    private final ObservableList<Node> nodes = FXCollections.observableArrayList();

    private Timeline timeline;

    /**
     * @return Returns the timeline.
     */
    public Timeline getTimeline() {
        return this.timeline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {

    }

    /**
     * TODO To complete.
     * 
     * @param slices
     */
    public void setNodes(final List<? extends Node> nodesToAdd) {
        for (final Node n : nodesToAdd) {
            this.nodes.add(n);
        }
    }

    public void doIt() {

        Collections.shuffle(this.nodes);

        final List<DoubleProperty> opacities = new ArrayList<>();

        // DoubleProperty dp;
        int idx = 0;

        final int nodesCount = this.nodes.size();
        final int groupCount = nodesCount / 2;

        for (int i = 0; i < groupCount; i++)
        {
            // dp = new SimpleDoubleProperty(1.0);
            for (int j = idx; j < 20 && j < this.nodes.size(); j++) {
                // nodes.get(j).opacityProperty().bind(dp);
                // nodes.get(j).scaleXProperty().bind(dp);
                opacities.add(/* dp */this.nodes.get(j).opacityProperty());
                idx++;

            }
            // opacities.add(/* dp */nodes.get(j).opacityProperty());
        }

        final List<KeyFrame> keyFrames = new ArrayList<>();

        List<KeyValue> keyValues;

        long d = 100;
        keyValues = new ArrayList<>();
        for (int i = 0; i < opacities.size(); i++) {

            keyFrames.add(new KeyFrame(Duration.millis(d), new EventHandler<ActionEvent>() {

                @Override
                public void handle(final ActionEvent arg0) {
                    System.out.println(arg0.toString());

                }
            }, new KeyValue(opacities.get(i), 1.0/* , Interpolator.EASE_IN */)));

            d += 1000;
            // }
        }

        this.timeline = TimelineBuilder.create()
                .keyFrames(keyFrames)
                .build();

    }

    private Duration getRandomDuration() {
        return Duration.millis(new Random().nextLong() % 1800 + 600);
    }

}
