package org.jrebirth.af.core.command.dataflow;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.internal.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.core.wave.JRebirthItems;

public class CreateDataCommand extends DefaultCommand {
    @Override
    protected void perform(final Wave wave) throws CommandException {
        wave.addDatas(Builders.waveData(JRebirthItems.integerItem, 21));
        wave.addDatas(Builders.waveData(JRebirthItems.stringItem, "half"));
    }
}