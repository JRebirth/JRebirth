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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.jrebirth.core.resource.factory.AbstractResourceBuilder;
import org.jrebirth.core.util.ClasspathUtility;
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

    /** Store all parameter values. */
    private final Map<String, Object> parametersMap = new ConcurrentHashMap<>();

    /** The file extension used by configuration files. */
    private String configurationFileExtension;

    /** The Wildcard used to load configuration files. */
    private String configurationFileWildcard;

    /**
     * Default Constructor.
     */
    public ParameterBuilder() {
        super();
    }

    /**
     * Search configuration files according to the parameters provided.
     * 
     * @param wildcard the regex wildcard (must not be null)
     * @param extension the file extension without the first dot (ie: properties) (must not be null)
     */
    public void searchConfigurationFiles(final String wildcard, final String extension) {

        // Store parameters
        this.configurationFileWildcard = wildcard;
        this.configurationFileExtension = extension;

        // Search and analyze all properties files available
        readPropertiesFiles();
    }

    /**
     * Read all configuration files available into the application classpath.
     */
    private void readPropertiesFiles() {

        if (!this.configurationFileWildcard.isEmpty() && !this.configurationFileExtension.isEmpty()) {
            // Assemble the regex pattern
            final Pattern filePattern = Pattern.compile(this.configurationFileWildcard + "\\." + this.configurationFileExtension);

            // Retrieve all resources from default classpath
            final Collection<String> list = ClasspathUtility.getClasspathResources(filePattern);

            LOGGER.info("{} configuration file{} found.", list.size(), list.size() > 1 ? "s" : "");

            for (final String cf : list) {
                readPropertiesFile(new File(cf));
            }
        } else {
            LOGGER.info("Configuration Loading is skipped");
        }
    }

    /**
     * Read a customized configuration file to load parameters values.
     * 
     * @param custConfFile the file to load
     */
    private void readPropertiesFile(final File custConfFile) {

        final Properties p = new Properties();

        LOGGER.info("Read configuration file : {} ", custConfFile.getAbsolutePath());

        try (InputStream is = new FileInputStream(custConfFile)) {

            // Read the properties file
            p.load(is);

            for (final Object key : p.keySet()) {
                if (this.parametersMap.containsKey(key)) {
                    LOGGER.trace("Update key {} with value= {}", key, p.get(key));
                } else {
                    LOGGER.trace("Store key {} with value= {}", key, p.get(key));
                }
                this.parametersMap.put(key.toString(), p.get(key));

            }

        } catch (final IOException e) {
            LOGGER.error("Impossible to read the properties file : {}", custConfFile.getAbsolutePath());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object buildResource(final ParameterParams parameterParams) {
        Object object = null;
        if (parameterParams instanceof ObjectParameter) {

            final ObjectParameter<?> op = (ObjectParameter<?>) parameterParams;

            // Check if the parameter has a parameter name and
            // check if the parameter has been loaded from any customized configuration file
            if (op.name() != null && this.parametersMap.containsKey(op.name())) {

                // Retrieve the customized parameter
                object = op.parseObject(this.parametersMap.get(op.name()).toString());

            } else {
                // No customized parameter has been loaded, gets the default programmatic one
                object = op.object();
            }

            // Don't store the parameter into the map if it hasn't got any parameter name
            if (op.name() != null) {
                // Store the new parameter into the map
                this.parametersMap.put(op.name(), object);
            }
        }
        return object;
    }
}
