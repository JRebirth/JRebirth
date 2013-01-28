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
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

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

    /** . */
    private final Map<String, Object> parametersMap = new ConcurrentHashMap<>();

    /**
     * Default Constructor
     */
    public ParameterBuilder() {
        super();

        // Search and analyze all properties files available
        readPropertiesFiles();
    }

    /**
     * TODO To complete.
     */
    private void readPropertiesFiles() {

        final FilenameFilter configFileFilter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name != null && name.endsWith("jrebirth.properties");
            }
        };

        final File configFolder = new File("config");
        if (configFolder.exists()) {

            final File[] configFiles = configFolder.listFiles(configFileFilter);
            final List<File> cfList = new ArrayList<>(Arrays.asList(configFiles));

            // Sort configuration File to allow to override default configuration
            Collections.sort(cfList, new Comparator<File>() {

                @Override
                public int compare(final File file1, final File file2) {
                    return file1.getName().compareTo(file2.getName());
                }
            });

            for (final File cf : configFiles) {
                readPropertiesFile(cf);
            }
        }

    }

    /**
     * TODO To complete.
     * 
     * @param cf
     */
    private void readPropertiesFile(final File cf) {
        final Properties p = new Properties();

        try (InputStream is = new FileInputStream(cf)) {

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
            e.printStackTrace();
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

            object = op.object();// Build the requested parameter

            if (op.name() != null && PARAMETERS.containsKey(op.name())) {

                final Object configured = op.parseObject(PARAMETERS.getObject(op.name()).toString());

                this.parametersMap.put(op.name(), configured);
            }
        }

        return object;
    }
}
