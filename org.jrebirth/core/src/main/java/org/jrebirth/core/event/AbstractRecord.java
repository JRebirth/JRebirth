package org.jrebirth.core.event;

import java.io.IOException;
import java.io.OutputStream;

import org.jrebirth.core.exception.CoreException;

/**
 * 
 * The class <strong>AbstractRecord</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public abstract class AbstractRecord implements Recordable {

    /** The file name to use. */
    private String fileName;

    /** The output stream to use. */
    private OutputStream outputStream;

    /**
     * Return the output stream.
     * 
     * @return the output stream
     */
    private OutputStream getOutputStream() {
        if (this.outputStream == null) {
            this.outputStream = buildOutputStream();
        }
        return this.outputStream;
    }

    /**
     * Build the Output Stream to use.
     * 
     * @return the OutputStream built
     */
    protected abstract OutputStream buildOutputStream();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void record(final String data) {
        try {
            getOutputStream().write(data.getBytes());
            getOutputStream().flush();
        } catch (final IOException e) {
            // Nothing that we can do
            return;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeOutputStream() throws CoreException {
        try {
            getOutputStream().close();
        } catch (final IOException e) {
            throw new CoreException("Impossible to close the output stream", e);
        }
    }

    /**
     * @return Returns the fileName.
     */
    @Override
    public final String getFileName() {
        return this.fileName;
    }

    /**
     * @param fileName The fileName to set.
     */
    public final void setFileName(final String fileName) {
        this.fileName = fileName;
    }
}
