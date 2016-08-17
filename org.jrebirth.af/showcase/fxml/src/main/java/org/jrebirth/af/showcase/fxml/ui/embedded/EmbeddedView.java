package org.jrebirth.af.showcase.fxml.ui.embedded;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import org.jrebirth.af.api.resource.fxml.FXMLItem;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.fxml.FXML;
import org.jrebirth.af.core.ui.DefaultView;

public class EmbeddedView extends DefaultView<EmbeddedModel, BorderPane, EmbeddedController> {

    private static FXMLItem ROOT_EMBEDDED_FXML = Resources.create(new FXML("org.jrebirth.af.showcase.fxml.ui.embedded", "Embedded"));

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

        this.box = new VBox();
        this.box.setAlignment(Pos.CENTER);

        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(600, 600);
        scrollPane.setContent(this.box);

        node().setCenter(scrollPane);

        addNode(EmbeddedView.ROOT_EMBEDDED_FXML.get().node());

        // Load 2 more instance
        addNode(EmbeddedView.ROOT_EMBEDDED_FXML.getNew().node());
        addNode(EmbeddedView.ROOT_EMBEDDED_FXML.getNew().node());

    }

    public void addNode(final Node node) {

        this.box.getChildren().add(node);

    }

}
