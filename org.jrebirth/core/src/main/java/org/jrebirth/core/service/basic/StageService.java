package org.jrebirth.core.service.basic;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveTypeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>StageService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class StageService extends ServiceBase {

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_ADD_STAGE = WaveTypeBase.build("ADD_STAGE");

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_STAGE_ADDED = WaveTypeBase.build("STAGE_ADDED");

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StageService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        registerCallback(DO_ADD_STAGE, RE_STAGE_ADDED);
    }

    /**
     * Add a stage.
     * 
     * @param wave the source wave
     */
    public void addStage(final Wave wave) {

    }

}
