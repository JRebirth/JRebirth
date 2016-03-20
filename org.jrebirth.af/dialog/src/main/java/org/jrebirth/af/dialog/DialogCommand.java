package org.jrebirth.af.dialog;

import org.jrebirth.af.api.command.CommandBean;
import org.jrebirth.af.api.module.RegistrationPoint;

@RegistrationPoint
public interface DialogCommand extends CommandBean<DialogWB> {

    void openInfoDialog();

    void openWarningDialog();

    void openErrorDialog();

}
