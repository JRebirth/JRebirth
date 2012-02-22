package org.jrebirth.core.event.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.jrebirth.core.event.AbstractRecord;
import org.jrebirth.core.exception.CoreException;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public OutputStream getOutputStream() throws CoreException {
        return new OutConsole();
    }

    /**
     * Log an error message.
     * 
     * @param message the error message to log
     */
    public void error(final String message) {
        System.err.println(message);
        record(message);
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
            System.out.write(b);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void write(final byte[] b) throws IOException {
            System.out.write(b);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void write(final byte[] b, final int off, final int len) throws IOException {
            System.out.write(b, off, len);
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
