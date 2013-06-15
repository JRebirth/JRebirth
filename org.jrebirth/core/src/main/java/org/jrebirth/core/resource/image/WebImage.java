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

/**
 * The interface <strong>LocalImage</strong>.
 * 
 * @author Sébastien Bordes
 */
public class WebImage extends AbstractBaseImage implements ImageParams {

    /** the local image path. */
    private final String website;

    /** Indicate if we must call http:// or https:// protocol. */
    private Boolean secured = Boolean.FALSE;

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
        this.website = website;
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
        this.secured = secured;
    }

    /**
     * Return the website base url.
     * 
     * @return the website
     */
    public String website() {
        return this.website;
    }

    public Boolean secured() {
        return this.secured;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(website()).append(PARAMETER_SEPARATOR);
        sb.append(secured().toString()).append(PARAMETER_SEPARATOR);
        sb.append(path()).append(PARAMETER_SEPARATOR);
        sb.append(name()).append(PARAMETER_SEPARATOR);
        sb.append(extension());

        return sb.toString();
    }

    /**
     * .
     * 
     * @param localPath
     * @return
     */
    public static WebImage parseImage(final String serializedImage) {

        final String[] parameters = serializedImage.split(PARAMETER_SEPARATOR);

        return new WebImage(parameters[0], Boolean.parseBoolean(parameters[1]), parameters[2], parameters[3], Enum.valueOf(ImageExtension.class, parameters[4]));
    }

    public String getUrl() {
        final StringBuilder sb = new StringBuilder();

        sb.append(secured() ? "https://" : "http://");
        sb.append(website());
        sb.append(path());
        sb.append(name());
        sb.append(extension());

        return sb.toString();
    }
}
