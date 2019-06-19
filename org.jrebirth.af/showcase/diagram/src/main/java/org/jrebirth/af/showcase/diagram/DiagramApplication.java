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
package org.jrebirth.af.showcase.diagram;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.showcase.diagram.resource.DiagramStyles;
import org.jrebirth.af.showcase.diagram.ui.diagram.DiagramModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * The class <strong>UndoApplication</strong> is used to demonstrated Undo/Redo features.
 *
 * @author Sébastien Bordes
 */
public final class DiagramApplication extends DefaultApplication<StackPane> {

    /**
     * Application launcher.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(DiagramApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        return DiagramModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(Scene scene) {
        super.customizeScene(scene);
        addCSS(scene, DiagramStyles.MAIN);
    }
    
    

}
