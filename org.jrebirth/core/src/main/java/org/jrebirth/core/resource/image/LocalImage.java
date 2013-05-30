/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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
package org.jrebirth.core.resource.image;

import org.jrebirth.core.resource.AbstractBaseParams;

/**
 * The interface <strong>LocalImage</strong>.
 * 
 * @author Sébastien Bordes
 */
public class LocalImage extends AbstractBaseParams implements ImageParams {

    /** the local image path. */
    private final String localPath;

    /**
     * Default Constructor.
     * 
     * @param path the image local path
     */
    public LocalImage(final String localPath) {
        super();
        this.localPath = localPath;
    }

    /**
     * Return the local image path.
     * 
     * @return the local image path
     */
    public String localPath() {
        return this.localPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.localPath;
    }

    /**
     * .
     * 
     * @param localPath
     * @return
     */
    public static LocalImage parseImage(final String localPath) {
        return new LocalImage(localPath);
    }
}
