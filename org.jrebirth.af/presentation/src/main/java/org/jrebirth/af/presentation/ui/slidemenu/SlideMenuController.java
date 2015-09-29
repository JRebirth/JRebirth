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
package org.jrebirth.af.presentation.ui.slidemenu;

import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.ui.adapter.ActionAdapter;

/**
 * The class <strong>SlideMenuController</strong>.
 *
 * @author Sébastien Bordes
 */
public final class SlideMenuController extends DefaultController<SlideMenuModel, SlideMenuView> implements ActionAdapter {

    /**
     * Default Constructor.
     *
     * @param view the view to control
     *
     * @throws CoreException if an error occurred while creating event handlers
     */
    public SlideMenuController(final SlideMenuView view) throws CoreException {
        super(view);
    }

    /**
     * .
     *
     * @param mouseEvent
     */
    protected void onMouseReleased(final MouseEvent mouseEvent) {
    }

}
