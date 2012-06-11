package org.jrebirth.presentation.ui.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.MotionBlurBuilder;
import javafx.util.Duration;

import org.jrebirth.core.link.Wave;
import org.jrebirth.core.ui.AbstractModel;
import org.jrebirth.presentation.model.AnimationType;
import org.jrebirth.presentation.model.Slide;
import org.jrebirth.presentation.model.SlideContent;

/**
 * The class <strong>AbstractSlideModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the SlideModel class
 * @param <V> the SlideView class
 * @param <S> the SlideStep to use
 */
public abstract class AbstractSlideModel<M extends AbstractSlideModel<M, V, S>, V extends AbstractSlideView<?, ?, ?>, S extends SlideStep> extends AbstractModel<M, V> implements SlideModel<S> {

    /** The slide data object. */
    private Slide slide;

    /** The step position. */
    private int stepPosition;

    /** The list of slide step. */
    private List<S> stepList;

    /** The slide number. */
    private int slideNumber;

    private Animation hideAnimation;

    private Animation showAnimation;

    /**
     * @return Returns the slide.
     */
    public Slide getSlide() {
        if (this.slide == null) {
            this.slide = Slide.class.cast(getModelObject()/* .getValue() */);
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
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {

        // Load the default slide step (if exists)
        if (getStepList().size() > 0) {
            showSlideStep(getStepList().get(this.stepPosition));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Nothing to do generic
        getClass();
    }

    /**
     * {@inheritDoc}
     */
    // @Override
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do generic
        getClass();
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
        return this.stepPosition;
    }

    public boolean hasStep() {
        return !getStepList().isEmpty();
    }

    /**
     * @return Returns the stepList.
     */
    private List<S> getStepList() {
        if (this.stepList == null) {
            this.stepList = new ArrayList<>();
            this.stepList.addAll(Arrays.asList(initializeSlideStep()));
        }
        return this.stepList;
    }

    /**
     * Initialize the SlideStep array.
     */
    protected abstract S[] initializeSlideStep();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nextStep() {
        final boolean res = this.stepPosition < this.stepList.size() - 1;
        if (res && !getView().isSlideLocked()) {
            this.stepPosition++;
            // Launch the next step
            showSlideStep(getStepList().get(this.stepPosition));
        }
        // otherwise no more step return true to go to the next slide
        return !res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean previousStep() {
        final boolean res = this.stepPosition > 0;
        if (res && !getView().isSlideLocked()) {
            this.stepPosition--;
            // Launch the previous step
            showSlideStep(getStepList().get(this.stepPosition));
        }
        // otherwise no more step return true to go to the previous slide
        return !res;
    }

    /**
     * Return the default content or null.
     * 
     * @return the SlideContent
     */
    public SlideContent getDefaultContent() {
        if (getSlide().getContent() != null && !getSlide().getContent().isEmpty()) {
            return getSlide().getContent().get(0);
        }
        return null;
    }

    /**
     * Return the default content or null.
     * 
     * @return the SlideContent
     */
    public SlideContent getContent(final SlideStep slideStep) {
        if (getSlide().getContent() != null && !getSlide().getContent().isEmpty()) {
            for (final SlideContent sc : getSlide().getContent()) {
                if (sc.getName() != null && !sc.getName().isEmpty() && sc.getName().equals(slideStep.toString())) {
                    return sc;
                }
            }
        }
        return getDefaultContent();
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
     * TODO To complete.
     * 
     * @param hideAnimation
     * @return
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
     * TODO To complete.
     * 
     * @return
     */
    private Animation buildSliding() {

        return null;
    }

    /**
     * TODO To complete.
     * 
     * @param d
     * @param e
     * @return
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
                                .build()
                )
                .build();
    }

    /**
     * TODO To complete.
     * 
     * @return
     */
    protected Animation buildHorizontalAnimation(final double fromX, final double toX, final double fromY, final double toY) {

        final MotionBlur mb = MotionBlurBuilder.create().angle(180).build();
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
                                        new KeyFrame(Duration.millis(1000), new KeyValue(mb.radiusProperty(), 0))
                                )
                                .build()
                )
                .build();
    }

}
