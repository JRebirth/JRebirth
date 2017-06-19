package org.jrebirth.demo.comparisontool.ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.core.wave.JRebirthItems;
import org.jrebirth.demo.comparisontool.bean.FileComparison;
import org.jrebirth.demo.comparisontool.bean.PathToCompare;
import org.jrebirth.demo.comparisontool.command.DisplayExceptionCommand;
import org.jrebirth.demo.comparisontool.resources.ComparisonParameters;
import org.jrebirth.demo.comparisontool.service.ComparatorService;
import org.jrebirth.demo.comparisontool.service.ExportCSVService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleModel</strong>.
 *
 * @author
 */
public final class CheckerModel extends DefaultObjectModel<CheckerModel, CheckerView, PathToCompare> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckerModel.class);
    private FilteredList<FileComparison> filteredList;
    private SortedList<FileComparison> sortedList;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        LOGGER.debug("Init Sample Model");

        // Initialize default values
        object().sourcePath(new File(ComparisonParameters.sourcePath.get()))
                .targetPath(new File(ComparisonParameters.targetPath.get()))
                .missing(false)
                .newer(false)
                .upgraded(false)
                .downgraded(false);

        filteredList = new FilteredList<>(object().pLastResult(), this::filter);
        sortedList = new SortedList<>(filteredList);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Put the code to initialize inner models here (if any)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {

        Bindings.bindBidirectional(view().sourceText().textProperty(), object().pSourcePath(), new FileStringConverter());
        Bindings.bindBidirectional(view().targetText().textProperty(), object().pTargetPath(), new FileStringConverter());

        sortedList.comparatorProperty().bind(view().getTable().comparatorProperty());
        view().getTable().setItems(sortedList);

        bindFilterButton();

        bindButtonEnabling();

    }

    /**
     * TODO To complete.
     */
    private void bindButtonEnabling() {
        final BooleanBinding bexpr = new BooleanBinding() {

            @Override
            protected boolean computeValue() {
                return !(object().sourcePath() != null && object().sourcePath().exists() && object().sourcePath().isDirectory()
                        && object().targetPath() != null && object().targetPath().exists() && object().targetPath().isDirectory());
            }
        };

        final ChangeListener<File> cl = new ChangeListener<File>() {

            @Override
            public void changed(final ObservableValue<? extends File> observable, final File oldValue, final File newValue) {
                bexpr.invalidate();
            }
        };

        object().pSourcePath().addListener(cl);
        object().pTargetPath().addListener(cl);

        view().getStartButton().disableProperty().bind(bexpr);

        final SimpleListProperty<FileComparison> filteredListProperty = new SimpleListProperty<>(view().getTable().getItems());
        view().getExportCSV().disableProperty().bind(filteredListProperty.emptyProperty());

        final SimpleListProperty<FileComparison> listProperty = new SimpleListProperty<>(object().pLastResult());
        view().getMissing().disableProperty().bind(listProperty.emptyProperty());
        view().getDowngraded().disableProperty().bind(listProperty.emptyProperty());
        view().getNewer().disableProperty().bind(listProperty.emptyProperty());
        view().getSame().disableProperty().bind(listProperty.emptyProperty());
        view().getUpdated().disableProperty().bind(listProperty.emptyProperty());
        view().getUpgraded().disableProperty().bind(listProperty.emptyProperty());
    }

    private void bindFilterButton() {
        Bindings.bindBidirectional(view().getSame().selectedProperty(), object().pSame());
        Bindings.bindBidirectional(view().getUpdated().selectedProperty(), object().pUpdated());
        Bindings.bindBidirectional(view().getMissing().selectedProperty(), object().pMissing());
        Bindings.bindBidirectional(view().getNewer().selectedProperty(), object().pNewer());
        Bindings.bindBidirectional(view().getUpgraded().selectedProperty(), object().pUpgraded());
        Bindings.bindBidirectional(view().getDowngraded().selectedProperty(), object().pDowngraded());

        view().getSame().textProperty().bind(object().pSameCount().asString("Same (%s)"));
        view().getUpdated().textProperty().bind(object().pUpdatedCount().asString("Updated (%s)"));
        view().getMissing().textProperty().bind(object().pMissingCount().asString("Missing (%s)"));
        view().getNewer().textProperty().bind(object().pNewerCount().asString("Newer (%s)"));
        view().getUpgraded().textProperty().bind(object().pUpgradedCount().asString("Upgraded (%s)"));
        view().getDowngraded().textProperty().bind(object().pDowngradedCount().asString("Downgraded (%s)"));

        object().pSame().addListener(this::updateFilter);
        object().pDowngraded().addListener(this::updateFilter);
        object().pMissing().addListener(this::updateFilter);
        object().pNewer().addListener(this::updateFilter);
        object().pUpdated().addListener(this::updateFilter);
        object().pUpgraded().addListener(this::updateFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Process a wave action, you must listen the wave type before
    }

    @OnWave(ComparatorService.COMPARISON_DONE)
    public void displayResult(final List<FileComparison> result, final Wave wave) {

        object().lastResult(result);

        object().pSameCount().set((int) result.stream().filter(fc -> fc.isSame()).count());
        object().pUpdatedCount().set((int) result.stream().filter(fc -> fc.isUpdated()).count());
        object().pMissingCount().set((int) result.stream().filter(fc -> fc.isMissing()).count());
        object().pNewerCount().set((int) result.stream().filter(fc -> fc.isNewer()).count());
        object().pUpgradedCount().set((int) result.stream().filter(fc -> fc.isUpgraded()).count());
        object().pDowngradedCount().set((int) result.stream().filter(fc -> fc.isDowngraded()).count());

        view().hideProgress();
    }

    @OnWave(ExportCSVService.EXPORT_CSV_DONE)
    public void achieveExport(final File resultFile, final Wave wave) {

        view().hideProgress();

        if (resultFile != null && resultFile.exists()) {
            try {
                java.awt.Desktop.getDesktop().open(resultFile);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnWave(ExportCSVService.FILE_NOT_FOUND)
    public void manageException(final Wave wave) {

        view().hideProgress();

        callCommand(DisplayExceptionCommand.class, wave.getData(JRebirthItems.exceptionItem), wave.getData(JRebirthItems.waveItem));
    }

    public void updateFilter(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
        filteredList.setPredicate(this::filter);
    }

    private boolean filter(FileComparison fc) {

        if (fc.isMissing() && object().missing() ||
                fc.isNewer() && object().newer() ||
                fc.isUpgraded() && object().upgraded() ||
                fc.isDowngraded() && object().downgraded() ||
                fc.isSame() && object().same() ||
                fc.isUpdated() && object().updated() ||
                !object().updated() && !object().same() && !object().missing() && !object().newer() && !object().upgraded() && !object().downgraded()) {
            return true;
        }
        return false;
    }

}
