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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.jrebirth.core.resource.AbstractBaseParams;

/**
 * The interface <strong>AbstractBaseImage</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseImage extends AbstractBaseParams implements ImageParams {

    /** The image path. */
    private final StringProperty path = new SimpleStringProperty(""); // Avoid NPE for path

    /** The image path. */
    private final StringProperty nameProperty = new SimpleStringProperty();

    /** The image path. */
    private final ObjectProperty<ImageExtension> extensionProperty = new SimpleObjectProperty<>();

    /** The requested width. */
    private final double requestedWidth;

    /** The requested height. */
    private final double requestedHeight;

    /** The preserve ratio. */
    private final boolean preserveRatio;

    /** The smooth. */
    private final boolean smooth;

    /** The background loading. */
    private final boolean backgroundLoading;

    /**
     * Default Constructor.
     * 
     * @param path the path of the image to load
     * @param name the image file name to use.
     * @param extension the image extension to use
     */
    public AbstractBaseImage(final String path, final String name, final ImageExtension extension) {
        this(path, name, extension, 0, 0, false, false, false);
    }

    /**
     * Default Constructor.
     * 
     * @param path the path of the image to load
     * @param name the image file name to use.
     * @param extension the image extension to use
     * @param requestedWidth the requested Width
     * @param requestedHeight the requested Height
     * @param preserveRatio the preserve Ratio
     * @param smooth the smooth
     * @param backgroundLoading the background loading
     */
    public AbstractBaseImage(final String path, final String name, final ImageExtension extension, final double requestedWidth,
            final double requestedHeight, final boolean preserveRatio, final boolean smooth, final boolean backgroundLoading) {
        super();
        this.path.set(path);
        this.nameProperty.set(name);
        this.extensionProperty.set(extension);

        this.requestedWidth = requestedWidth;
        this.requestedHeight = requestedHeight;
        this.preserveRatio = preserveRatio;
        this.smooth = smooth;
        this.backgroundLoading = backgroundLoading;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String path() {
        return this.path.get();
    }

    /**
     * Path property.
     * 
     * @return the string property
     */
    public StringProperty pathProperty() {
        return this.path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return this.nameProperty.get();
    }

    /**
     * Name property.
     * 
     * @return the string property
     */
    public StringProperty nameProperty() {
        return this.nameProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageExtension extension() {
        return this.extensionProperty.get();
    }

    /**
     * Extension property.
     * 
     * @return the object property
     */
    public ObjectProperty<ImageExtension> extensionProperty() {
        return this.extensionProperty;
    }

    /**
     * Requested width.
     * 
     * @return Returns the requestedWidth.
     */
    public double requestedWidth() {
        return this.requestedWidth;
    }

    /**
     * Requested height.
     * 
     * @return Returns the requestedHeight.
     */
    public double requestedHeight() {
        return this.requestedHeight;
    }

    /**
     * Preserve ratio.
     * 
     * @return Returns the preserveRatio.
     */
    public boolean preserveRatio() {
        return this.preserveRatio;
    }

    /**
     * Smooth.
     * 
     * @return Returns the smooth.
     */
    public boolean smooth() {
        return this.smooth;
    }

    /**
     * Background loading.
     * 
     * @return Returns the backgroundLoading.
     */
    public boolean backgroundLoading() {
        return this.backgroundLoading;
    }

}
