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

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javafx.scene.image.Image;

import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.builder.AbstractResourceBuilder;
import org.jrebirth.af.core.resource.provided.JRebirthImages;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ImageBuilder</strong>.
 *
 * Class used to manage images with weak reference.
 *
 * @author Sébastien Bordes
 */
public final class ImageBuilder extends AbstractResourceBuilder<ImageItem, ImageParams, Image> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageBuilder.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected Image buildResource(final ImageItem imageItem, final ImageParams jrImage) {
        Image image = null;
        if (jrImage instanceof RelImage) {
            // Build the requested relative image
            image = buildLocalImage((RelImage) jrImage, false);
        } else if (jrImage instanceof AbsImage) {
            // Build the requested absolute image
            image = buildLocalImage((AbsImage) jrImage, true);
        } else if (jrImage instanceof WebImage) {
            // Build the requested web image
            image = buildWebImage((WebImage) jrImage);
        }
        
        // Try to get the default image when an image is not found
        if (image == null && !JRebirthParameters.NOT_AVAILABLE_IMAGE_NAME.equals(jrImage.name())) {
            // Return the default image
            image = JRebirthImages.NOT_AVAILABLE.get();
        }
        
        // Default image was not found
        if(image == null){
            //Build one programmatically TODO
        }
        
        return image;
    }

    /**
     * Build a local image with its local path.
     *
     * @param jrImage the local image params
<<<<<<< 8.x
     *
=======
     * @param skipImagesFolder skip imagesFolder prefix addition
     * 
>>>>>>> 895028c Fix #122 Manage multiple loation for images, fonts and style Manage localimage with relative and absolute resource params
     * @return the JavaFX image object
     */
    private Image buildLocalImage(final AbstractBaseImage jrImage, final boolean skipImagesFolder) {
        final StringBuilder sb = new StringBuilder();

        if (jrImage.path() != null && !jrImage.path().isEmpty()) {
            sb.append(jrImage.path()).append(Resources.PATH_SEP);
        }

        sb.append(jrImage.name());

        if (jrImage.extension() != null) {
            sb.append(jrImage.extension());
        }
        return loadImage(sb.toString(), skipImagesFolder);
    }
    
    /**
     * Build a web image with its url parameters.
     *
     * @param jrImage the web image params
     *
     * @return the JavaFX image object
     */
    private Image buildWebImage(final WebImage jrImage) {

        final String url = jrImage.getUrl();

        Image image = null;
        if (url == null || url.isEmpty()) {
            LOGGER.error("Image : {} not found !", url);
        } else {
            image = new Image(url);
        }
        return image;
    }

    /**
     * Load an image.
     *
     * @param resourceName the name of the image, path must be separated by '/'
     *
     * @param skipImagesFolder skip imagesFolder prefix addition
     * 
     * @return the image loaded
     */
    private Image loadImage(final String resourceName, final boolean skipImagesFolder) {
        Image image = null;
        
        List<String> imagePaths = (skipImagesFolder) ? Collections.singletonList("") : JRebirthParameters.IMAGE_FOLDER.get();
        for(int i = 0 ; i < imagePaths.size() && image == null ;i++){
            
            String imagePath = imagePaths.get(i);
            if(!imagePath.isEmpty()){
                imagePath += Resources.PATH_SEP;
            }
            final InputStream imageInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(imagePath + resourceName);
            if (imageInputStream != null) {
                image = new Image(imageInputStream);
            }
        }
        if (image == null) {
            LOGGER.error("Image : {} not found into base folder: {}", resourceName, JRebirthParameters.IMAGE_FOLDER.get());
        }
        return image;
    }

}
