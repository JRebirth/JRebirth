package org.jrebirth.af.showcase.todos.ui.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import org.jrebirth.af.core.ui.DefaultView;
import org.jrebirth.af.showcase.todos.resource.TodosImages;

public class MainView extends DefaultView<MainModel, BorderPane, MainController> {

    private BorderPane borderPane;

    public MainView(final MainModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        super.initView();

        StackPane sp = new StackPane();
        sp.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(4), new Insets(0))));
        final ImageView appName = new ImageView(TodosImages.HEADER_LOGO.get());
        StackPane.setAlignment(appName, Pos.CENTER);
        sp.getChildren().add(appName);
        node().setTop(sp);

        this.borderPane = new BorderPane();
        node().setCenter(this.borderPane);

        this.borderPane.setTop(model().headerModel().node());
        this.borderPane.setCenter(model().contentModel().node());
        this.borderPane.setBottom(model().statusModel().node());

    }

    /**
     * @return Returns the borderPane.
     */
    BorderPane borderPane() {
        return this.borderPane;
    }
}
