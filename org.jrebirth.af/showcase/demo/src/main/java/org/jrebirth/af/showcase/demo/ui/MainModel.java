/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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
package org.jrebirth.af.showcase.demo.ui;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.annotation.LinkComponent;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.component.ui.stack.StackModel;
import org.jrebirth.af.core.ui.DefaultModel;

/**
 * The class <strong>MainModel</strong>.
 *
 * @author Sébastien Bordes
 */
public final class MainModel extends DefaultModel<MainModel, MainView> {

    private final List<ModuleModel> modules = new ArrayList<>();

    @LinkComponent("DemoStack")
    private StackModel stackModel;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        for (final ModuleModel mm : getModels(ModuleModel.class)) {
            modules.add(mm);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        view().node().setCenter(stackModel.node());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do yet

    }

    /**
     * @return Returns the modules.
     */
    List<ModuleModel> getModules() {
        return modules;
    }

}
