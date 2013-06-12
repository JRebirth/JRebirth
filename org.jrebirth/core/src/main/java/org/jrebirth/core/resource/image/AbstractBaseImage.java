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
 * The interface <strong>AbstractBaseImage</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public abstract class AbstractBaseImage extends AbstractBaseParams implements ImageParams {

    /** The image path. */
    private final String path;

    /** The image path. */
    private final String name;

    /** The image path. */
    private final ImageExtension extension;

    private final double requestedWidth = 0;
    private final double requestedHeight = 0;
    private final boolean preserveRatio = false;
    private final boolean smooth = false;
    private final boolean backgroundLoading = false;

    /**
     * Default Constructor.
     * 
     * @param path the path of the image to load
     * @param name the image file name to use.
     * @param extension the image extension to use
     */
    public AbstractBaseImage(final String path, String name, ImageExtension extension) {
        super();
        this.path = path;
        this.name = name;
        this.extension = extension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String path() {
        // Avoid NPE on path
        return path == null ? "" : path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageExtension extension() {
        return extension;
    }

}
