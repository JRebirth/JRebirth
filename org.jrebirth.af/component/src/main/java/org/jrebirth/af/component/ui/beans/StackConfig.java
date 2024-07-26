/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2024
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.component.ui.beans;

import org.jrebirth.af.component.ui.stack.StackItem;
import org.jrebirth.af.component.ui.stack.StackModel;
import org.jrebirth.af.core.ui.object.ModelConfig;

public class StackConfig extends ModelConfig<StackModel, StackConfig> {

    private String stackName;

    private Class<? extends StackItem> stackItemClass;

    public StackConfig() {
        super(StackModel.class);
    }

    public StackConfig(Class<StackModel> modelClass) {
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

    public Class<? extends StackItem> stackItemClass() {
        return stackItemClass;
    }

    public StackConfig stackItemClass(Class<? extends StackItem> pageEnumClass) {
        this.stackItemClass = pageEnumClass;
        return this;
    }

}
