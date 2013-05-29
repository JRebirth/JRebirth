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

import javafx.scene.image.Image;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.parameter.ParameterItem;

/**
 * The class <strong>ImageItemBase</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ImageItemBase implements ImageItem {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the image item. */
    private int uid;

    /**
     * Private Constructor.
     * 
     * @param imageParams the primitive values for the image
     */
    private ImageItemBase(final ImageParams imageParams) {
        builder().storeParams(this, imageParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageBuilder builder() {
        return ResourceBuilders.IMAGE_BUILDER;
    }

    /**
     * Build an image item.
     * 
     * @param imageParams the primitive values for the image
     * 
     * @return a new fresh image item object
     */
    public static ImageItemBase build(final ImageParams imageParams) {
        final ImageItemBase imageItem = new ImageItemBase(imageParams);

        // Ensure that the uid will be unique at runtime
        synchronized (ImageItemBase.class) {
            imageItem.setUid(++idGenerator);
        }
        return imageItem;
    }

    /**
     * Build a image item.
     * 
     * @param imageParameterName the parameter name used by this image
     * @param imageParams the primitive values for the image
     * 
     * @return a new fresh image item object
     */
    public static ImageItemBase build(final ParameterItem imageParameterName, final ImageParams imageParams) {
        final ImageItemBase imageItem = new ImageItemBase(imageParams);

        // Ensure that the uid will be unique at runtime
        synchronized (ImageItemBase.class) {
            imageItem.setUid(++idGenerator);
        }
        return imageItem;
    }

    /**
     * Gets the uid.
     * 
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     * 
     * @param uid The uid to set.
     */
    public void setUid(final int uid) {
        this.uid = uid;
    }

}
