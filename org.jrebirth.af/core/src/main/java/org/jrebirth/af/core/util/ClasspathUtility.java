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
package org.jrebirth.af.core.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;

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

        final String[] classpathEntries = CLASSPATH.split(CLASSPATH_SEPARATOR);
        for (final String classpathEntry : classpathEntries) {
            // Parse the classpath entry and apply the given pattern as filter
            resources.addAll(getResources(classpathEntry, searchPattern));
        }
        // Sort resources
        Collections.sort(resources);

        return resources;
    }

    /**
     * Search all files that match the given Regex pattern.
     * 
     * @param classpathEntryPath the root folder used for search
     * @param searchPattern the regex pattern used as a filter
     * 
     * @return list of resources that match the pattern
     */
    private static List<String> getResources(final String classpathEntryPath, final Pattern searchPattern) {
        final List<String> resources = new ArrayList<>();
        final File classpathEntryFile = new File(classpathEntryPath);
        // The classpath entry could be a jar or a folder
        if (classpathEntryFile.isDirectory()) {
            // Browse the folder content
            resources.addAll(getResourcesFromDirectory(classpathEntryFile, searchPattern));
        } else if (classpathEntryFile.getName().endsWith(".jar") || classpathEntryFile.getName().endsWith(".zip")) {
            // Explode and browse jar|zip content
            resources.addAll(getResourcesFromJarOrZipFile(classpathEntryFile, searchPattern));
        } else {
            LOGGER.log(RESOURCE_IGNORED, classpathEntryFile.getAbsolutePath());
        }
        return resources;
    }

    /**
     * Browse a directory to search resources that match the pattern.
     * 
     * @param directory the root directory to browse
     * @param searchPattern the regex pattern used as a filter
     * 
     * @return list of resources that match the pattern
     */
    private static List<String> getResourcesFromDirectory(final File directory, final Pattern searchPattern) {
        final List<String> resources = new ArrayList<>();

        // Filter only properties files
        final File[] fileList = directory.listFiles();

        // Iterate over each relevant file
        for (final File file : fileList) {
            // If the file is a directory process a recursive call to explorer the tree
            if (file.isDirectory()) {
                resources.addAll(getResourcesFromDirectory(file, searchPattern));
            } else {
                try {
                    checkResource(resources, searchPattern, file.getCanonicalPath());
                } catch (final IOException e) {
                    LOGGER.log(BAD_CANONICAL_PATH, e);
                }
            }
        }
        return resources;
    }

    /**
     * Browse the jar content to search resources that match the pattern.
     * 
     * @param jarOrZipFile the jar to explore
     * @param searchPattern the regex pattern used as a filter
     * 
     * @return list of resources that match the pattern
     */
    @SuppressWarnings("unchecked")
    private static List<String> getResourcesFromJarOrZipFile(final File jarOrZipFile, final Pattern searchPattern) {
        final List<String> resources = new ArrayList<>();

        try (ZipFile zf = new ZipFile(jarOrZipFile);) {

            final Enumeration<ZipEntry> e = (Enumeration<ZipEntry>) zf.entries();
            while (e.hasMoreElements()) {
                final ZipEntry ze = e.nextElement();
                checkResource(resources, searchPattern, ze.getName());
            }

        } catch (final IOException e) {
            LOGGER.log(FILE_UNREADABLE, e);
        }
        return resources;
    }

    /**
     * Check if the resource match the regex.
     * 
     * @param resources the list of found resources
     * @param searchPattern the regex pattern
     * @param resourceName the resource to check and to add
     */
    private static void checkResource(final List<String> resources, final Pattern searchPattern, final String resourceName) {
        if (searchPattern.matcher(resourceName).matches()) {
            resources.add(resourceName);
        }
    }
}
