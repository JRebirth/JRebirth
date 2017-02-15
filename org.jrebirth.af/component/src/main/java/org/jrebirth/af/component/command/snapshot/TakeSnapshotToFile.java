package org.jrebirth.af.component.command.snapshot;

import java.util.Arrays;
import java.util.List;

import javafx.scene.image.WritableImage;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.multi.DefaultMultiCommand;

public class TakeSnapshotToFile extends DefaultMultiCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<UniqueKey<? extends Command>> defineSubCommand() {
        return Arrays.asList(
                             getCommandKey(SnapshotCommand.class),
                             getCommandKey(SaveImageCommand.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void afterEach(Wave wave, Wave childWave) {

        if (childWave.componentClass() == SnapshotCommand.class) {
            // Recopy the image to save
            final WritableImage image = wave.waveBean(SnapshotWaveBean.class).image();
            if (image != null) {
                wave.waveBean(SaveImageWaveBean.class).image(image);
            }
        }
    }

}
