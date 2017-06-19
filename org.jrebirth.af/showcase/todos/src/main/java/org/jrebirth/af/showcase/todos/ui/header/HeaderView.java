package org.jrebirth.af.showcase.todos.ui.header;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import org.jrebirth.af.core.ui.DefaultView;

public class HeaderView extends DefaultView<HeaderModel, HBox, HeaderController> {

    private CheckBox selectAll;

    // @OnKey(Key.Released)
    private TextField todoText;

    public HeaderView(final HeaderModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        super.initView();

        node().setStyle("-fx-background-color:white");
        node().setPadding(new Insets(10, 20, 10, 20));
        node().setSpacing(40);
        node().setAlignment(Pos.CENTER);

        this.selectAll = new CheckBox();

        this.todoText = new TextField();
        this.todoText.setPrefWidth(Integer.MAX_VALUE);
        HBox.setHgrow(this.todoText, Priority.ALWAYS);

        node().getChildren().addAll(this.selectAll, this.todoText);

    }

    /**
     * @return Returns the selectAll.
     */
    CheckBox selectAll() {
        return this.selectAll;
    }

    /**
     * @return Returns the todoText.
     */
    TextField todoText() {
        return this.todoText;
    }

}
