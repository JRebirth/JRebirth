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
package org.jrebirth.af.showcase.analyzer.ui.editor.ball;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.util.Duration;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.core.ui.DefaultView;
import org.jrebirth.af.showcase.analyzer.ui.editor.BallColors;
import org.jrebirth.af.showcase.analyzer.ui.editor.EditorWaves;

/**
 *
 * The class <strong>BallView</strong>.
 *
 * The view used to display a vent node.
 *
 * @author Sébastien Bordes
 */
public final class BallView extends DefaultView<BallModel, StackPane, BallController> {

    /** The ZoomIN ZoomOUT animation. */
    private ScaleTransition scaleTransition;

    /** The Show animation. */
    private ParallelTransition showTransition;

    /** The circle shape of the ball. */
    private Circle circle;

    /** The label used to dispaly the ball name. */
    private Label label;

    // private final DoubleProperty xTranslateProperty = new SimpleDoubleProperty();

    // private final DoubleProperty yTranslateProperty = new SimpleDoubleProperty();

    /**
     * Default Constructor.
     *
     * @param model the controls view model
     *
     * @throws CoreException if build fails
     */
    public BallView(final BallModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().setScaleX(0);
        node().setScaleY(0);

        this.circle = CircleBuilder.create()
                                   .radius(22)
                                   .fill(Color.ALICEBLUE)
                                   .stroke(Color.WHITE)
                                   .strokeWidth(4)
                                   .build();

        this.label = LabelBuilder.create()
                                 .textFill(Color.WHITE)
                                 // .effect(arg0)
                                 .build();

        node().getChildren().addAll(this.circle, this.label);

        this.showTransition = ParallelTransitionBuilder.create()
                                                       .children(
                                                                 ScaleTransitionBuilder.create()
                                                                                       .duration(Duration.millis(400))
                                                                                       .node(node())
                                                                                       .fromX(0.0)
                                                                                       .fromY(0.0)
                                                                                       .toX(1f)
                                                                                       .toY(1f)
                                                                                       .build(),
                                                                 TranslateTransitionBuilder.create()
                                                                                           .duration(Duration.millis(500))
                                                                                           .node(node())
                                                                                           .fromX(0.0)
                                                                                           .fromY(0.0)
                                                                                           .toX(getX())
                                                                                           .toY(getY())
                                                                                           .build())
                                                       .cycleCount(1)
                                                       .autoReverse(false)
                                                       .build();

        this.showTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent arg0) {
                model().sendWave(EditorWaves.RE_EVENT_PROCESSED/* , Builders.waveData(waveItem, value) */);

            }
        });

        this.scaleTransition = ScaleTransitionBuilder.create()
                                                     .duration(Duration.millis(600))
                                                     .node(node())
                                                     .fromX(1.1)
                                                     .fromY(1.1)
                                                     .toX(0.7)
                                                     .toY(0.7)
                                                     .cycleCount(Animation.INDEFINITE)
                                                     .autoReverse(true)
                                                     .build();

        this.circle.setEffect(InnerShadowBuilder.create()
                                                .color(Color.GREY)
                                                .blurType(BlurType.GAUSSIAN)
                                                .radius(2)
                                                .offsetX(1)
                                                .offsetY(1)
                                                .build());

        node().setEffect(DropShadowBuilder.create()
                                          // .input()
                                          .color(Color.BLACK)
                                          .blurType(BlurType.GAUSSIAN)
                                          .radius(4)
                                          .offsetX(3)
                                          .offsetY(3)
                                          .build());

    }

    /**
     * @return Returns the scaleTransition.
     */
    ScaleTransition getScaleTransition() {
        return this.scaleTransition;
    }

    /**
     * Define the ball style.
     *
     * @param eventType the type of event for this ball
     */
    public void setStyle(final JRebirthEventType eventType) {

        switch (eventType) {
            case CREATE_APPLICATION:
                this.circle.setFill(BallColors.APPLICATION.get());
                this.label.setText("App");
                break;
            case CREATE_NOTIFIER:
                this.circle.setFill(BallColors.NOTIFIER.get());
                this.label.setText("N");
                break;
            case CREATE_GLOBAL_FACADE:
                this.circle.setFill(BallColors.GLOBAL_FACADE.get());
                this.label.setText("GF");
                break;
            case CREATE_UI_FACADE:
                this.circle.setFill(BallColors.UI_FACADE.get());
                this.label.setText("UF");
                break;
            case CREATE_SERVICE_FACADE:
                this.circle.setFill(BallColors.SERVICE_FACADE.get());
                this.label.setText("SF");
                break;
            case CREATE_COMMAND_FACADE:
                this.circle.setFill(BallColors.COMMAND_FACADE.get());
                this.label.setText("CF");
                break;
            case CREATE_SERVICE:
                this.circle.setFill(BallColors.SERVICE.get());
                this.label.setText("S");
                break;
            case CREATE_MODEL:
                this.circle.setFill(BallColors.MODEL.get());
                this.label.setText("M");
                break;
            case CREATE_COMMAND:
                this.circle.setFill(BallColors.COMMAND.get());
                this.label.setText("C");
                break;
            case CREATE_VIEW:
                this.circle.setFill(BallColors.VIEW.get());
                this.label.setText("V");
                break;
            default:
                // Nothing to colorize
        }

    }

    /**
     * To complete.
     */
    public void resetScale() {
        ScaleTransitionBuilder.create()
                              .duration(Duration.millis(400))
                              .node(node())
                              .toX(1f)
                              .toY(1f)
                              .cycleCount(1)
                              .autoReverse(false)
                              .build()
                              .play();
    }

    /**
     * Show the ball by using a nice transition.
     */
    @Override
    public void start() {
        this.showTransition.play();
    }

    /**
     * Hide the ball by using a nice reverse transition..
     */
    @Override
    public void hide() {

        this.showTransition.setRate(-1);
        this.showTransition.play();

        // TranslateTransitionBuilder.create().duration(Duration.millis(500)).node(getRootNode()).byX(-50).byY(-40).build().play();
        //
        // final ScaleTransition st = new ScaleTransition(Duration.millis(400),
        // getRootNode());
        // st.setToX(0);
        // st.setToY(0);
        // st.setCycleCount(1);
        // st.setAutoReverse(false);
        // st.play();
    }

    /**
     * @return Returns the showTransition.
     */
    ParallelTransition getShowTransition() {
        return this.showTransition;
    }

    /**
     * To remove.
     */
    // public void prout() {
    // if (getModel().getEventModel().getEventType() == EventType.CREATE_GLOBAL_FACADE) {
    // final Circle c = new Circle(230);
    // c.setCenterX(getRootNode().getCenterX() + getRootNode().getTranslateX() + 70);
    // c.setCenterY(getRootNode().getCenterY() + getRootNode().getTranslateY());
    // c.setFill(Color.ORANGE);
    // c.setOpacity(0.05);
    // /*
    // * c.setStrokeLineCap(StrokeLineCap.ROUND); c.setStrokeDashOffset(100); c.setStrokeWidth(6); c.setStrokeType(StrokeType.CENTERED); c.setStroke(Color.BLUEVIOLET);
    // */
    // getModel().getModel(EditorModel.class).getView().getRootNode().getChildren().add(c);
    // }
    // }

    /**
     * Return the x coordinate.
     *
     * @return the x value
     */
    private double getX() {
        double res;
        switch (model().getEventModel().eventType()) {
            case CREATE_APPLICATION:
            case CREATE_COMMAND:
            case CREATE_COMMAND_FACADE:
                res = 0;
                break;
            case CREATE_GLOBAL_FACADE:
            case CREATE_NOTIFIER:
                res = 70;
                break;
            case CREATE_SERVICE_FACADE:
            case CREATE_SERVICE:
                res = -200 * Math.cos(Math.PI / 6);
                break;
            case CREATE_UI_FACADE:
            case CREATE_MODEL:
                res = 200 * Math.cos(Math.PI / 6);
                break;

            default:
                res = 50;
        }
        return res;
    }

    /**
     * Return the y coordinate.
     *
     * @return the y value
     */
    private double getY() {
        double res;
        switch (model().getEventModel().eventType()) {
            case CREATE_COMMAND_FACADE:
            case CREATE_COMMAND:
                res = -200 * Math.sin(Math.PI / 2);
                break;
            case CREATE_GLOBAL_FACADE:
            case CREATE_APPLICATION:
            case CREATE_NOTIFIER:
                res = 0;
                break;
            case CREATE_SERVICE_FACADE:
            case CREATE_UI_FACADE:
            case CREATE_MODEL:
            case CREATE_SERVICE:
                res = +200 * Math.sin(Math.PI / 6);
                break;
            default:
                res = 50;
        }
        return res;
    }

}
