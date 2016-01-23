package org.jrebirth.af.component.command.dock;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.component.ui.beans.TabConfig;
import org.jrebirth.af.component.ui.dock.DockModel;
import org.jrebirth.af.component.ui.tab.TabModel;
import org.jrebirth.af.core.command.single.internal.DefaultBeanCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Builders;

public class AddDockCommand extends DefaultBeanCommand<DockWaveBean> {

    @Override
    protected void initCommand() {
    }

    @Override
    protected void perform(final Wave wave) throws CommandException {

        // Perform check

        // This command is running into JTP
        // So launch another wave that will processed by Model into JAT

        if (getWaveBean(wave).model() != null) {
            for (final Model model : getWaveBean(wave).model()) {
                sendWaveToTabModel(wave, model);
            }
        }

        if (getWaveBean(wave).modelKey() != null) {
            for (final UniqueKey<? extends Model> modelKey : getWaveBean(wave).modelKey()) {
                sendWaveToTabModel(wave, getModel(modelKey));
            }
        }

        if (getWaveBean(wave).tab() != null) {
            for (final TabConfig tab : getWaveBean(wave).tab()) {
                // sendWaveToTabModel(wave, getModel(tab.modelKey()));
            }
        }
    }

    private void sendWaveToTabModel(final Wave wave, final Model model) {

        sendWave(Builders.wave()
                         .waveType(TabModel.ADD)
                         .componentClass(TabModel.class)
                         .addDatas(Builders.waveData(DockModel.DOCK_KEY, getWaveBean(wave).dockHolderKey())
        // ,
        // Builders.waveData(DockModel.MODEL, model )
        ));

        // getModel(TabModel.class, getWaveBean(wave).dockConfig()).addTab(model, wave);
    }

}
