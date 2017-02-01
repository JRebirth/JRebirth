package org.jrebirth.af.component.ui.beans;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.ui.object.ModelConfig;

public class PartConfig<M extends Model, PC extends PartConfig<M, PC>> extends ModelConfig<M, PC> {

    public PartConfig(final Class<M> modelClass) {
        super(modelClass);
    }

}
