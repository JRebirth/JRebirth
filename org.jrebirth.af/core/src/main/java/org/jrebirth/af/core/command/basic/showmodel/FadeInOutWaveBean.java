package org.jrebirth.af.core.command.basic.showmodel;

import javafx.scene.Node;
import javafx.util.Duration;

import org.jrebirth.af.api.wave.WaveBean;

public class FadeInOutWaveBean implements WaveBean {

    private Node node;
    private Duration showDuration = Duration.millis(3000);
    private Duration fadingInDuration = Duration.millis(1000);
    private Duration fadingOutDuration = Duration.millis(1000);

    /**
     * Build a new instance of {@link FadeInOutWaveBean}.
     *
     * @return a fresh instance
     */
    public static FadeInOutWaveBean create() {
        return new FadeInOutWaveBean();
    }

    /**
     * Hide the default constructor but allow subclassing.
     */
    public FadeInOutWaveBean() {
        super();
    }

    public Node node() {
        return this.node;
    }

    public FadeInOutWaveBean node(final Node node) {
        this.node = node;
        return this;
    }

    public Duration showDuration() {
        return this.showDuration;
    }

    public FadeInOutWaveBean showDuration(final Duration showDuration) {
        this.showDuration = showDuration;
        return this;
    }

    public Duration fadingInDuration() {
        return this.fadingInDuration;
    }

    public FadeInOutWaveBean fadingInDuration(final Duration fadingInDuration) {
        this.fadingInDuration = fadingInDuration;
        return this;
    }

    public Duration fadingOutDuration() {
        return this.fadingOutDuration;
    }

    public FadeInOutWaveBean fadingOutDuration(final Duration fadingOutDuration) {
        this.fadingOutDuration = fadingOutDuration;
        return this;
    }
}
