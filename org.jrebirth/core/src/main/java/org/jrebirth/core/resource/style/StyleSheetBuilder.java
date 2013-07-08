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
package org.jrebirth.core.resource.style;

import java.net.URL;

import org.jrebirth.core.resource.factory.AbstractResourceBuilder;
import org.jrebirth.core.resource.provided.JRebirthParameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>StyleSheetBuilder</strong>.
 * 
 * Class used to manage style sheet with weak reference.
 * 
 * @author Sébastien Bordes
 */
public final class StyleSheetBuilder extends AbstractResourceBuilder<StyleSheetItem, StyleSheetParams, URL> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StyleSheetBuilder.class);

    /** The css file extension. */
    private static final String CSS_EXT = ".css";

    /** The class path separator. */
    private static final String PATH_SEP = "/";

    /**
     * {@inheritDoc}
     */
    @Override
    protected URL buildResource(final StyleSheetParams ssp) {
        URL cssURL = null;
        if (ssp instanceof StyleSheet) {
            // Build the requested font
            cssURL = buildStyleSheetUrl((StyleSheet) ssp);
        }
        if (cssURL == null) {
            // Return the default image

        }
        return cssURL;
    }

    /**
     * Build a local image with its local path.
     * 
     * @param ss the local image params
     * 
     * @return the JavaFX image object
     */
    private URL buildStyleSheetUrl(final StyleSheet ss) {

        final StringBuilder sb = new StringBuilder();

        sb.append(JRebirthParameters.STYLE_FOLDER.get()).append(PATH_SEP);

        if (!ss.path().isEmpty()) {
            sb.append(ss.path()).append(PATH_SEP);
        }

        sb.append(ss.name());

        if (!ss.name().endsWith(CSS_EXT)) {
            sb.append(CSS_EXT);
        }

        return buildUrl(sb.toString());
    }

    /**
     * Get a style sheet URL.
     * 
     * @param styleSheetPath the path of the style sheet, path must be separated by '/'
     * 
     * @return the image loaded
     */
    private URL buildUrl(final String styleSheetPath) {

        final URL cssResource = Thread.currentThread().getContextClassLoader().getResource(styleSheetPath);

        if (cssResource == null) {
            LOGGER.error("Style Sheet : " + styleSheetPath + " not found !");
        }
        return cssResource;
    }

}
