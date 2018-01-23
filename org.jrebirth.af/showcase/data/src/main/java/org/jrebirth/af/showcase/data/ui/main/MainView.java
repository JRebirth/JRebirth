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

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.ui.DefaultView;

/**
 *
 * The class <strong>MainView</strong>.
 *
 * The main view of the JRebirth Analyzer application.
 *
 * @author Sébastien Bordes
 */
public final class MainView extends DefaultView<MainModel, BorderPane, MainController> {

    private final List<Button> buttonList = new ArrayList<>();

    /**
     * Default Constructor.
     *
     * @param model the view model
     *
     * @throws CoreException if build fails
     */
    public MainView(final MainModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().setPrefSize(800, 600);

        node().setLeft(createMenu());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.buttonList.stream().findFirst().ifPresent(button -> button.fire());
    }

    private Node createMenu() {
        final VBox box = new VBox();

        for (final ModuleModel mm : model().getModules()) {
            final Node n = createModuleButton(mm);
            VBox.setMargin(n, new Insets(4, 4, 4, 4));
            box.getChildren().add(n);
        }
        return box;
    }

    private Node createModuleButton(final ModuleModel mm) {
        final Button b = new Button(mm.moduleName());
        b.getStyleClass().add("menuButton");
        b.setPrefSize(100, 50);
        b.setOnAction(controller()::onButtonFired);
        b.setUserData(Key.create(mm.getClass()));
        this.buttonList.add(b);
        return b;
    }

}
