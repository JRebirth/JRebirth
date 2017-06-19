package org.jrebirth.af.showcase.todos.ui.main;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

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

        final ImageView appName = new ImageView(TodosImages.HEADER_LOGO.get());
        node().setTop(appName);

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
