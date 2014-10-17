package org.jrebirth.af.showcase.fxml.ui.embedded;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.fxml.FXML;
import org.jrebirth.af.core.resource.fxml.FXMLItem;
import org.jrebirth.af.core.ui.DefaultView;

public class EmbeddedView extends DefaultView<EmbeddedModel, AnchorPane, EmbeddedController> {

    private static FXMLItem ROOT_EMBEDDED_FXML = Resources.create(new FXML("org.jrebirth.af.showcase.fxml.ui.embedded", "Embedded"));

    private BorderPane borderPane;

    private VBox box;

    public EmbeddedView(final EmbeddedModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        super.initView();

        getRootNode().setMaxHeight(Region.USE_PREF_SIZE);
        getRootNode().setMaxWidth(Region.USE_PREF_SIZE);
        getRootNode().setMinHeight(Region.USE_PREF_SIZE);
        getRootNode().setMinWidth(Region.USE_PREF_SIZE);

        getRootNode().setPrefHeight(400);
        getRootNode().setPrefWidth(600);

        box = new VBox();
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(600, 600);
        scrollPane.setContent(box);
        
        this.borderPane = new BorderPane();
        this.borderPane.setCenter(scrollPane);

        getRootNode().getChildren().add(this.borderPane);

        addNode(EmbeddedView.ROOT_EMBEDDED_FXML.get().getNode());
        
        // Load 2 more instance
        addNode(EmbeddedView.ROOT_EMBEDDED_FXML.getNew().getNode());
        addNode(EmbeddedView.ROOT_EMBEDDED_FXML.getNew().getNode());
        
    }

    public void addNode(final Node node) {

        this.box.getChildren().add(node);

    }

}
