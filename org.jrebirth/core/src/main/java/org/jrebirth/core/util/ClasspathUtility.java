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
package org.jrebirth.core.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ClassUtility</strong>.
 * 
 * Some Useful class utilities to perform introspection.
 * 
 * @author Sébastien Bordes
 */
public final class ClasspathUtility {

    /** The separator used for serialization. */
    public static final String SEPARATOR = "|";

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClasspathUtility.class);

    private static final FilenameFilter configFileFilter = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            return name != null && name.endsWith(".properties");
        }
    };

    /**
     * Private Constructor.
     */
    private ClasspathUtility() {
        // Nothing to do
    }

    /**
     * for all elements of java.class.path get a Collection of resources Pattern pattern = Pattern.compile(".*"); gets all resources
     * 
     * @param pattern the pattern to match
     * @return the resources in the order they are found
     */
    public static Collection<String> getResources(final Pattern pattern) {
        final ArrayList<String> retval = new ArrayList<String>();
        final String classPath = System.getProperty("java.class.path", ".");
        final String classPathSeparator = System.getProperty("path.separator");

        final String[] classPathElements = classPath.split(classPathSeparator);
        for (final String element : classPathElements) {
            retval.addAll(getResources(element, pattern));
        }
        return retval;
    }

    private static Collection<String> getResources(final String element, final Pattern pattern) {
        final ArrayList<String> retval = new ArrayList<String>();
        final File file = new File(element);
        if (file.isDirectory()) {
            retval.addAll(getResourcesFromDirectory(file, pattern));
        } else {
            retval.addAll(getResourcesFromJarFile(file, pattern));
        }
        return retval;
    }

    private static Collection<String> getResourcesFromJarFile(final File file, final Pattern pattern) {
        final ArrayList<String> retval = new ArrayList<String>();
        ZipFile zf;
        try {
            zf = new ZipFile(file);
        } catch (final ZipException e) {
            throw new Error(e);
        } catch (final IOException e) {
            throw new Error(e);
        }
        final Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            final ZipEntry ze = (ZipEntry) e.nextElement();
            final String fileName = ze.getName();
            final boolean accept = pattern.matcher(fileName).matches();
            if (accept) {
                retval.add(fileName);
            }
        }
        try {
            zf.close();
        } catch (final IOException e1) {
            throw new Error(e1);
        }
        return retval;
    }

    private static Collection<String> getResourcesFromDirectory(final File directory, final Pattern pattern) {
        final ArrayList<String> retval = new ArrayList<String>();
        final File[] fileList = directory.listFiles(configFileFilter);

        for (final File file : fileList) {
            if (file.isDirectory()) {
                retval.addAll(getResourcesFromDirectory(file, pattern));
            } else {
                try {
                    final String fileName = file.getCanonicalPath();
                    final boolean accept = pattern.matcher(fileName).matches();
                    if (accept) {
                        retval.add(fileName);
                    }
                } catch (final IOException e) {
                    throw new Error(e);
                }
            }
        }
        return retval;
    }

    /**
     * list the resources that match args[0]
     * 
     * @param args args[0] is the pattern to match, or list all resources if there are no args
     */
    public static void main(final String[] args) {
        Pattern pattern;
        if (args.length < 1) {
            pattern = Pattern.compile(".*");
        } else {
            pattern = Pattern.compile(args[0]);
        }

    }
}
