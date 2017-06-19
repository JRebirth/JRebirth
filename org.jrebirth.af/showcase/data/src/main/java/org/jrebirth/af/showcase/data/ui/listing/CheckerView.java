package org.jrebirth.demo.comparisontool.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.resource.color.ColorItem;
import org.jrebirth.af.api.ui.annotation.OnMouse;
import org.jrebirth.af.api.ui.annotation.type.Mouse;
import org.jrebirth.af.core.ui.AbstractView;
import org.jrebirth.demo.comparisontool.bean.FileComparison;
import org.jrebirth.demo.comparisontool.resources.Colors;
import org.jrebirth.demo.comparisontool.resources.Images;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleView</strong>.
 *
 * @author
 */
public final class CheckerView extends AbstractView<CheckerModel, BorderPane, CheckerController> {

    private static final String SOURCE_COLUMN = "SourceColumn";

    private static final String TARGET_COLUMN = "TargetColumn";

    private static final String SOURCE_VERSION_COLUMN = "SourceVersion";

    private static final String SOURCE_QUALIFIER_COLUMN = "SourceQualifier";

    private static final String TARGET_VERSION_COLUMN = "TargetVersion";

    private static final String TARGET_QUALIFIER_COLUMN = "TargetQualifier";

    private static final String SOURCE_DATE_COLUMN = "SourceDate";

