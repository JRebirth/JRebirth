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
package org.jrebirth.core.resource.parameter;

import java.util.ResourceBundle;

import org.jrebirth.core.resource.factory.AbstractResourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>FontBuilder</strong>.
 * 
 * Class used to manage font with weak reference.
 * 
 * @author Sébastien Bordes
 */
public final class ParameterBuilder extends AbstractResourceBuilder<ParameterItem, ParameterParams, Object> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterBuilder.class);

    /** The jrebirth bundle name. */
    private static final String BUNDLE_NAME = "jrebirth";

    /** The Resources bundle built. */
    private static final ResourceBundle PARAMETERS = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Default Constructor
     */
    public ParameterBuilder() {
        super();

        // TODO Search All configuration file
        readPropertiesFile();
    }

    /**
     * TODO To complete.
     */
    private void readPropertiesFile() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object buildResource(final ParameterParams parameterParams) {
        final Object object = null;
        if (parameterParams instanceof ObjectParameter) {
            // Build the requested parameter

        }

        return object;
    }

}
