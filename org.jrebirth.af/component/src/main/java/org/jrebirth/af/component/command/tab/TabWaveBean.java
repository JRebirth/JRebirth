package org.jrebirth.af.component.command.tab;

import org.jrebirth.af.component.ui.beans.Tab;
import org.jrebirth.af.core.wave.WaveBean;

public class TabWaveBean implements WaveBean {

    private String tabHolderKey;

    private Tab[] tab;

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

    /**
     * @return Returns the tab.
     */
    public Tab[] tab() {
        return this.tab;
    }

    /**
     * @param tab The tab to set.
     */
    public TabWaveBean tab(final Tab... tab) {
        this.tab = tab;
        return this;
    }

}