    private static final String TARGET_DATE_COLUMN = "TargetDate";

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckerView.class);

    /** Button used to trigger the SampleCommand. */
    @OnMouse(Mouse.Clicked)
    private Button startButton;

    private TextField sourceText;

    private Button openSource;

    private TextField targetText;

    private Button openTarget;

    private ToggleButton same;
    private ToggleButton updated;
    private ToggleButton missing;
    private ToggleButton newer;
    private ToggleButton upgraded;
    private ToggleButton downgraded;

    private Button exportCSV;

    private TableView<FileComparison> table;

    private ProgressPopUp ppu;

    /**
     * Default Constructor.
     *
     * @param model the controls view model
     *
     * @throws CoreException if build fails
     */
    public CheckerView(final CheckerModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().setTop(buildQueryPane());

        node().setCenter(buildResultPane());
    }

    private Node buildQueryPane() {

        final GridPane gp = new GridPane();

        gp.setPadding(new Insets(5));

        gp.setHgap(5);
        gp.setVgap(5);

        final Label sourceLbl = new Label("New Path:");
        GridPane.setConstraints(sourceLbl, 0, 0, 1, 1);

        this.sourceText = new TextField();
        GridPane.setFillWidth(this.sourceText, true);
        GridPane.setHgrow(this.sourceText, Priority.ALWAYS);

        GridPane.setConstraints(this.sourceText, 1, 0, 1, 1);
        this.openSource = new Button();
        this.openSource.getStyleClass().add("image");
        this.openSource.setGraphic(new ImageView(Images.Open.get()));
        GridPane.setConstraints(this.openSource, 2, 0, 1, 1);

        this.openSource.setOnAction(controller()::chooseSource);

        final Label targetLbl = new Label("Reference Path:");
        GridPane.setConstraints(targetLbl, 0, 1, 1, 1);

        this.targetText = new TextField();
        GridPane.setFillWidth(this.targetText, true);
        GridPane.setHgrow(this.targetText, Priority.ALWAYS);

        GridPane.setConstraints(this.targetText, 1, 1, 1, 1);
        this.openTarget = new Button();
        this.openTarget.getStyleClass().add("image");
        this.openTarget.setGraphic(new ImageView(Images.Open.get()));
        GridPane.setConstraints(this.openTarget, 2, 1, 1, 1);

        this.openTarget.setOnAction(controller()::chooseTarget);

        this.startButton = new Button("Start Comparison");
        this.startButton.setBorder(Border.EMPTY);
        this.startButton.setMinWidth(140);
        this.startButton.getStyleClass().add("image");
        this.startButton.setGraphic(new ImageView(Images.Start.get()));
        this.startButton.setContentDisplay(ContentDisplay.BOTTOM);
        GridPane.setConstraints(this.startButton, 3, 0, 2, 2, HPos.CENTER, VPos.CENTER);

        final HBox filterBox = new HBox(2);
        filterBox.setAlignment(Pos.CENTER);

        this.same = buildFilterButton("Same", Colors.Same);
        this.updated = buildFilterButton("Updated", Colors.Updated);
        this.missing = buildFilterButton("Missing", Colors.Missing);
        this.newer = buildFilterButton("New", Colors.Newer);
        this.upgraded = buildFilterButton("Upgraded", Colors.Upgraded);
        this.downgraded = buildFilterButton("Downgraded", Colors.Downgraded);

        filterBox.getChildren().addAll(this.missing, this.newer, this.same, this.updated, this.upgraded, this.downgraded);
        GridPane.setConstraints(filterBox, 0, 3, 4, 1, HPos.CENTER, VPos.CENTER);

        this.exportCSV = new Button();
        this.exportCSV.setBorder(Border.EMPTY);
        this.exportCSV.getStyleClass().add("image");
        this.exportCSV.setGraphic(new ImageView(Images.ExportCSV.get()));
        this.exportCSV.setMinHeight(40.0);
        this.exportCSV.setMaxHeight(40.0);
        this.exportCSV.setPrefHeight(40.0);

        this.exportCSV.setContentDisplay(ContentDisplay.BOTTOM);
        this.exportCSV.setOnAction(controller()::exportCSV);
        GridPane.setConstraints(this.exportCSV, 4, 3, 1, 1, HPos.RIGHT, VPos.CENTER);

        gp.getChildren().addAll(sourceLbl, this.sourceText, this.openSource, targetLbl, this.targetText, this.openTarget, this.startButton, filterBox, this.exportCSV);

        return gp;
    }

    /**
     * .
     * 
     * @return
     */
    private ToggleButton buildFilterButton(String name, ColorItem colorItem) {
        final ToggleButton tb = new ToggleButton(name);
        tb.getStyleClass().add("filterButton");
        // tb.setPrefSize(120, 30);
        tb.setBackground(new Background(new BackgroundFill(colorItem.get(), new CornerRadii(6.0), Insets.EMPTY)));
        return tb;
    }

    private Node buildResultPane() {

        this.table = new TableView<>();

        final TableColumn<FileComparison, String> sourceNameColumn = new TableColumn<>("Name");
        sourceNameColumn.setId(SOURCE_COLUMN);
        sourceNameColumn.setPrefWidth(150);
        sourceNameColumn.setMinWidth(100);
        sourceNameColumn.setCellValueFactory(this::getColumnContent);
        sourceNameColumn.setCellFactory(this::getTableCell);

        final TableColumn<FileComparison, String> sourceVersionColumn = new TableColumn<>("Version");
        sourceVersionColumn.setId(SOURCE_VERSION_COLUMN);
        sourceVersionColumn.setPrefWidth(80);
        sourceVersionColumn.setMinWidth(60);
        sourceVersionColumn.setCellValueFactory(this::getColumnContent);
        sourceVersionColumn.setCellFactory(this::getTableCell);

        final TableColumn<FileComparison, String> sourceQualifierColumn = new TableColumn<>("Qualifier");
        sourceQualifierColumn.setId(SOURCE_QUALIFIER_COLUMN);
        sourceQualifierColumn.setPrefWidth(80);
        sourceQualifierColumn.setMinWidth(60);
        sourceQualifierColumn.setCellValueFactory(this::getColumnContent);
        sourceQualifierColumn.setCellFactory(this::getTableCell);

        final TableColumn<FileComparison, String> sourceDateColumn = new TableColumn<>("Date");
        sourceDateColumn.setId(SOURCE_DATE_COLUMN);
        sourceDateColumn.setPrefWidth(80);
        sourceDateColumn.setMinWidth(60);
        sourceDateColumn.setCellValueFactory(this::getColumnContent);
        sourceDateColumn.setCellFactory(this::getTableCell);

        final TableColumn<FileComparison, String> targetNameColumn = new TableColumn<>("Name");
        targetNameColumn.setId(TARGET_COLUMN);
        targetNameColumn.setPrefWidth(150);
        targetNameColumn.setMinWidth(100);
        targetNameColumn.setCellValueFactory(this::getColumnContent);
        targetNameColumn.setCellFactory(this::getTableCell);

        final TableColumn<FileComparison, String> targetVersionColumn = new TableColumn<>("Version");
        targetVersionColumn.setId(TARGET_VERSION_COLUMN);
        targetVersionColumn.setPrefWidth(80);
        targetVersionColumn.setMinWidth(60);
        targetVersionColumn.setCellValueFactory(this::getColumnContent);
        targetVersionColumn.setCellFactory(this::getTableCell);

        final TableColumn<FileComparison, String> targetQualifierColumn = new TableColumn<>("Qualifier");
        targetQualifierColumn.setId(TARGET_QUALIFIER_COLUMN);
        targetQualifierColumn.setPrefWidth(80);
        targetQualifierColumn.setMinWidth(60);
        targetQualifierColumn.setCellValueFactory(this::getColumnContent);
        targetQualifierColumn.setCellFactory(this::getTableCell);

        final TableColumn<FileComparison, String> targetDateColumn = new TableColumn<>("Date");
        targetDateColumn.setId(TARGET_DATE_COLUMN);
        targetDateColumn.setPrefWidth(80);
        targetDateColumn.setMinWidth(60);
        targetDateColumn.setCellValueFactory(this::getColumnContent);
        targetDateColumn.setCellFactory(this::getTableCell);

        final TableColumn<FileComparison, String> sourceColumn = new TableColumn<>("New Path");
        sourceColumn.getColumns().setAll(sourceNameColumn, sourceVersionColumn, sourceQualifierColumn, sourceDateColumn);

        final TableColumn<FileComparison, String> targetColumn = new TableColumn<>("Reference Path");
        targetColumn.getColumns().setAll(targetNameColumn, targetVersionColumn, targetQualifierColumn, targetDateColumn);

        this.table.getColumns().setAll(sourceColumn, targetColumn);

        this.table.setPrefWidth(450);

        this.table.setPrefHeight(300);

        this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return this.table;
    }

    public ProgressBar getProgressBar() {

        this.ppu = new ProgressPopUp();
        this.ppu.start();
        return this.ppu.getProgressBar();
    }

    private TableCell<FileComparison, String> getTableCell(final TableColumn<FileComparison, String> column) {
        final TableCell<FileComparison, String> tc = new TableCell<FileComparison, String>() {
            @Override
            protected void updateItem(final String item, final boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                    setBackground(null);
                } else {
                    setText(item);

                    final FileComparison fc = column.getTableView().getItems().get(getTableRow().getIndex());
                    if (fc.isMissing()) {
                        setBackground(new Background(new BackgroundFill(Colors.Missing.get(), CornerRadii.EMPTY, Insets.EMPTY)));
                    } else if (fc.isNewer()) {
                        setBackground(new Background(new BackgroundFill(Colors.Newer.get(), CornerRadii.EMPTY, Insets.EMPTY)));
                    } else if (fc.isUpgraded()) {
                        setBackground(new Background(new BackgroundFill(Colors.Upgraded.get(), CornerRadii.EMPTY, Insets.EMPTY)));
                    } else if (fc.isDowngraded()) {
                        setBackground(new Background(new BackgroundFill(Colors.Downgraded.get(), CornerRadii.EMPTY, Insets.EMPTY)));
                    } else if (fc.isSame()) {
                        setBackground(new Background(new BackgroundFill(Colors.Same.get(), CornerRadii.EMPTY, Insets.EMPTY)));
                    } else if (fc.isUpdated()) {
                        setBackground(new Background(new BackgroundFill(Colors.Updated.get(), CornerRadii.EMPTY, Insets.EMPTY)));
                    } else {
                        setBackground(null);
                    }

                }
            }
        };
        tc.setAlignment(Pos.CENTER);
        return tc;
    }

    private ObservableValue<String> getColumnContent(final CellDataFeatures<FileComparison, String> cell) {
        String res = null;

        if (cell.getValue().getSource() != null) {
            switch (cell.getTableColumn().getId()) {
                case SOURCE_COLUMN:
                    res = cell.getValue().sourceName();
                    break;
                case SOURCE_VERSION_COLUMN:
                    res = cell.getValue().sourceVersion();
                    break;
                case SOURCE_QUALIFIER_COLUMN:
                    res = cell.getValue().sourceQualifier();
                    break;
                case SOURCE_DATE_COLUMN:
                    res = cell.getValue().sourceDate();
                    break;
            }
        }

        if (cell.getValue().getTarget() != null) {
            switch (cell.getTableColumn().getId()) {
                case TARGET_COLUMN:
                    res = cell.getValue().targetName();
                    break;
                case TARGET_VERSION_COLUMN:
                    res = cell.getValue().targetVersion();
                    break;
                case TARGET_QUALIFIER_COLUMN:
                    res = cell.getValue().targetQualifier();
                    break;
                case TARGET_DATE_COLUMN:
                    res = cell.getValue().targetDate();
                    break;
            }
        }
        return new SimpleStringProperty(res);
    }

    /**
     * @return the table
     */
    public TableView<FileComparison> getTable() {
        return this.table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        LOGGER.debug("Start the Sample View");
        // Custom code to process when the view is displayed the first time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        LOGGER.debug("Reload the Sample View");
        // Custom code to process when the view is displayed the 1+n time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        LOGGER.debug("Hide the Sample View");
        // Custom code to process when the view is hidden
    }

    // public TextArea textArea() {
    // return textArea;
    // }

    /**
     * @return the sourceText
     */
    TextField sourceText() {
        return this.sourceText;
    }

    /**
     * @return the targetText
     */
    TextField targetText() {
        return this.targetText;
    }

    /**
     * @return the missing
     */
    ToggleButton getSame() {
        return this.same;
    }

    ToggleButton getUpdated() {
        return this.updated;
    }

    /**
     * @return the missing
     */
    ToggleButton getMissing() {
        return this.missing;
    }

    /**
     * @return the newer
     */
    ToggleButton getNewer() {
        return this.newer;
    }

    /**
     * @return the upgraded
     */
    ToggleButton getUpgraded() {
        return this.upgraded;
    }

    /**
     * @return the downgraded
     */
    ToggleButton getDowngraded() {
        return this.downgraded;
    }

    public void hideProgress() {
        this.ppu.stop();

    }

    public StringProperty getProgressTitle() {
        return this.ppu.getProgressTitle();
    }

    public StringProperty getProgressMessage() {
        return this.ppu.getProgressMessage();
    }

    /**
     * @return the startButton
     */
    protected Button getStartButton() {
        return this.startButton;
    }

    /**
     * @return the exportCSV
     */
    protected Button getExportCSV() {
        return this.exportCSV;
    }

}
