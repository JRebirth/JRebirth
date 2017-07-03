package org.jrebirth.af.showcase.todos.ui.status;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import org.jrebirth.af.core.ui.DefaultView;

public class StatusView extends DefaultView<StatusModel, HBox, StatusController> {

    private ToggleButton all;
    private ToggleButton active;
    private ToggleButton completed;
    private Label summary;
    private ToggleGroup group;

    public StatusView(final StatusModel model) {
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

        this.summary = new Label("0 item left");
        HBox.setMargin(this.summary, new Insets(10, 80, 10, 10));

        this.group = new ToggleGroup();

        this.all = new ToggleButton("All");
        this.all.setUserData(FilterKind.All);
        this.all.setToggleGroup(this.group);

        this.active = new ToggleButton("Active");
        this.active.setUserData(FilterKind.Undone);
        this.active.setToggleGroup(this.group);

        this.completed = new ToggleButton("Done");
        this.completed.setUserData(FilterKind.Done);
        this.completed.setToggleGroup(this.group);

        this.all.setSelected(true);

        node().getChildren().addAll(this.summary, this.all, this.active, this.completed);

    }

    public ToggleGroup getGroup() {
        return this.group;
    }

    public void updateStatus(final long count) {
        if (count > 1) {
            this.summary.setText(count + " items left");
        } else {
            this.summary.setText(count + " item left");
        }

    }

}
