package org.jrebirth.af.core.command.basic.multi;

import javafx.stage.Stage;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUICommand;

public class UiCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {
        // Nothing to do yet
        super.initCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) {

        final Stage s = new Stage();
        s.show();
        s.close();
        System.out.println("ui command done");
    }

}
