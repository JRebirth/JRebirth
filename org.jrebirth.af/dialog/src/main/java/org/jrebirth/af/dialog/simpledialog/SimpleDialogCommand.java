package org.jrebirth.af.dialog.simpledialog;

import org.jrebirth.af.dialog.AbstractDialogCommand;
import org.jrebirth.af.dialog.DialogCommand;
import org.jrebirth.af.processor.annotation.Register;
import org.jrebirth.af.processor.annotation.RegistrationPriority;

import com.github.daytron.simpledialogfx.data.DialogType;
import com.github.daytron.simpledialogfx.dialog.Dialog;

/**
 * 
 * The class <strong>SimpleDialogCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
@Register(value = DialogCommand.class, priority = RegistrationPriority.Low)
public class SimpleDialogCommand extends AbstractDialogCommand implements DialogCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInfoDialog() {
        final Dialog dialog = new Dialog(
                                         DialogType.INFORMATION,
                                         bean.getTitle(),
                                         bean.getHeader(),
                                         bean.getMessage()
                );
        dialog.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openWarningDialog() {
        final Dialog dialog = new Dialog(
                                         DialogType.WARNING,
                                         bean.getTitle(),
                                         bean.getHeader(),
                                         bean.getMessage()
                );
        dialog.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openErrorDialog() {
        final Dialog dialog = new Dialog(
                                         DialogType.ERROR,
                                         bean.getTitle(),
                                         bean.getHeader(),
                                         bean.getMessage()
                );
        dialog.showAndWait();
    }

}
