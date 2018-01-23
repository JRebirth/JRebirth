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
package org.jrebirth.af.component.ui.tab;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.core.ui.adapter.AbstractDefaultAdapter;
import org.jrebirth.af.core.ui.adapter.ActionAdapter;

import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;

/**
 * The Class TabActionAdapter.
 */
class TabbedPaneActionAdapter extends AbstractDefaultAdapter<TabbedPaneController> implements ActionAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(final ActionEvent actionEvent) {

        final ToggleButton b = (ToggleButton) actionEvent.getSource();

        final Dockable t = (Dockable) b.getUserData();

        controller().view().selectTab(t);

        // if(actionEvent.){
        // getController().getModel().removeTab(model, wave);
        // getController().getModel().getObject().tabs().remove(t);
        // }
    }

}
