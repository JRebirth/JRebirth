package org.jrebirth.af.showcase.fonticon.ui.main;

import java.util.Collections;

import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.beans.TabbedPaneConfig;
import org.jrebirth.af.component.ui.tab.TabbedPaneModel;
import org.jrebirth.af.core.command.basic.showmodel.FadeInOutWaveBean;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.iconfontbridge.fontawesome.FontAwesome;
import org.jrebirth.af.showcase.fonticon.ui.fonticon.FontIconModel;

@Register(value = ModuleModel.class)
public class MainModel extends DefaultSimpleModel<BorderPane> implements ModuleModel {

    private TabbedPaneModel tabModel;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initSimpleView() {
        // node().setCenter(tabModel.node());

        // if (!this.hasShown.getAndSet(true)) {
        final FadeInOutWaveBean fiowb = FadeInOutWaveBean.create().showDuration(Duration.millis(4000));
        final TabbedPaneConfig cfg = TabbedPaneConfig.create()
                                                     .id("FontIcon")
                                                     .tabs(
                                                           Dockable.create()
                                                                   .name("FontAwesome")
                                                                   .modelKey(Key.createMulti(FontIconModel.class, FontAwesome.class)));

        attachUi(TabbedPaneModel.class, WBuilder.buildUiData(node().centerProperty(), fiowb, Collections.singletonList(cfg)).toArray(new WaveData[0]));
        // }
    }

    @Override
    public String moduleName() {
        return "FontIcon";
    }

}
