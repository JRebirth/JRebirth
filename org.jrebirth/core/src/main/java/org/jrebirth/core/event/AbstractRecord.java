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
            if (getOutputStream() != null) {
                getOutputStream().write(data.getBytes());
                getOutputStream().write("\r\n".getBytes());// TODO
                getOutputStream().flush();
            }
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
            if (getOutputStream() != null) {
                getOutputStream().close();
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
