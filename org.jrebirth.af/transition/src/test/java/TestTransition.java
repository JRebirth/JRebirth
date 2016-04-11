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
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jrebirth.af.api.resource.ResourceItem;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.application.DefaultApplication;

import ui.TransitionModel;

/**
 * The class <strong>JRebirthAnalyzer</strong>.
 * 
 * The application that demonstrates how to use JRebirth Framework. It also allow to show all JRebirth events.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class TestTransition extends DefaultApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(TestTransition.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        return TransitionModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "Test Transition";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        stage.setFullScreen(false);
        stage.setWidth(800);
        stage.setHeight(600);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> preBootWaveList() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> postBootWaveList() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends ResourceItem<?, ?, ?>> getResourceToPreload() {
        return Collections.emptyList();
    }
}
