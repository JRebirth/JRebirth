package org.jrebirth.af.component.command.dock;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.component.ui.beans.DockPaneConfig;
import org.jrebirth.af.component.ui.beans.PartConfig;

public class DockWaveBean implements WaveBean {

    private String dockHolderKey;

    private DockPaneConfig dockConfig;

    private Model[] model;

    private UniqueKey<? extends Model>[] modelKey;

    private PartConfig<?, ?>[] parts;

    public static DockWaveBean create() {
        return new DockWaveBean();
    }

    /**
     * @return Returns the dockHolderKey.
     */
    public String dockHolderKey() {
        return this.dockHolderKey;
    }

    /**
     * @param dockHolderKey The dockHolderKey to set.
     */
    public DockWaveBean dockHolderKey(final String dockHolderKey) {
        this.dockHolderKey = dockHolderKey;
        return this;
    }

    /**
     * @return Returns the model.
     */
    public Model[] model() {
        return this.model;
    }

    /**
     * @param model The tab to set.
     */
    public DockWaveBean model(final Model... model) {
        this.model = model;
        return this;
    }

    /**
     * @return Returns the modelKey.
     */
    public UniqueKey<? extends Model>[] modelKey() {
        return this.modelKey;
    }

    /**
     * @param modelKey The modelKey to set.
     */
    @SafeVarargs
    public final DockWaveBean modelKey(final UniqueKey<? extends Model>... modelKey) {
        this.modelKey = modelKey;
        return this;
    }

    /**
     * @return Returns the parts.
     */
    public PartConfig<?, ?>[] parts() {
        return this.parts;
    }

    /**
     * @param parts The parts to set.
     */
    public DockWaveBean parts(final PartConfig<?, ?>... parts) {
        this.parts = parts;
        return this;
    }

    public DockPaneConfig dockConfig() {
        return this.dockConfig;
    }

    public DockWaveBean dockConfig(final DockPaneConfig dockConfig) {
        this.dockConfig = dockConfig;
        return this;
    }

}
