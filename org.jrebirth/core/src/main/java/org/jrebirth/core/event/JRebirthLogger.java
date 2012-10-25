/**
 * Copyright JRebirth.org © 2011-2012 
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.core.event;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The class <strong>JRebirthLogger</strong>.
 * 
 * Used to log string of a JRebirth application into the client console.
 * 
 * @author Sébastien Bordes
 */
public class JRebirthLogger extends AbstractRecord {

    /** The <code>LOG_SEPARATOR</code> field is used separate line content. */
    private static final String LOG_SEPARATOR = " - ";

    /** The singleton to access from other thread. */
    private static JRebirthLogger instance;

    /** Check if the logger is enabled. */
    private boolean enabled = true;

    /**
     * Return the singleton used to log into the client console.
     * 
     * @return the singleton
     */
    public static JRebirthLogger getInstance() {
        synchronized (JRebirthLogger.class) {
            if (instance == null) {
                instance = new JRebirthLogger();
            }
            return instance;
        }
    }

    /**
     * @return Returns the enabled.
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * @param enabled The enabled to set.
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<OutputStream> buildOutputStreamList() {
        final List<OutputStream> fosList = new ArrayList<>();
        fosList.add(new OutConsole(System.out));
        return fosList;
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
     * Log the fatal message and print the stack trace.
     * 
     * @param e the exception to log
     */
    public void logException(final Exception e) {
        fatal(e.toString());
        for (final StackTraceElement ste : e.getStackTrace()) {
            record("\t" + ste.toString());
        }
    }

    /**
     * Log a new line.
     * 
     * @param logLevel the level to used
     * @param message the message to log
     */
    private void log(final LogLevel logLevel, final String message) {
        if (isEnabled()) {
            record(logLevel.name() + LOG_SEPARATOR + message);
        }
    }

    /**
     * The class <strong>OutConsole</strong>.
     * 
     * @author Sébastien Bordes
     */
    private static final class OutConsole extends OutputStream {

        /** The inner output stream to used. */
        private final OutputStream os;

        /**
         * Default Constructor.
         * 
         * @param os the system output stream
         */
        public OutConsole(final OutputStream os) {
            super();
            this.os = os;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void write(final int b) throws IOException {
            this.os.write(b);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void write(final byte[] b) throws IOException {
            this.os.write(b);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void write(final byte[] b, final int off, final int len) throws IOException {
            this.os.write(b, off, len);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void flush() throws IOException {
            this.os.flush();
        }
    }

}
