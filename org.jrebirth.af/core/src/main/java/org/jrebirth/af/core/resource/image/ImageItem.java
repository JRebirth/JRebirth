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

import javafx.scene.image.Image;

import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.resource.ResourceItem;

/**
 * The class <strong>ImageItem</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface ImageItem extends ResourceItem<Image, ImageItem, ImageParams, ImageBuilder> {

    /**
     * {@inheritDoc}
     */
    @Override
    default ImageItem set(final ImageParams imageParams) {
        builder().storeParams(this, imageParams);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default Image get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default ImageBuilder builder() {
        return ResourceBuilders.IMAGE_BUILDER;
    }

    public interface Local extends ImageItem {

        /**
         * .
         * 
         * @param path the image local path
         * @param name the file name
         * @param extension the image extension
         */
        default void local(final String path, final String name, final ImageExtension extension) {
            set(new LocalImage(path, name, extension));
        }

        /**
         * .
         * 
         * @param name the file name
         * @param extension the image extension
         */
        default void local(final String name, final ImageExtension extension) {
            set(new LocalImage(name, extension));
        }

        /**
         * .
         * 
         * @param fullName the full file name (including path and image extension)
         */
        default void local(final String fullName) {
            set(new LocalImage(fullName));
        }

    }

    public interface Web extends ImageItem {

        /**
         * Default Constructor.
         * 
         * @param website the website base url
         * @param path the path of the image to load
         * @param name the image file name to use.
         * @param extension the image extension to use
         */
        default void web(final String website, final String path, final String name, final ImageExtension extension) {
            set(new WebImage(website, path, name, extension));
        }

        /**
         * Default Constructor.
         * 
         * @param website the website base url
         * @param secured the http protocol to use (http or https)
         * @param path the path of the image to load
         * @param name the image file name to use.
         * @param extension the image extension to use
         */
        default void web(final String website, final boolean secured, final String path, final String name, final ImageExtension extension) {
            set(new WebImage(website, secured, path, name, extension));
        }
    }

}
