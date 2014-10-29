package org.jrebirth.af.core.command.dataflow;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.internal.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.JRebirthItems;
import org.junit.Assert;

public class DisplayDataCommand extends DefaultCommand {
    @Override
    protected void perform(final Wave wave) throws CommandException {

        System.out.println("Received " + wave.getData(JRebirthItems.integerItem));
        System.out.println("Received " + wave.getData(JRebirthItems.stringItem));

        Assert.assertEquals(42, wave.get(JRebirthItems.integerItem).intValue());
        Assert.assertEquals("magic", wave.get(JRebirthItems.stringItem));

    }
}