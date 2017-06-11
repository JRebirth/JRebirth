package org.jrebirth.af.component.command.tab;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.tab.TabbedPaneModel;
import org.jrebirth.af.core.command.single.ui.DefaultUIBeanCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.WBuilder;

public class AddTabCommand extends DefaultUIBeanCommand<TabWaveBean> {

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

        if (waveBean(wave).tab() != null) {
            for (final Dockable tab : waveBean(wave).tab()) {
                sendWaveToTabModel(wave, tab);
            }
        }
    }

    private void sendWaveToTabModel(final Wave wave, final Dockable tab) {
        
    	System.out.println("AddtabWave " + tab.name());
    	sendWave(WBuilder.wave()
                         .waveType(TabbedPaneModel.ADD)
                         .componentClass(TabbedPaneModel.class)
                         .addDatas(WBuilder.waveData(TabbedPaneModel.TAB_KEY, waveBean(wave).tabHolderKey()),
                                   WBuilder.waveData(TabbedPaneModel.TAB, tab)));

        // getModel(TabModel.class, waveBean(wave).tabConfig()).addTab(tab, wave);
    }

}
