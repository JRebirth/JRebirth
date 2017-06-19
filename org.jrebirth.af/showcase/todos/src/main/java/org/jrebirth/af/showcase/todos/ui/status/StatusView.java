package org.jrebirth.af.showcase.todos.ui.status;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

import org.jrebirth.af.core.ui.DefaultView;

public class StatusView extends DefaultView<StatusModel, HBox, StatusController> {

    private ToggleButton all;
    private ToggleButton active;
    private ToggleButton completed;
    private Label summary;

    public StatusView(StatusModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        super.initView();

        summary = new Label("0 item left");

        all = new ToggleButton("All");
        active = new ToggleButton("Active");
        completed = new ToggleButton("Done");

        node().getChildren().addAll(summary, all, active, completed);
    }

}
