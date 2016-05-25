package org.jrebirth.af.component.ui.beans;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.ui.object.ModelConfig;

public class PartConfig<PC extends PartConfig<PC>> extends ModelConfig<PC> {

    public PartConfig(final Class<? extends Model> modelClass) {
        super(modelClass);
    }

}
