package org.jrebirth.af.component.command.snapshot;

import java.io.File;

//import javafx.embed.swing.SwingFXUtils;
import javafx.stage.FileChooser;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUIBeanCommand;
import org.jrebirth.af.core.exception.CommandException;

public class SaveImageCommand extends DefaultUIBeanCommand<SaveImageWaveBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(Wave wave) throws CommandException {

        final SaveImageWaveBean wb = waveBean(wave);

        File file = wb.file();

        // No file is provided so we shall choose a new one
        if (file == null) {
            final FileChooser fc = new FileChooser();
            fc.setTitle(wb.chooserTitle());

            fc.setInitialDirectory(wb.directory() != null ? wb.directory() : new File("."));
            fc.setInitialFileName(wb.fileName() != null ? wb.fileName() : "image");

            fc.getExtensionFilters().addAll(wb.fileExtension());

            file = fc.showSaveDialog(wb.popupOwner() != null ? wb.popupOwner() : localFacade().globalFacade().application().stage());

        }

        // FIXME
        // if (file != null) {
        // final BufferedImage bi = SwingFXUtils.fromFXImage(wb.image(), null);
        // try {
        // ImageIO.write(bi, "png", file);
        // } catch (final IOException e) {
        // throw new CoreRuntimeException(e);
        // }
        // }

    }

}
