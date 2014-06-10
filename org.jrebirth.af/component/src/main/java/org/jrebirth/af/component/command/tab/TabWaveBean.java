package org.jrebirth.af.component.command.tab;

import org.jrebirth.af.component.ui.beans.TabBB;
import org.jrebirth.af.core.wave.WaveBean;

public class TabWaveBean implements WaveBean {

    private String tabHolderKey;

    private TabBB[] tab;

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
    public TabBB[] tab() {
        return this.tab;
    }

    /**
     * @param tab The tab to set.
     */
    public TabWaveBean tab(final TabBB... tab) {
        this.tab = tab;
        return this;
    }

}
