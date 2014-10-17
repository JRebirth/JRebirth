package org.jrebirth.af.core.command.dataflow;

import org.jrebirth.af.core.command.impl.single.syncro.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.JRebirthItems;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveData;
import org.junit.Assert;

public class UpdateDataCommand extends DefaultCommand{
    @Override
    protected void perform(Wave wave) throws CommandException {
        
        System.out.println("From "+ wave.getData(JRebirthItems.integerItem));
        System.out.println("From " + wave.getData(JRebirthItems.stringItem));
        
        Assert.assertEquals(21, wave.get(JRebirthItems.integerItem).intValue());
        Assert.assertEquals("half", wave.get(JRebirthItems.stringItem));
        
        WaveData<Integer> wdInteger = wave.getData(JRebirthItems.integerItem);
        wdInteger.setValue(wdInteger.getValue()*2);
        
        wave.addDatas(WaveData.build(JRebirthItems.stringItem, "magic"));
    }
}