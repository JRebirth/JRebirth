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
import java.util.List;

import org.jrebirth.core.exception.CoreException;

/**
 * 
 * The class <strong>AbstractRecord</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractRecord implements Recordable {

    /** The file name to use. */
    private String fileName;

    /** The output stream to use. */
    private List<OutputStream> outputStreamList;

    /**
     * Return the output stream.
     * 
     * @return the output stream
     */
    private List<OutputStream> getOutputStreamList() {
        if (this.outputStreamList == null) {
            this.outputStreamList = buildOutputStreamList();
        }
        return this.outputStreamList;
    }

    /**
     * Build the Output Stream to use.
     * 
     * @return the OutputStream built
     */
    protected abstract List<OutputStream> buildOutputStreamList();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void record(final String data) {
        try {
            for (final OutputStream outputStream : getOutputStreamList()) {
                outputStream.write(data.getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.flush();
            }
        } catch (final IOException e) {
            JRebirthLogger.getInstance().logException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeOutputStream() throws CoreException {
        try {
            for (final OutputStream outputStream : getOutputStreamList()) {
                outputStream.close();
            }
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
