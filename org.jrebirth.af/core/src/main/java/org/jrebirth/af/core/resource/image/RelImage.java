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
package org.jrebirth.af.core.resource.image;

import java.util.Arrays;
import java.util.List;

import org.jrebirth.af.api.resource.image.ImageExtension;
import org.jrebirth.af.api.resource.image.ImageParams;

/**
 * The interface <strong>LocalImage</strong>.
 *
 * @author Sébastien Bordes
 */
public class RelImage extends AbstractBaseImage implements ImageParams {

    /**
     * Default Constructor.
     *
     * @param path the image local path
     * @param name the file name
     * @param extension the image extension
     */
    public RelImage(final String path, final String name, final ImageExtension extension) {
        super(path, name, extension);
    }

    /**
     * Default Constructor.
     *
     * @param name the file name
     * @param extension the image extension
     */
    public RelImage(final String name, final ImageExtension extension) {
        this("", name, extension);
    }

    /**
     * Default Constructor.
     *
     * @param fullName the full file name (including path and image extension)
     */
    public RelImage(final String fullName) {
        this("", fullName, ImageExtension.NONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {
        if (parameters.length == 1) {
            nameProperty().set(parameters[0]);
        }
        if (parameters.length == 2) {
            nameProperty().set(parameters[0]);
            extensionProperty().set(ImageExtension.valueOf(parameters[1]));
        }
        if (parameters.length == 3) {
            pathProperty().set(parameters[0]);
            nameProperty().set(parameters[1]);
            extensionProperty().set(ImageExtension.valueOf(parameters[2]));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(path(), name(), extension().toString());
    }

}
