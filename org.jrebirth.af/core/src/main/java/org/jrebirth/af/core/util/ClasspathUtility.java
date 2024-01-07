/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

/**
 * The class <strong>ClassUtility</strong>.
 *
 * Some Useful class utilities to perform introspection.
 *
 * @author Sébastien Bordes
 */
public final class ClasspathUtility implements UtilMessages {

    /** The separator used for serialization. */
    public static final String SEPARATOR = "|";

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(ClasspathUtility.class);

    /** The classpath of the current java executable or current folder if not defined. */
    private static final String CLASSPATH = System.getProperty("java.class.path", ".");

    /** String to separate all classpath entries. */
    private static final String CLASSPATH_SEPARATOR = System.getProperty("path.separator");

    /**
     * Private Constructor.
     */
    private ClasspathUtility() {
        // Nothing to do
    }

    /**
     * Retrieve all resources that match the search pattern from the java.class.path.
     *
     * @param searchPattern the pattern used to filter all matching files
     *
     * @return Sorted list of resources that match the pattern
     */
    public static Collection<String> getClasspathResources(final Pattern searchPattern) {

        final List<String> resources = new ArrayList<>();

        try (ScanResult scanResult = new ClassGraph()
                                                     .whitelistPaths("/").scan()) {
            resources.addAll(scanResult.getResourcesMatchingPattern(searchPattern).asMap().keySet());
        }
        // Sort resources
        Collections.sort(resources);

        return resources;
    }

    /**
     * TRy to load a custom resource file.
     *
     * @param custConfFileName the custom resource file to load
     *
     * @return the load input stream
     */
    public static InputStream loadInputStream(final String custConfFileName) {
        InputStream is = null;
        
        ClassLoader cl = ModuleLayer.boot().findLoader("org.jrebirth.af.core");
        
        final File resourceFile = new File(custConfFileName);
        // Check if the file could be find
        if (resourceFile.exists()) {
            try {
                is = new FileInputStream(resourceFile);
            } catch (final FileNotFoundException e) {
                // Nothing to do
            }
        } else {
            // Otherwise try to load from context classloader
            is = cl.getResourceAsStream(custConfFileName);
        }

        return is;
    }
}
