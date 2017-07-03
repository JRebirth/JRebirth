package org.jrebirth.af.showcase.todos.ui.content.list;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import org.jrebirth.af.core.ui.DefaultView;
import org.jrebirth.af.iconfontbridge.fontawesome.FontAwesome;

public class ListRowView extends DefaultView<ListRowModel, HBox, ListRowController> {

    public CheckBox done;
    public TextField contentInput;

    public HBox contentBox;

    public Label contentLabel;

    public Button deleteButton;

    public ListRowView(final ListRowModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void initView() {

        this.done = new CheckBox();
        this.contentLabel = new Label();
        this.deleteButton = new Button();
        FontAwesome.remove.use(this.deleteButton, 12);

        node().getChildren().addAll(this.done, this.contentLabel, this.deleteButton);

        this.deleteButton.setVisible(false);

        /*
         *
         * contentInput.visibleProperty().bind(viewModel.editModeProperty());
         *
         * contentInput.setOnAction(event -> viewModel.editModeProperty().set(false)); contentInput.focusedProperty().addListener((obs, oldV, newV) -> { if (!newV) {
         * viewModel.editModeProperty().set(false); } });
         *
         * contentBox.visibleProperty().bind(viewModel.editModeProperty().not()); done.visibleProperty().bind(viewModel.editModeProperty().not());
         *
         * contentLabel.textProperty().bind(viewModel.contentProperty()); contentLabel.setOnMouseClicked(event -> { if (event.getClickCount() > 1) { viewModel.editModeProperty().set(true);
         * contentInput.requestFocus(); } });
         *
         *
         * viewModel.textStrikeThrough().addListener((obs, oldV, newV) -> { if (newV) { contentLabel.getStyleClass().add(STRIKETHROUGH_CSS_CLASS); } else {
         * contentLabel.getStyleClass().remove(STRIKETHROUGH_CSS_CLASS); } });
         */

    }

    CheckBox getDone() {
        return this.done;
    }

    TextField getContentInput() {
        return this.contentInput;
    }

    Label getContentLabel() {
        return this.contentLabel;
    }

    Button getDeleteButton() {
        return this.deleteButton;
    }

}
