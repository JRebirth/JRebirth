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
package org.jrebirth.af.showcase.analyzer.ui.editor.ball.facade.command;

import javafx.scene.shape.Circle;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultView;

/**
 * 
 * The class <strong>BallView</strong>.
 * 
 * The view used to display a vent node.
 * 
 * @author Sébastien Bordes
 */
public final class CommandFacadeBallView extends DefaultView<CommandFacadeBallModel, Circle, CommandFacadeBallController> {

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public CommandFacadeBallView(final CommandFacadeBallModel model) throws CoreException {
        super(model);
    }

}
