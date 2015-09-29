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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.ui.annotation.OnScroll;
import org.jrebirth.af.api.ui.annotation.RootNodeId;
import org.jrebirth.af.api.ui.annotation.type.Scroll;
import org.jrebirth.af.core.ui.DefaultView;
import org.jrebirth.presentation.model.Slide;

/**
 *
 * The class <strong>SlideMenuView</strong>.
 *
 * @author Sébastien Bordes
 */
@RootNodeId("SlideMenu")
@OnScroll({ Scroll.Started, Scroll.Finished })
public final class SlideMenuView extends DefaultView<SlideMenuModel, ListView<Slide>, SlideMenuController> {

    /**
     * Default Constructor.
     *
     * @param model the view model
     *
     * @throws CoreException if build fails
     */
    public SlideMenuView(final SlideMenuModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        getRootNode().setPrefSize(300, 600);

        final ObservableList<Slide> items = FXCollections.observableArrayList(getModel().getSlides());
        getRootNode().setItems(items);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Nothing to do yet
        super.hide();
    }

}
