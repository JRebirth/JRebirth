package org.jrebirth.af.dialog.basic;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import org.jrebirth.af.dialog.AbstractDialogCommand;
import org.jrebirth.af.dialog.DialogCommand;
import org.jrebirth.af.processor.annotation.Register;
import org.jrebirth.af.processor.annotation.RegistrationPriority;

@Register(value = DialogCommand.class, priority = RegistrationPriority.Lowest)
public class BasicOpenDialogCommand extends AbstractDialogCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInfoDialog() {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(bean.getTitle());
        alert.setHeaderText(bean.getHeader());
        alert.setContentText(bean.getMessage());

        alert.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openWarningDialog() {
        final Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(bean.getTitle());
        alert.setHeaderText(bean.getHeader());
        alert.setContentText(bean.getMessage());

        alert.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openErrorDialog() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(bean.getTitle());
        alert.setHeaderText(bean.getHeader());
        alert.setContentText(bean.getMessage());

        alert.showAndWait();
    }
}
