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

import org.jrebirth.core.exception.CoreException;

/**
 * The interface <strong>Recordable</strong>.
 * 
 * Used to define how to save data created by the application.
 * 
 * @author Sébastien Bordes
 */
public interface Recordable {

    /**
     * Return the file name used to save the file.
     * 
     * @return the file name to use
     */
    String getFileName();

    /**
     * Write data into the output stream.
     * 
     * @param data the data to record
     * 
     * @throws CoreException if impossible to write into the output stream
     */
    void record(String data) throws CoreException;

    /**
     * Close the output stream.
     * 
     * @throws CoreException if an error occurred while closing the stream
     */
    void closeOutputStream() throws CoreException;

}
