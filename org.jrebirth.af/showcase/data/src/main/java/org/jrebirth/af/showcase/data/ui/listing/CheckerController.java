package org.jrebirth.demo.comparisontool.ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.AbstractController;
import org.jrebirth.af.core.wave.JRebirthWaves;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.demo.comparisontool.service.ComparatorService;
import org.jrebirth.demo.comparisontool.service.ExportCSVService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleController</strong>.
 *
 * @author
 */
public final class CheckerController extends AbstractController<CheckerModel, CheckerView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckerController.class);

    /**
     * Default Constructor.
     *
     * @param view the view to control
     *
     * @throws CoreException if an error occurred while creating event handlers
     */
    public CheckerController(final CheckerView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventHandlers() throws CoreException {

    }

    /**
     * Manage Mouse click of widget that have annotation.
     *
     * @param event the mouse event
     */
    void onMouseClicked(final MouseEvent event) {

        LOGGER.debug("Start button clicked => Call Comparison Service");

        // Clear out the table view
        model().object().pLastResult().clear();

        // Call service to populate it again
        model().returnData(ComparatorService.class,
                           ComparatorService.DO_COMPARE,
                           WBuilder.waveData(JRebirthWaves.PROGRESS_BAR, view().getProgressBar()),
                           WBuilder.waveData(JRebirthWaves.TASK_TITLE, view().getProgressTitle()),
                           WBuilder.waveData(JRebirthWaves.TASK_MESSAGE, view().getProgressMessage()),
                           WBuilder.waveData(ComparatorService.SOURCE, model().object().sourcePath()),
                           WBuilder.waveData(ComparatorService.TARGET, model().object().targetPath()));

    }

    public void exportCSV(final ActionEvent ae) {

        final FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(".")); // to be stored
        fc.setInitialFileName("Export.csv"); // to be stored
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Comma Separated Value", "*.csv"));

        final File exportedFile = fc.showSaveDialog(model().localFacade().globalFacade().application().stage());

        if (exportedFile != null) {
            if (!exportedFile.exists()) {
                try {
                    exportedFile.createNewFile();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
            if (exportedFile.exists()) {
                // Manage Default Command Button
                model().returnData(ExportCSVService.class,
                                   ExportCSVService.DO_EXPORT_CSV,
                                   WBuilder.waveData(JRebirthWaves.PROGRESS_BAR, view().getProgressBar()),
                                   WBuilder.waveData(JRebirthWaves.TASK_TITLE, view().getProgressTitle()),
                                   WBuilder.waveData(JRebirthWaves.TASK_MESSAGE, view().getProgressMessage()),
                                   WBuilder.waveData(ExportCSVService.EXPORTED_FILE, exportedFile),
                                   WBuilder.waveData(ExportCSVService.CONTENT, view().getTable().getItems()));
            }
        }

    }

    public void chooseSource(final ActionEvent ae) {

        model().object().sourcePath(chooseFolder(model().object().sourcePath()));
    }

    public void chooseTarget(final ActionEvent ae) {

        model().object().targetPath(chooseFolder(model().object().targetPath()));
    }

    /**
     *
     */
    private File chooseFolder(final File currentFolder) {
        final DirectoryChooser fc = new DirectoryChooser();

        fc.setInitialDirectory(currentFolder != null && currentFolder.exists() ? currentFolder : new File("."));

        return fc.showDialog(model().localFacade().globalFacade().application().stage());
    }

}
