package org.jrebirth.af.component.command.tab;

import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.beans.TabConfig;

public class TabWaveBean implements WaveBean {

    private String tabHolderKey;

    private TabConfig tabConfig;

    // private Model[] model;

    // private UniqueKey<? extends Model>[] modelKey;

    private Dockable[] tab;

    public static TabWaveBean create() {
        return new TabWaveBean();
    }

    /**
     * @return Returns the tabHolderKey.
     */
    public String tabHolderKey() {
        return this.tabHolderKey;
    }

    /**
     * @param tabHolderKey The tabHolderKey to set.
     */
    public TabWaveBean tabHolderKey(final String tabHolderKey) {
        this.tabHolderKey = tabHolderKey;
        return this;
    }

    // /**
    // * @return Returns the model.
    // */
    // public Model[] model() {
    // return this.model;
    // }
    //
    // /**
    // * @param model The tab to set.
    // */
    // public TabWaveBean model(final Model... model) {
    // this.model = model;
    // return this;
    // }
    //
    // /**
    // * @return Returns the modelKey.
    // */
    // public UniqueKey<? extends Model>[] modelKey() {
    // return this.modelKey;
    // }
    //
    // /**
    // * @param modelKey The modelKey to set.
    // */
    // @SafeVarargs
    // public final TabWaveBean modelKey(final UniqueKey<? extends Model>... modelKey) {
    // this.modelKey = modelKey;
    // return this;
    // }

    /**
     * @return Returns the tab.
     */
    public Dockable[] tab() {
        return this.tab;
    }

    /**
     * @param tab The tab to set.
     */
    public TabWaveBean tab(final Dockable... tab) {
        this.tab = tab;
        return this;
    }

    public TabConfig tabConfig() {
        return this.tabConfig;
    }

    public TabWaveBean tabConfig(final TabConfig tabConfig) {
        this.tabConfig = tabConfig;
        return this;
    }

}
