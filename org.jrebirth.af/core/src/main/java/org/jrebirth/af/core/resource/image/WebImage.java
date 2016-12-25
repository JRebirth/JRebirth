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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.jrebirth.af.api.resource.image.ImageExtension;
import org.jrebirth.af.api.resource.image.ImageParams;

/**
 * The interface <strong>LocalImage</strong>.
 *
 * @author Sébastien Bordes
 */
public class WebImage extends AbstractBaseImage implements ImageParams {

    /** the local image path. */
    private final StringProperty websiteProperty = new SimpleStringProperty();

    /** Indicate if we must call http:// or https:// protocol. */
    private final BooleanProperty securedProperty = new SimpleBooleanProperty(Boolean.FALSE);

    /**
     * Default Constructor.
     *
     * @param website the website base url
     * @param path the path of the image to load
     * @param name the image file name to use.
     * @param extension the image extension to use
     */
    public WebImage(final String website, final String path, final String name, final ImageExtension extension) {
        super(path, name, extension);
        this.websiteProperty.set(website);
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
    public WebImage(final String website, final boolean secured, final String path, final String name, final ImageExtension extension) {
        this(website, path, name, extension);
        this.securedProperty.set(secured);
    }

    /**
     * Return the website base url.
     *
     * @return the website
     */
    public String website() {
        return this.websiteProperty.get();
    }

    /**
     * Return the website property.
     *
     * @return the website property
     */
    public StringProperty websiteProperty() {
        return this.websiteProperty;
    }

    /**
     * Return the secured flag.
     *
     * @return the secured
     */
    public Boolean secured() {
        return this.securedProperty.get();
    }

    /**
     * Return the secured property.
     *
     * @return the secured property
     */
    public BooleanProperty securedProperty() {
        return this.securedProperty;
    }

    /**
     * Build the image url.
     *
     * @return the full image url string
     */
    public String getUrl() {
        final StringBuilder sb = new StringBuilder();

        sb.append(secured() ? "https://" : "http://")
          .append(website())
          .append(path())
          .append(name())
          .append(extension());

        return sb.toString();
    }

    /**
     * Parse the serialized Web Image string to build a fresh instance.
     *
     * @param serializedImage the serialized string
     *
     * @return a new fresh instance of {@link WebImage}
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String... parameters) {
        if (parameters.length == 4) {
            websiteProperty().set(parameters[0]);
            pathProperty().set(parameters[1]);
            nameProperty().set(parameters[2]);
            extensionProperty().set(ImageExtension.of(parameters[3]));
        }
        if (parameters.length == 5) {
            websiteProperty().set(parameters[0]);
            securedProperty().set(readBoolean(parameters[1]));
            pathProperty().set(parameters[2]);
            nameProperty().set(parameters[3]);
            extensionProperty().set(ImageExtension.of(parameters[4]));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(website(), secured().toString(), path(), name(), extension().toString());
    }

}
