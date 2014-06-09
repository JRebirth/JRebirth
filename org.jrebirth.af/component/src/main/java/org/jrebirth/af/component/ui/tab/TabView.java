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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.jrebirth.af.component.ui.beans.TabBB;
import org.jrebirth.af.component.ui.beans.TabOrientation;
import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.DefaultView;
import org.jrebirth.af.core.ui.annotation.RootNodeClass;

/**
 * The Class TabView only creates a TabPane like component that will handle Model Components.
 *
 * No Controller is needed.
 *
 * @author Sébastien Bordes
 */
@RootNodeClass("TabPanel")
public class TabView extends DefaultView<TabModel, BorderPane, TabController> {

    /** The Constant LOGGER. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(TabView.class);

    /** The stack pane. */
    private StackPane stackPane;

    /** The button by tab. */
    private final Map<String, Button> buttonByTab = new HashMap<>();

    /** The marker. */
    private Rectangle marker;

    /** The box. */
    private Pane box;

    /** The hover node. */
    private Node hoverNode;

    /**
     * Instantiates a new Dock View.
     *
     * @param model the model
     *
     * @throws CoreException the core exception
     */
    public TabView(final TabModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        switch (getModel().getObject().orientation()) {
            case top:
                getRootNode().setTop(buildButtonBar(true));
                break;
            case bottom:
                getRootNode().setBottom(buildButtonBar(true));
                break;
            case left:
                getRootNode().setLeft(buildButtonBar(false));
                break;
            case right:
                getRootNode().setRight(buildButtonBar(false));
                break;
        }

        this.stackPane = new StackPane();

        getRootNode().setCenter(this.stackPane);

    }

    /**
     * Builds the button bar.
     *
     * @param isHorizontal the is horizontal
     * @return the pane
     */
    private Pane buildButtonBar(final boolean isHorizontal) {

        this.box = isHorizontal ? new HBox() : new VBox();

        return this.box;
    }

    /**
     * Adds the tab.
     *
     * @param idx the idx
     * @param tab the tab
     */
    public void addTab(int idx, final TabBB tab) {

        final Button b = new Button(tab.name());
        b.setUserData(tab);

        this.buttonByTab.put(tab.name(), b);

        selectTab(tab);

        getController().initTabEventHandler(b);

        if (idx < 0) {
            idx = this.box.getChildren().size();
        }

        this.box.getChildren().add(idx, b);

    }

    public void removeTab(final List<TabBB> tabs) {
        for (final TabBB tab : tabs) {
            final Button b = this.buttonByTab.get(tab.name());
            this.box.getChildren().remove(b);
        }
    }

    /**
     * Select tab.
     *
     * @param t the t
     */
    void selectTab(final TabBB t) {
        this.stackPane.getChildren().clear();
        this.stackPane.getChildren().add(getModel().getLocalFacade().retrieve(t.modelKey()).getRootNode());
    }

    /**
     * Gets the box.
     *
     * @return the box
     */
    Pane getBox() {
        return this.box;
    }

    /**
     * Gets the button by tab.
     *
     * @param t the t
     * @return the button by tab
     */
    public Button getButtonByTab(final TabBB t) {
        return this.buttonByTab.get(t.name());
    }

    /**
     * Draw marker.
     *
     * @param x the x
     * @param y the y
     */
    public void drawMarker(final Button dragged, final double x, final double y) {

        final int draggedIdx = getBox().getChildren().indexOf(dragged);
        final int markerIdx = getBox().getChildren().indexOf(this.marker);

        int idx = 0;
        Node tempHoverNode = null;
        final int xx = 0;
        for (final Node n : getBox().getChildren()) {

            if (n.getBoundsInParent().contains(x, y)) {
                tempHoverNode = n;
                break;
            }

            if (n != this.marker) {
                idx++;
            }
        }

        if (tempHoverNode == this.hoverNode) {
            return;
        } else {
            this.hoverNode = tempHoverNode;
        }

        System.out.println("marker" + markerIdx + "  idx " + idx);
        if (markerIdx != idx) {

            if (this.marker != null) {
                getBox().getChildren().remove(this.marker);
            }

            if (draggedIdx != idx) {
                this.marker = getModel().getObject().orientation() == TabOrientation.bottom || getModel().getObject().orientation() == TabOrientation.top ?
                        new Rectangle(10, getBox().getHeight()) : new Rectangle(getBox().getWidth(), 4);

                this.marker.setFill(Color.LIGHTGREEN);

                getBox().getChildren().add(idx, this.marker);
            }
        }

    }

    /**
     * Removes the marker.
     *
     * @return the int
     */
    public int removeMarker() {
        System.out.println("Remove Marker");
        final int idx = getBox().getChildren().indexOf(this.marker);
        getBox().getChildren().remove(this.marker);
        return idx;
    }

}