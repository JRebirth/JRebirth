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

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.ui.stack.StackWaves;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.ui.adapter.ActionAdapter;
import org.jrebirth.af.core.wave.Builders;

/**
 * The class <strong>MainController</strong>.
 *
 * @author Sébastien Bordes
 */
public final class MainController extends DefaultController<MainModel, MainView> implements ActionAdapter {

    /**
     * Default Constructor.
     *
     * @param view the view to control
     *
     * @throws CoreException if an error occurred while creating event handlers
     */
    public MainController(final MainView view) throws CoreException {
        super(view);
    }

    public void onButtonClicked(MouseEvent event) {
        final Button b = (Button) event.getSource();
        final UniqueKey<? extends Model> data = (UniqueKey<? extends Model>) b.getUserData();

        getModel().sendWave(StackWaves.SHOW_PAGE_MODEL,
                            Builders.waveData(StackWaves.PAGE_MODEL_KEY, data),
                            Builders.waveData(StackWaves.STACK_NAME, "DemoStack"));
    }

}
