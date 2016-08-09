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

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.annotation.RootNodeClass;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.beans.TabbedPaneOrientation;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.DefaultView;

/**
 * The Class TabView only creates a TabPane like component that will handle Model Components.
 *
 * No Controller is needed.
 *
 * @author Sébastien Bordes
 */
@RootNodeClass("TabPanel")
public class TabbedPaneView extends DefaultView<TabbedPaneModel, BorderPane, TabbedPaneController> {

    /** The Constant LOGGER. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(TabbedPaneView.class);

    /** The stack pane. */
    private StackPane stackPane;

    private ToggleGroup group;

    /** The button by tab. */
    private final Map<String, ToggleButton> buttonByTab = new HashMap<>();

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
    public TabbedPaneView(final TabbedPaneModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        this.group = new ToggleGroup();
        initButtonBar();

        this.stackPane = new StackPane();

        node().setCenter(this.stackPane);

    }

    private void initButtonBar() {

        switch (model().object().orientation()) {
            case top:
                node().setTop(buildButtonBar(true));
                break;
            case bottom:
                node().setBottom(buildButtonBar(true));
                break;
            case left:
                node().setLeft(buildButtonBar(false));
                break;
            case right:
                node().setRight(buildButtonBar(false));
                break;
        }
    }

    void reloadButtonBar() {

        node().setTop(null);
        node().setBottom(null);
        node().setLeft(null);
        node().setRight(null);

        initButtonBar();
    }

    /**
     * Builds the button bar.
     *
     * @param isHorizontal the is horizontal
     * @return the pane
     */
    private Pane buildButtonBar(final boolean isHorizontal) {

        if (isHorizontal) {
            this.box = new HBox();
            this.box.setMaxWidth(Region.USE_COMPUTED_SIZE);

            this.box.getStyleClass().add("HorizontalTabbedPane");
            
        } else {
            this.box = new VBox();
            this.box.setMaxHeight(Region.USE_COMPUTED_SIZE);

            this.box.getStyleClass().add("VerticalTabbedPane");
        }
        
        switch(model().object().orientation()){
	        case top:
	        	this.box.getStyleClass().add("Top");
	            break;
	        case bottom:
	        	this.box.getStyleClass().add("Bottom");
	            break;
	        case left:
	        	this.box.getStyleClass().add("Left");
	            break;
	        case right:
	        	this.box.getStyleClass().add("Right");
	            break;
        }
        

        return this.box;
    }

    /**
     * Adds the tab.
     *
     * @param idx the idx
     * @param tab the tab
     */
    public void addTab(int idx, final Dockable tab) {

        final ToggleButton b = new ToggleButton(tab.name());// , new ImageView(model().getBehavior(DockableBehavior.class).modelIcon()));
        b.setToggleGroup(this.group);
        b.getStyleClass().clear();
        b.setUserData(tab);

        this.buttonByTab.put(tab.name(), b);

        selectTab(tab);

        controller().initTabEventHandler(b);

        if (idx < 0) {
            idx = this.box.getChildren().size();
        }

        b.setScaleX(0.0);
        this.box.getChildren().add(idx, b);
        
        if(this.box instanceof HBox){
        	HBox.setMargin(b, new Insets(0, 1, 0, 0));
        }else if(this.box instanceof VBox){
        	VBox.setMargin(b, new Insets(1, 0, 0, 0));
        }

        final ScaleTransition st = new ScaleTransition(Duration.millis(600));
        st.setNode(b);
        st.setFromX(0.0);
        st.setToX(1.0);

        st.play();
    }

    public void removeTab(final List<Dockable> tabs) {
        for (final Dockable tab : tabs) {
            final ToggleButton b = this.buttonByTab.get(tab.name());

            final ScaleTransition st = new ScaleTransition(Duration.millis(600));
            st.setNode(b);
            st.setFromX(1.0);
            st.setToX(0.0);
            st.setOnFinished(event -> this.box.getChildren().remove(b));

            st.play();
        }
    }

    /**
     * Select tab.
     *
     * @param t the t
     */
    void selectTab(final Dockable t) {
        // Remove all previously displayed children
        this.stackPane.getChildren().clear();
        //
        this.stackPane.getChildren().add(model().getModel(t.modelKey()).node());
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
    public ToggleButton getButtonByTab(final Dockable t) {
        return this.buttonByTab.get(t.name());
    }

    /**
     * Draw marker.
     *
     * @param x the x
     * @param y the y
     */
    public void drawMarker(final ToggleButton dragged, final double x, final double y) {

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
                this.marker = model().object().orientation() == TabbedPaneOrientation.bottom || model().object().orientation() == TabbedPaneOrientation.top ? new Rectangle(10, getBox().getHeight())
                        : new Rectangle(getBox().getWidth(), 4);

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
