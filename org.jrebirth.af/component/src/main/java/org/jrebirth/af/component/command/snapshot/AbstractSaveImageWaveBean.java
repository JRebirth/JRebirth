package org.jrebirth.af.component.command.snapshot;

import java.io.File;
import java.util.List;

import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import org.jrebirth.af.api.annotation.bean.Bean;
import org.jrebirth.af.api.wave.WaveBean;

@Bean("SaveImageWaveBean")
public abstract class AbstractSaveImageWaveBean implements WaveBean {

    protected Window popupOwner;

    protected File file;

    protected File folder;

    protected File directory;

    protected String fileName;

    protected List<ExtensionFilter> fileExtension;

    protected String chooserTitle;

    protected WritableImage image;

}
