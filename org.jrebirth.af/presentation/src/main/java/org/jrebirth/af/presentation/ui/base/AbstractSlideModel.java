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
package org.jrebirth.af.presentation.ui.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.Animation;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.MotionBlurBuilder;
import javafx.util.Duration;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.presentation.model.AnimationType;
import org.jrebirth.af.presentation.model.Slide;
import org.jrebirth.af.presentation.model.SlideContent;

/**
 * The class <strong>AbstractSlideModel</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <M> the SlideModel class
 * @param <V> the SlideView class
 * @param <S> the SlideStep to use
 */
public abstract class AbstractSlideModel<M extends AbstractSlideModel<M, V, S>, V extends AbstractSlideView<?, ?, ?>, S extends SlideStep> extends DefaultModel<M, V> implements SlideModel<S> {

    /** The slide data object. */
    private Slide slide;

    /** The step position. */
    private AtomicInteger stepPosition;

    /** The list of slide step. */
    private List<S> stepList;

    /** The slide number. */
    private int slideNumber;

    /** The animation used to hide the slide. */
    private Animation hideAnimation;

    /** The animation used to show the slide. */
    private Animation showAnimation;

    public enum SlideFlow {
        forward, backward
    };

    /** The current slide flow. */
    private SlideFlow currentFlow = SlideFlow.forward;

    /**
     * @return Returns the slide.
     */
    public Slide getSlide() {
        if (this.slide == null) {
            this.slide = Slide.class.cast(getFirstKeyPart()/* .getValue() */);
        }
        return this.slide;
    }

    /**
     * @param slide The slide to set.
     */
    public void setSlide(final Slide slide) {
        this.slide = slide;
    }

    /**
     * @return Returns the currentFlow.
     */
    public SlideFlow getCurrentFlow() {
        return this.currentFlow;
    }

    /**
     * Return true if the slide flow is forward
     *
     * @return true if currentFlow == forward
     */
    public boolean isForwardFlow() {
        return this.currentFlow == SlideFlow.forward;
    }

