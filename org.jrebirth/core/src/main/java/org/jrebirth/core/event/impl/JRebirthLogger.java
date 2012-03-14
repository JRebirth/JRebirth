package org.jrebirth.core.event.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.jrebirth.core.event.AbstractRecord;
import org.jrebirth.core.event.LogLevel;

/**
 * The class <strong>JRebirthLogger</strong>.
 * 
 * Used to log string of a JRebirth application.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public class JRebirthLogger extends AbstractRecord {

    /** The <code>LOG_SEPARATOR</code> field is used separate line content. */
    private static final String LOG_SEPARATOR = " - ";

    /**
     * {@inheritDoc}
     */
    @Override
    protected OutputStream buildOutputStream() {
        return new OutConsole();
    }

    /**
     * Log a trace message.
     * 
     * @param message the trace message to log
     */
    public void trace(final String message) {
        log(LogLevel.TRACE, message);
    }

    /**
     * Log a debug message.
     * 
     * @param message the debug message to log
     */
    public void debug(final String message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Log an info message.
     * 
     * @param message the info message to log
     */
    public void info(final String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Log a warn message.
     * 
     * @param message the warn message to log
     */
    public void warn(final String message) {
        log(LogLevel.WARN, message);
    }

    /**
     * Log an error message.
     * 
     * @param message the error message to log
     */
    public void error(final String message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Log a fatal message.
     * 
     * @param message the fatal message to log
     */
    public void fatal(final String message) {
        log(LogLevel.FATAL, message);
    }

    /**
     * Log a new line.
     * 
     * @param logLevel the level to used
     * @param message the message to log
     */
    private void log(final LogLevel logLevel, final String message) {
        record(logLevel.name() + LOG_SEPARATOR + message);
    }

    /**
     * The class <strong>OutConsole</strong>.
     * 
     * @author Sébastien Bordes
     * 
     * @version $Revision$ $Author$
     * @since $Date$
     */
    private static final class OutConsole extends OutputStream {

        /**
         * {@inheritDoc}
         */
        @Override
        public void write(final int b) throws IOException {
            System.out.println(b);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void write(final byte[] b) throws IOException {
            System.out.println(b);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void write(final byte[] b, final int off, final int len) throws IOException {
            System.out.println(b);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void flush() throws IOException {
            System.out.flush();
        }
    }

}
