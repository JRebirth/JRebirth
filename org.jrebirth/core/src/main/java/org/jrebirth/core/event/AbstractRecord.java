package org.jrebirth.core.event;

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
     * {@inheritDoc}
     */
    @Override
    public OutputStream getOutputStream() throws CoreException {
        if (this.outputStream == null) {
            /*
             * try { //this.outputStream = new FileOutputStream("events.etd"); } catch (final FileNotFoundException e) { throw new CoreException("Impossible to create file", e); }
             */
        }
        return this.outputStream;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void record(final String data) {
        // try {
        // getOutputStream().write(data.getBytes());
        // // getOutputStream().write("\r\n".getBytes());
        // getOutputStream().flush();
        // } catch (final IOException | CoreException e) {
        // // Nothing to do yet FIXME CATCH GLOBAL EXCEPTION
        // return;
        // }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeOutputStream() throws CoreException {
        // try {
        // getOutputStream().close();
        // } catch (final IOException e) {
        // throw new CoreException("Impossible to close the stream", e);
        // }
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
