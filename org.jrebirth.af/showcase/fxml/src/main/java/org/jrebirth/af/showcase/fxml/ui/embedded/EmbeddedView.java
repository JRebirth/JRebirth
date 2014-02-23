package org.jrebirth.af.showcase.fxml.ui.embedded;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.fxml.FXML;
import org.jrebirth.af.core.resource.fxml.FXMLItem;
import org.jrebirth.af.core.ui.DefaultView;

public class EmbeddedView extends DefaultView<EmbeddedModel, AnchorPane, EmbeddedController> {

    private static FXMLItem ROOT_EMBEDDED_FXML = Resources.create(new FXML("org.jrebirth.af.showcase.fxml.ui.embedded", "Embedded"));

    private BorderPane borderPane;

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

        this.borderPane = new BorderPane();

        getRootNode().getChildren().add(this.borderPane);

        addNode(EmbeddedView.ROOT_EMBEDDED_FXML.get().getNode());
    }

    public void addNode(final Node node) {

        this.borderPane.setCenter(node);

    }

}
