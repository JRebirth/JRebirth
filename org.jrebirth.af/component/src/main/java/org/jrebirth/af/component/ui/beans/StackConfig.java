package org.jrebirth.af.component.ui.beans;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.ui.stack.PageEnum;
import org.jrebirth.af.component.ui.stack.StackModel;
import org.jrebirth.af.core.ui.object.ModelConfig;

public class StackConfig extends ModelConfig<StackConfig> {

    private String stackName;

    private Class<? extends PageEnum> pageEnumClass;

    public StackConfig() {
        super(StackModel.class);
    }

    public StackConfig(Class<? extends Model> modelClass) {
        super(modelClass);
    }

    public static StackConfig create() {
        return new StackConfig(StackModel.class);
    }

    public String stackName() {
        return stackName;
    }

    public StackConfig stackName(String stackName) {
        this.stackName = stackName;
        return this;
    }

    public Class<? extends PageEnum> pageEnumClass() {
        return pageEnumClass;
    }

    public StackConfig pageEnumClass(Class<? extends PageEnum> pageEnumClass) {
        this.pageEnumClass = pageEnumClass;
        return this;
    }

}
