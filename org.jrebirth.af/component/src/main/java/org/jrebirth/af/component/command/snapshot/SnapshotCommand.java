package org.jrebirth.af.component.command.snapshot;

import javafx.scene.Scene;
import javafx.scene.image.WritableImage;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUIBeanCommand;
import org.jrebirth.af.core.exception.CommandException;

public class SnapshotCommand extends DefaultUIBeanCommand<SnapshotWaveBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(Wave wave) throws CommandException {

        final SnapshotWaveBean wb = waveBean(wave);

        WritableImage image = wb.image();

        if (wb.node() == null) {
            final Scene scene = localFacade().globalFacade().application().scene();

            image = scene.snapshot(image);

        } else {

            image = wb.node().snapshot(wb.parameters(), image);
        }

        wb.image(image);

    }

}
