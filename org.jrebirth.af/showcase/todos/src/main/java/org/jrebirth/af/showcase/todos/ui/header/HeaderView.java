package org.jrebirth.af.showcase.todos.ui.header;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import org.jrebirth.af.core.ui.DefaultView;

public class HeaderView extends DefaultView<HeaderModel, HBox, HeaderController> {

    private CheckBox selectAll;
    private TextField todoText;

    public HeaderView(HeaderModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        super.initView();

        selectAll = new CheckBox();

        todoText = new TextField();

        node().getChildren().addAll(selectAll, todoText);

    }

    /**
     * @return Returns the selectAll.
     */
    CheckBox selectAll() {
        return selectAll;
    }

    /**
     * @return Returns the todoText.
     */
    TextField todoText() {
        return todoText;
    }

}
