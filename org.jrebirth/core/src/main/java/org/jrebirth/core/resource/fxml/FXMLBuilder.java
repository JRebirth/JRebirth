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
package org.jrebirth.core.resource.fxml;

import java.net.URL;

import org.jrebirth.core.resource.factory.AbstractResourceBuilder;
import org.jrebirth.core.ui.fxml.FXMLComponent;
import org.jrebirth.core.ui.fxml.FXMLUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>StyleSheetBuilder</strong>.
 * 
 * Class used to manage style sheet with weak reference.
 * 
 * @author Sébastien Bordes
 */
public final class FXMLBuilder extends AbstractResourceBuilder<FXMLItem, FXMLParams, FXMLComponent> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLBuilder.class);

    /** The css file extension. */
    private static final String FXML_EXT = ".fxml";

    /**
     * {@inheritDoc}
     */
    @Override
    protected FXMLComponent buildResource(final FXMLParams fp) {
        FXMLComponent component = null;
        if (fp instanceof FXML) {
            // Build the requested font
            component = buildFXMLComponent((FXML) fp);
        }
        if (component == null) {
            // Return the default ...

        }
        return component;
    }

    /**
     * Build a FXML component taht embed a node and its FXML controller.
     * 
     * @param fxmlParam the fxmlparams
     * 
     * @return the JavaFX image object
     */
    private FXMLComponent buildFXMLComponent(final FXML fxmlParam) {

        final StringBuilder sb = new StringBuilder();

        // sb.append(JRebirthParameters.STYLE_FOLDER.get()).append(Resources.PATH_SEP);
        //
        // if (!fp.path().isEmpty()) {
        // sb.append(fp.path()).append(Resources.PATH_SEP);
        // }
        //
        // sb.append(fp.name());
        //
        // if (!fp.name().endsWith(FXML_EXT)) {
        // sb.append(FXML_EXT);
        // }

        return FXMLUtils.loadFXML(null, sb.toString());
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
