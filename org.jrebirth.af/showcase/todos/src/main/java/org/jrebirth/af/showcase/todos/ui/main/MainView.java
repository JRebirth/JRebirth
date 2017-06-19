package org.jrebirth.af.showcase.todos.ui.main;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import org.jrebirth.af.core.ui.DefaultView;

public class MainView extends DefaultView<MainModel, BorderPane, MainController> {

    private BorderPane borderPane;

    public MainView(MainModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        super.initView();

        final Text appName = new Text("Todos");
        node().setTop(appName);

        borderPane = new BorderPane();
        node().setCenter(borderPane);

        borderPane.setTop(model().headerModel().node());
        borderPane.setCenter(model().contentModel().node());
        borderPane.setBottom(model().statusModel().node());

    }

    /**
     * @return Returns the borderPane.
     */
    BorderPane borderPane() {
        return borderPane;
    }
}
