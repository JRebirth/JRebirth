package org.jrebirth.af.showcase.todos.ui.content;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import org.jrebirth.af.core.ui.DefaultView;

import bean.Todo;

public class ContentView extends DefaultView<ContentModel, BorderPane, ContentController> {

    private static final String DONE_COLUMN = "DoneColumn";

    private static final String TEXT_COLUMN = "TextColumn";

    private TableView<Todo> table;

    public ContentView(ContentModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void initView() {
        super.initView();

        table = new TableView<>();

        final TableColumn<Todo, String> doneColumn = new TableColumn<>("Done");
        doneColumn.setId(DONE_COLUMN);
        doneColumn.setPrefWidth(150);
        doneColumn.setMinWidth(100);
        // sourceNameColumn.setCellValueFactory(this::getColumnContent);
        // sourceNameColumn.setCellFactory(this::getTableCell);

        final TableColumn<Todo, String> textColumn = new TableColumn<>("Text");
        textColumn.setId(TEXT_COLUMN);
        textColumn.setPrefWidth(80);
        textColumn.setMinWidth(60);
        // sourceVersionColumn.setCellValueFactory(this::getColumnContent);
        // sourceVersionColumn.setCellFactory(this::getTableCell);

        table.getColumns().setAll(doneColumn, textColumn);

        table.setPrefWidth(450);

        table.setPrefHeight(300);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        node().setCenter(table);
    }

}
