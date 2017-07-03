package org.jrebirth.af.showcase.fonticon.ui.fonticon;

import java.util.stream.Stream;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import org.jrebirth.af.api.ui.NullController;
import org.jrebirth.af.core.resource.provided.IconFont;
import org.jrebirth.af.core.ui.DefaultView;

public class FontIconView extends DefaultView<FontIconModel, ScrollPane, NullController> {

    public FontIconView(final FontIconModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().setFitToWidth(true);
        node().setFitToHeight(true);

        final FlowPane pane = new FlowPane(Orientation.HORIZONTAL);
        pane.setPadding(new Insets(10));
        pane.setHgap(4.0);
        pane.setVgap(4.0);

        pane.setAlignment(Pos.CENTER);

        pane.maxWidthProperty().bind(((Region) node().getParent()).widthProperty());

        final Class<? extends IconFont> c = model().object();
        Stream.of(c.getEnumConstants()).filter(c::isInstance).map(c::cast).forEach(iconFont -> pane.getChildren().add(buildTile(iconFont)));

        // pane.setStyle("-fx-background-color:blue;");
        node().setContent(pane);
    }

    private Node buildTile(final IconFont iconFont) {
        final VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(100, 50);

        final Label icon = new Label();
        iconFont.use(icon);
        icon.setPadding(new Insets(4));
        icon.setAlignment(Pos.CENTER);
        VBox.setVgrow(icon, Priority.ALWAYS);

        final Label text = new Label(iconFont.name());
        text.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(text, Priority.SOMETIMES);

        text.setTooltip(new Tooltip(iconFont.name()));

        box.getChildren().addAll(icon, text);

        // box.setStyle("-fx-background-color:red;");
        return box;
    }

}
