package org.jrebirth.showcase.fxml.ui.embedded;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import org.jrebirth.core.resource.Resources;
import org.jrebirth.core.resource.fxml.FXML;
import org.jrebirth.core.resource.fxml.FXMLItem;
import org.jrebirth.core.ui.DefaultView;

public class EmbeddedView extends DefaultView<EmbeddedModel, AnchorPane, EmbeddedController> {

    FXMLItem EMBEDDED_FXML = Resources.create(new FXML("org/jrebirth/showcase/fxml/ui/embedded/Embedded"));

    private BorderPane borderPane;

    public EmbeddedView(EmbeddedModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        super.initView();

        getRootNode().setPrefHeight(400);
        getRootNode().setPrefWidth(600);

        borderPane = new BorderPane();

        getRootNode().getChildren().add(borderPane);

        addNode(EMBEDDED_FXML.get().getNode());
    }

    public void addNode(Node node) {

        borderPane.setCenter(node);

    }

}