    /**
     * @param currentFlow The currentFlow to set.
     */
    @Override
    public void setCurrentFlow(final SlideFlow currentFlow) {
        this.currentFlow = currentFlow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {
        super.bind();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Load the default slide step (if exists)
        if (getStepList().size() > 0) {

            initStepPosition();
            showSlideStep(getStepList().get(this.stepPosition.get()));
            // } else {
            // showSlideStep(getStepList().get(getStepCount() - 1));
            // }
        }
    }

    /**
     * TODO To complete.
     */
    private void initStepPosition() {
        if (this.stepPosition == null) {

            if (isForwardFlow()) {
                this.stepPosition = new AtomicInteger(0);
            } else {
                this.stepPosition = new AtomicInteger(getStepCount() - 1);
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        this.stepPosition = null;
    }

    /**
     * {@inheritDoc}
     */
    // @Override
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do generic
    }

    /**
     * @return Returns the slideNumber.
     */
    public int getSlideNumber() {
        return this.slideNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlideNumber(final int slideNumber) {
        this.slideNumber = slideNumber;
    }

    /**
     * @return Returns the stepPosition.
     */
    public int getStepPosition() {
        return this.stepPosition.get();
    }

    /**
     * Returns true if the slide has at least one step.
     *
     * @return true if the slide step list is not empty
     */
    public boolean hasStep() {
        return !getStepList().isEmpty();
    }

    protected int getStepCount() {
        return getStepList().size();
    }

    /**
     * @return Returns the stepList.
     */
    private List<S> getStepList() {
        if (this.stepList == null) {
            this.stepList = new ArrayList<>();
            this.stepList.addAll(Arrays.asList(initializeSlideStep()));

            for (final SlideContent sc : getSlide().getContent()) {
                if (!hasStep(sc)) {
                    if (sc.getIndex() != null) {
                        this.stepList.add(sc.getIndex().intValue(), (S) new DefaultSlideStep(sc.getName()));
                    } else {
                        this.stepList.add((S) new DefaultSlideStep(sc.getName()));
                    }
                }
            }
        }
        return this.stepList;
    }

    /**
     * Check if step defined in xml exist into current step list.
     *
     * @param slideContent the content to check
     *
     * @return true if the slide content is mapped to a programmatic slidestep
     */
    private boolean hasStep(final SlideContent slideContent) {
        boolean res = false;
        for (final S step : getStepList()) {
            if (step.name().equalsIgnoreCase(slideContent.getName())) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Initialize the SlideStep array.
     *
     * @return the list of slide step for this slide
     */
    protected abstract S[] initializeSlideStep();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nextStep() {
        initStepPosition();
        final boolean res = this.stepPosition.get() < getStepList().size() - 1;
        if (res && getView().isReadyForSlidesStepUpdate(false)) {

            setCurrentFlow(SlideFlow.forward);

            // Launch the next step
            showSlideStep(getStepList().get(this.stepPosition.incrementAndGet()));
        }
        // otherwise no more step return true to go to the next slide
        return !res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean previousStep() {
        initStepPosition();
        final boolean res = this.stepPosition.get() > 0;
        if (res && getView().isReadyForSlidesStepUpdate(true)) {

            setCurrentFlow(SlideFlow.backward);

            // Launch the previous step
            showSlideStep(getStepList().get(this.stepPosition.decrementAndGet()));
        }
        // otherwise no more step return true to go to the previous slide
        return !res;
    }

    /**
     * Return the default content or null.
     *
     * @return the default SlideContent
     */
    public SlideContent getDefaultContent() {
        SlideContent res = null;
        if (getSlide().getContent() != null && !getSlide().getContent().isEmpty()) {
            res = getSlide().getContent().get(0);
        }
        return res;
    }

    /**
     * Return the default content or null for the given step.
     *
     * @param slideStep the step to build
     *
     * @return the SlideContent
     */
    public SlideContent getContent(final SlideStep slideStep) {
        SlideContent res = null;
        if (getSlide().getContent() != null && !getSlide().getContent().isEmpty()) {
            for (final SlideContent sc : getSlide().getContent()) {
                if (sc.getName() != null && !sc.getName().isEmpty() && sc.getName().equals(slideStep.name())) {
                    res = sc;
                }
            }
        }
        return res == null ? getDefaultContent() : res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void showSlideStep(final S slideStep);

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getShowAnimation() {
        if (this.showAnimation == null) {
            this.showAnimation = buildAnimation(getSlide().getShowAnimation());
        }
        return this.showAnimation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getHideAnimation() {
        if (this.hideAnimation == null) {
            this.hideAnimation = buildAnimation(getSlide().getHideAnimation());
        }
        return this.hideAnimation;
    }

    /**
     * Build an animation.
     *
     * @param animationType the type of the animation to build
     *
     * @return the animation
     */
    private Animation buildAnimation(final AnimationType animationType) {
        Animation animation = null;
        switch (animationType) {

            case MOVE_TO_RIGHT:
                animation = buildHorizontalAnimation(0, 2000, 0, 0);
                break;
            case MOVE_TO_LEFT:
                animation = buildHorizontalAnimation(0, -2000, 0, 0);
                break;
            case MOVE_TO_TOP:
                animation = buildHorizontalAnimation(0, 0, 0, -1000);
                break;
            case MOVE_TO_BOTTOM:
                animation = buildHorizontalAnimation(0, 0, 0, 1000);
                break;

            case MOVE_FROM_RIGHT:
                animation = buildHorizontalAnimation(2000, 0, 0, 0);
                break;
            case MOVE_FROM_LEFT:
                animation = buildHorizontalAnimation(-2000, 0, 0, 0);
                break;
            case MOVE_FROM_TOP:
                animation = buildHorizontalAnimation(0, 0, -1000, 0);
                break;
            case MOVE_FROM_BOTTOM:
                animation = buildHorizontalAnimation(0, 0, 1000, 0);
                break;
            case FADE_IN:
                animation = FadeTransitionBuilder.create().node(getRootNode()).fromValue(0).toValue(1.0).duration(Duration.seconds(1)).build();
                break;
            case FADE_OUT:
                animation = FadeTransitionBuilder.create().node(getRootNode()).fromValue(1.0).toValue(0.0).duration(Duration.seconds(1)).build();
                break;

            case SCALE_FROM_MAX:
                animation = buildScaleAnimation(20.0, 1.0, true);
                break;
            case SCALE_FROM_MIN:
                animation = buildScaleAnimation(0.0, 1.0, true);
                break;
            case SCALE_TO_MAX:
                animation = buildScaleAnimation(1.0, 20.0, false);
                break;
            case SCALE_TO_MIN:
                animation = buildScaleAnimation(1.0, 0.0, false);
                break;

            case SLIDING_TOP_BOTTOM_PROGRESSIVE:
                animation = buildSliding();
                break;

            case TILE_IN:
                break;
            case TILE_OUT:
                break;
            case TILE_IN_60_K:
                break;
            case TILE_OUT_60_K:
                break;

            default:
                // animation = PauseTransitionBuilder.create().duration(Duration.seconds(1)).build();
        }
        return animation;
    }

    /**
     * Build a sliding animation.
     *
     * @return a sliding animation
     */
    private Animation buildSliding() {
        return new TranslateTransition();
    }

    /**
     * Build a scaling animation.
     *
     * @param from scale ratio used as from value
     * @param to scale ratio used as to value
     * @param show if true a fade in transition will be performed, otherwise fade out
     *
     * @return a scale animation
     */
    private Animation buildScaleAnimation(final double from, final double to, final boolean show) {

        return ParallelTransitionBuilder.create()
                                        .children(
                                                  ScaleTransitionBuilder.create()
                                                                        .node(getRootNode())
                                                                        .fromX(from)
                                                                        .toX(to)
                                                                        .fromY(from)
                                                                        .toY(to)
                                                                        .duration(Duration.seconds(1))
                                                                        .build(),

                                                  FadeTransitionBuilder.create()
                                                                       .node(getRootNode())
                                                                       .fromValue(show ? 0.0 : 1.0)
                                                                       .toValue(show ? 1.0 : 0.0)
                                                                       .duration(Duration.seconds(1))
                                                                       .build())
                                        .build();
    }

    /**
     * Build a scaling animation.
     *
     * @param fromX the x starting point coordinate
     * @param toX the x arrival point coordinate
     * @param fromY the y starting point coordinate
     * @param toY the y arrival point coordinate
     *
     * @return a translate animation
     */
    protected Animation buildHorizontalAnimation(final double fromX, final double toX, final double fromY, final double toY) {

        final double angle = findAngle(fromX, toX, fromY, toY);

        final MotionBlur mb = MotionBlurBuilder.create().angle(angle).build();
        getRootNode().setEffect(mb);

        return ParallelTransitionBuilder.create()
                                        .children(
                                                  TranslateTransitionBuilder.create()
                                                                            .node(getRootNode())
                                                                            .fromX(fromX)
                                                                            .toX(toX)
                                                                            .fromY(fromY)
                                                                            .toY(toY)
                                                                            .duration(Duration.seconds(1))
                                                                            .build(),

                                                  TimelineBuilder.create()
                                                                 .keyFrames(
                                                                            new KeyFrame(Duration.millis(0), new KeyValue(mb.radiusProperty(), 0)),
                                                                            new KeyFrame(Duration.millis(100), new KeyValue(mb.radiusProperty(), 50)),
                                                                            new KeyFrame(Duration.millis(500), new KeyValue(mb.radiusProperty(), 63)),
                                                                            new KeyFrame(Duration.millis(900), new KeyValue(mb.radiusProperty(), 50)),
                                                                            new KeyFrame(Duration.millis(1000), new KeyValue(mb.radiusProperty(), 0)))
                                                                 .build())
                                        .build();
    }

    /**
     * Return the right angle for the given coordinate.
     *
     * @param fromX the x starting point coordinate
     * @param toX the x arrival point coordinate
     * @param fromY the y starting point coordinate
     * @param toY the y arrival point coordinate
     *
     * @return the right angle
     */
    private double findAngle(final double fromX, final double fromY, final double toX, final double toY) {
        final double yDelta = toY - fromY;
        final double y = Math.sin(yDelta) * Math.cos(toX);
        final double x = Math.cos(fromX) * Math.sin(toX)
                - Math.sin(fromX) * Math.cos(toX) * Math.cos(yDelta);
        double angle = Math.toDegrees(Math.atan2(y, x));

        // Keep a positive angle
        while (angle < 0) {
            angle += 360;
        }
        return (float) angle % 360;
    }
}
