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

import org.jrebirth.core.resource.AbstractBaseParams;

/**
 * The interface <strong>StyleSheet</strong>.
 * 
 * @author Sébastien Bordes
 */
public class StyleSheet extends AbstractBaseParams implements StyleSheetParams {

    /** The style sheet path. */
    private final String path;

    /** The style sheet name (without .css extension). */
    private final String name;

    /**
     * Default Constructor.
     * 
     * @param path the style sheet local path
     * @param name the style sheet name
     */
    public StyleSheet(final String path, final String name) {
        super();
        this.path = path;
        this.name = name;
    }

    /**
     * Default Constructor.
     * 
     * @param path the image local path
     */
    public StyleSheet(final String name) {
        this("", name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String path() {
        // Avoid NPE on path
        return this.path == null ? "" : this.path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(path()).append(PARAMETER_SEPARATOR);
        sb.append(name());

        return sb.toString();
    }

    /**
     * .
     * 
     * @param localPath
     * @return
     */
    public static StyleSheet parseStyleSheet(final String serializedStyleSheet) {

        final String[] parameters = serializedStyleSheet.split(PARAMETER_SEPARATOR);
        if (parameters.length > 1) {
            return new StyleSheet(parameters[0], parameters[1]);
        } else {
            return new StyleSheet(parameters[0]);
        }
    }
}
