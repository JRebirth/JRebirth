package org.jrebirth.af.core.command.dataflow;

import org.jrebirth.af.core.command.impl.single.syncro.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.JRebirthItems;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveData;

public class CreateDataCommand extends DefaultCommand{
    @Override
    protected void perform(Wave wave) throws CommandException {
        wave.addDatas(WaveData.build(JRebirthItems.integerItem, 21));
        wave.addDatas(WaveData.build(JRebirthItems.stringItem, "half"));
    }
}