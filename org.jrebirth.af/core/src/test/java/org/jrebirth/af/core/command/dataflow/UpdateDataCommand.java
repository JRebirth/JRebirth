package org.jrebirth.af.core.command.dataflow;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.core.command.single.internal.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.core.wave.JRebirthItems;

import org.junit.Assert;

public class UpdateDataCommand extends DefaultCommand {
    @Override
    protected void perform(final Wave wave) throws CommandException {

        System.out.println("From " + wave.getData(JRebirthItems.integerItem));
        System.out.println("From " + wave.getData(JRebirthItems.stringItem));

        Assert.assertEquals(21, wave.get(JRebirthItems.integerItem).intValue());
        Assert.assertEquals("half", wave.get(JRebirthItems.stringItem));

        final WaveData<Integer> wdInteger = wave.getData(JRebirthItems.integerItem);
        wdInteger.value(wdInteger.value() * 2);

        wave.addDatas(WBuilder.waveData(JRebirthItems.stringItem, "magic"));
    }
}
