package org.jrebirth.af.core.command.basic.multi;

import javafx.stage.Stage;

import org.jrebirth.af.core.command.DefaultUICommand;
import org.jrebirth.af.core.wave.Wave;

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
    protected void execute(Wave wave) {

        Stage s = new Stage();
        s.show();
        s.close();
        System.out.println("ui command done");
    }

}
