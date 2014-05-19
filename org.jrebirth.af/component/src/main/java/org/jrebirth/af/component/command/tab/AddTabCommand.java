package org.jrebirth.af.component.command.tab;

import org.jrebirth.af.component.ui.tab.TabModel;
import org.jrebirth.af.core.command.DefaultBeanCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBase;
import org.jrebirth.af.core.wave.WaveData;

public class AddTabCommand extends DefaultBeanCommand<TabWaveBean> {

    @Override
    protected void initCommand() {
    }

    @Override
    protected void perform(final Wave wave) throws CommandException {

        // Perform check

        // This command is running into JTP
        // So launch another wave that will processed by Model into JAT
        sendWave(WaveBase.create()
                .waveType(TabModel.ADD)
                .componentClass(TabModel.class)
                .addDatas(WaveData.build(TabModel.TAB_KEY, getWaveBean(wave).tabHolderKey())));

    }

}
