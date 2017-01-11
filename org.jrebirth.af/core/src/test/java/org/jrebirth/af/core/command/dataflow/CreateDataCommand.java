package org.jrebirth.af.core.command.dataflow;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.internal.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.JRebirthItems;
import org.jrebirth.af.core.wave.WBuilder;

public class CreateDataCommand extends DefaultCommand {
    @Override
    protected void perform(final Wave wave) throws CommandException {
        wave.addDatas(WBuilder.waveData(JRebirthItems.integerItem, 21));
        wave.addDatas(WBuilder.waveData(JRebirthItems.stringItem, "half"));
    }
}
