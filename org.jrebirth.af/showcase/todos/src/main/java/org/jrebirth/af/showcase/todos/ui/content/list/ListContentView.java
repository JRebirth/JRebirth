package org.jrebirth.af.showcase.todos.ui.content.list;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.DefaultStringConverter;

import org.jrebirth.af.core.ui.DefaultView;
import org.jrebirth.af.showcase.todos.ui.WWaves;

import bean.Todo;

public class ListContentView extends DefaultView<ListContentModel, BorderPane, ListContentController> {

    private static final String DONE_COLUMN = "DoneColumn";

    private static final String TEXT_COLUMN = "TextColumn";

    private TableView<Todo> table;

    public ListContentView(final ListContentModel model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void initView() {
        super.initView();

        this.table = new TableView<>();

        final TableColumn<Todo, Boolean> doneColumn = new TableColumn<>("Done");
        doneColumn.setId(DONE_COLUMN);
        doneColumn.setPrefWidth(40);
        doneColumn.setMaxWidth(40);
        doneColumn.setMinWidth(40);
        doneColumn.setCellValueFactory(cdf -> cdf.getValue().pDone());
        doneColumn.setCellFactory(tc -> new CheckBoxTableCell<>());

        final TableColumn<Todo, String> textColumn = new TableColumn<>("Text");
        textColumn.setId(TEXT_COLUMN);
        textColumn.setPrefWidth(200);
        textColumn.setMinWidth(100);
        textColumn.setEditable(true);
        textColumn.setCellValueFactory(this::getColumnContent);
        textColumn.setCellFactory(this::getTableCell);

        this.table.getColumns().setAll(doneColumn, textColumn);

        this.table.setPrefWidth(240);
        this.table.setPrefHeight(300);

        this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.table.setEditable(true);

        // Refresh status bar when a checkbox is updated
        this.table.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> model().sendWave(WWaves.UPDATE_STATUS_WT));

        node().setCenter(this.table);
    }

    private ObservableValue<String> getColumnContent(final CellDataFeatures<Todo, String> cell) {
        String res = null;

        switch (cell.getTableColumn().getId()) {
            case DONE_COLUMN:
                res = "";// cell.getValue().done();
                break;
            case TEXT_COLUMN:
                res = cell.getValue().text();
                break;
        }
        return new SimpleStringProperty(res);
    }

    private TableCell<Todo, String> getTableCell(final TableColumn<Todo, String> column) {
        final TextFieldTableCell<Todo, String> tc = new TextFieldTableCell<Todo, String>() {
            @Override
            public void updateItem(final String item, final boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                    setBackground(null);
                } else {
                    setText(item);

                    final Todo t = column.getTableView().getItems().get(getTableRow().getIndex());

                    getStyleClass().add("todoCell");
                    if (t.done()) {
                        getStyleClass().removeAll("undone");
                        getStyleClass().add("done");
                    } else {
                        getStyleClass().removeAll("done");
                        getStyleClass().add("undone");
                    }

                }
            }
        };
        tc.setConverter(new DefaultStringConverter());
        tc.setAlignment(Pos.CENTER_LEFT);
        return tc;
    }

    public TableView<Todo> getTable() {
        return this.table;
    }

}
