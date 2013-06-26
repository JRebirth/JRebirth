package org.jrebirth.core.service.basic;

import java.io.File;

import javafx.scene.Scene;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.WaveTypeBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.javafx.css.StyleManager;

/**
 * The class <strong>StageService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class StyleSheetTrackerService extends ServiceBase {

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_OPEN_STAGE = WaveTypeBase.build("OPEN_STAGE");

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_STAGE_OPENED = WaveTypeBase.build("STAGE_OPENED");

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StyleSheetTrackerService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        // registerCallback(DO_OPEN_STAGE, RE_STAGE_OPENED);
    }

    /**
     * Open a stage.
     * 
     * @param wave the source wave
     */
    public void listen(final String styleSheetPath, final Scene scene) {

        LOGGER.trace("Open a stage.");

        File file = new File(styleSheetPath);

        file.lastModified();

        StyleManager.getInstance().reloadStylesheets(scene);

    }

    public void start() {
        // Nothing to do yet

    }

}
