package org.jrebirth.af.dialog;

import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.AbstractSingleCommand;
import org.jrebirth.af.core.exception.CommandException;

@RunInto(RunType.JAT)
public abstract class AbstractDialogCommand extends AbstractSingleCommand<DialogWB> implements DialogCommand {

    protected DialogWB bean;

    @Override
    protected void initCommand() {
        // Nothing to do yet

    }

    @Override
    protected void perform(Wave wave) throws CommandException {

        bean = getWaveBean(wave);

        switch (bean.getDialogType()) {

            case Info:
                openInfoDialog();
                break;
            case Warning:
                openWarningDialog();
                break;
            case Error:
                openErrorDialog();
                break;
        }

    }

    @Override
    protected void initInnerComponents() {
        // Nothing to do yet

    }

}
