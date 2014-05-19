package org.jrebirth.af.core.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * The class <strong>ServiceUtility</strong> is used to provide convenient method related to Service tasks.
 *
 * @author Sébastien Bordes
 */
public final class ServiceUtility implements ServiceMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(ServiceUtility.class);

    /**
     * Private Constructor.
     */
    private ServiceUtility() {
        // Nothing to do
    }

    /**
     * Count the number of line of the given file. <br />
     * If an exception occurred it will be logged and then return -1
     *
     * @param file the file to inspect
     *
     * @return the number of lines of the file or -1
     */
    public static int countFileLines(final File file) {
        int res = -1;

        try (LineNumberReader reader = new LineNumberReader(new FileReader(file));) {

            // Read each line to increment the line number of the reader
            while (reader.readLine() != null) {
                ;
            }
            // Extract the current line number
            res = reader.getLineNumber();

        } catch (final IOException e) {
            LOGGER.error(COUNT_LINES_ERROR, e, file.getAbsolutePath());
        }
        return res;
    }

}