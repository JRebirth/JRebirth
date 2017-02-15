package org.jrebirth.af.component.command.snapshot;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

import org.jrebirth.af.api.annotation.bean.Bean;
import org.jrebirth.af.api.wave.WaveBean;

@Bean("SnapshotWaveBean")
public abstract class AbstractSnapshotWaveBean implements WaveBean {

    protected SnapshotParameters parameters;

    protected WritableImage image;

    protected Node node;
}
