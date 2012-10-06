/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.analyzer.ui.editor.ball;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import org.jrebirth.analyzer.ui.editor.EditorModel;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;

/**
 * 
 * The class <strong>BallView</strong>.
 * 
 * The view used to display a vent node.
 * 
 * @author Sébastien Bordes
 */
public final class BallView extends DefaultView<BallModel, Circle, BallController> {

    /** The ZoomIN ZoomOUT animation. */
    private ScaleTransition scaleTransition;

    /** The Show animation. */
    private ParallelTransition showTransition;

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
    protected void customInitializeComponents() {

        // Define custom colors
        getRootNode().setRadius(20);
        getRootNode().setFill(Color.ALICEBLUE);
        getRootNode().setScaleX(0);
        getRootNode().setScaleY(0);

        this.showTransition = new ParallelTransition();
        this.showTransition.getChildren().add(
                ScaleTransitionBuilder.create().duration(Duration.millis(400)).node(getRootNode()).byX(1f).byY(1f).cycleCount(1).autoReverse(false).build());
        this.showTransition.getChildren().add(
                TranslateTransitionBuilder.create().duration(Duration.millis(500)).node(getRootNode()).byX(getX()).byY(getY()).build());

        this.scaleTransition = new ScaleTransition(Duration.millis(600), getRootNode());
        this.scaleTransition.setToX(1.1f);
        this.scaleTransition.setToY(1.1f);
        this.scaleTransition.setCycleCount(Animation.INDEFINITE);
        this.scaleTransition.setAutoReverse(true);

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
    public void setStyle(final EventType eventType) {

        switch (eventType) {
            case CREATE_APPLICATION:
                getRootNode().setFill(Color.AZURE);
                break;
            case CREATE_NOTIFIER:
                getRootNode().setFill(Color.BISQUE);
                break;
            case CREATE_GLOBAL_FACADE:
                getRootNode().setFill(Color.BLANCHEDALMOND);
                break;
            case CREATE_UI_FACADE:
                getRootNode().setFill(Color.BLUEVIOLET);
                break;
            case CREATE_SERVICE_FACADE:
                getRootNode().setFill(Color.CADETBLUE);
                break;
            case CREATE_COMMAND_FACADE:
                getRootNode().setFill(Color.CORNFLOWERBLUE);
                break;
            case CREATE_SERVICE:
                getRootNode().setFill(Color.CRIMSON);
                break;
            case CREATE_MODEL:
                getRootNode().setFill(Color.DARKGREY);
                break;
            case CREATE_COMMAND:
                getRootNode().setFill(Color.DARKRED);
                break;
            case CREATE_VIEW:
                getRootNode().setFill(Color.GAINSBORO);
                break;
            default:
                // Nothing to colorize
        }

    }

    /**
     * To complete.
     */
    public void resetScale() {
        final ScaleTransition st = new ScaleTransition(Duration.millis(400), getRootNode());
        st.setToX(1);
        st.setToY(1f);
        st.setCycleCount(1);
        st.setAutoReverse(false);
        st.play();
    }

    /**
     * Show the ball by using a nice transition.
     */
    @Override
    public void doStart() {
        this.showTransition.play();
    }

    /**
     * Hide the ball by using a nice reverse transition..
     */
    @Override
    public void doHide() {

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
    public void prout() {
        if (getModel().getEventModel().getEventType() == EventType.CREATE_GLOBAL_FACADE) {
            final Circle c = new Circle(230);
            c.setCenterX(getRootNode().getCenterX() + getRootNode().getTranslateX() + 70);
            c.setCenterY(getRootNode().getCenterY() + getRootNode().getTranslateY());
            c.setFill(Color.ORANGE);
            c.setOpacity(0.05);
            /*
             * c.setStrokeLineCap(StrokeLineCap.ROUND); c.setStrokeDashOffset(100); c.setStrokeWidth(6); c.setStrokeType(StrokeType.CENTERED); c.setStroke(Color.BLUEVIOLET);
             */
            getModel().getModel(EditorModel.class).getView().getRootNode().getChildren().add(c);
        }
    }

    /**
     * Return the x coordinate.
     * 
     * @return the x value
     */
    private double getX() {
        double res;
        switch (getModel().getEventModel().getEventType()) {
            case CREATE_APPLICATION:
            case CREATE_COMMAND_FACADE:
                res = 0;
                break;
            case CREATE_GLOBAL_FACADE:
            case CREATE_NOTIFIER:
                res = 70;
                break;
            case CREATE_SERVICE_FACADE:
                res = -200 * Math.cos(Math.PI / 6);
                break;
            case CREATE_UI_FACADE:
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
        switch (getModel().getEventModel().getEventType()) {
            case CREATE_COMMAND_FACADE:
                res = -200 * Math.sin(Math.PI / 2);
                break;
            case CREATE_GLOBAL_FACADE:
            case CREATE_APPLICATION:
            case CREATE_NOTIFIER:
                res = 0;
                break;
            case CREATE_SERVICE_FACADE:
            case CREATE_UI_FACADE:
                res = +200 * Math.sin(Math.PI / 6);
                break;
            default:
                res = 50;
        }
        return res;
    }

}
