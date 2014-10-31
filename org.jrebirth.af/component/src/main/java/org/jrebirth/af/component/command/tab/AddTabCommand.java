package org.jrebirth.af.component.command.tab;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.tab.TabModel;
import org.jrebirth.af.core.command.single.internal.DefaultBeanCommand;
import org.jrebirth.af.core.exception.CommandException;

public class AddTabCommand extends DefaultBeanCommand<TabWaveBean> {

    @Override
    protected void initCommand() {
    }

    @Override
    protected void perform(final Wave wave) throws CommandException {

        // Perform check

        // This command is running into JTP
        // So launch another wave that will processed by Model into JAT

        // if(getWaveBean(wave).model() !=null){
        // for(Model model: getWaveBean(wave).model()){
        // sendWaveToTabModel(wave, model);
        // }
        // }

        // if(getWaveBean(wave).modelKey()!=null){
        // for(UniqueKey<? extends Model> modelKey: getWaveBean(wave).modelKey()){
        // sendWaveToTabModel(wave, getModel(modelKey));
        // }
        // }

        if (getWaveBean(wave).tab() != null) {
            for (final Dockable tab : getWaveBean(wave).tab()) {
                sendWaveToTabModel(wave, tab);
            }
        }
    }

    private void sendWaveToTabModel(final Wave wave, final Dockable tab) {
        // sendWave(Builders.wave()
        // .waveType(TabModel.ADD)
        // .componentClass(TabModel.class)
        // .addDatas( WaveData.build(TabModel.TAB_KEY, getWaveBean(wave).tabHolderKey()),
        // WaveData.build(TabModel.TAB, tab )
        // )
        // );
        //
        getModel(TabModel.class, getWaveBean(wave).tabConfig()).addTab(tab, wave);
    }

}
