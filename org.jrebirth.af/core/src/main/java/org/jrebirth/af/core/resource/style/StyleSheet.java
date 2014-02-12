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
package org.jrebirth.af.core.resource.style;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.jrebirth.af.core.resource.AbstractBaseParams;

/**
 * The interface <strong>StyleSheet</strong>.
 * 
 * @author Sébastien Bordes
 */
public class StyleSheet extends AbstractBaseParams implements StyleSheetParams {

    /** The style sheet path. */
    private final StringProperty pathProperty = new SimpleStringProperty(""); // Avoid NPE for path

    /** The style sheet name (without .css extension). */
    private final StringProperty nameProperty = new SimpleStringProperty();

    /**
     * Default Constructor.
     * 
     * @param path the style sheet local path
     * @param name the style sheet name
     */
    public StyleSheet(final String path, final String name) {
        super();
        this.pathProperty.set(path);
        this.nameProperty.set(name);
    }

    /**
     * Default Constructor.
     * 
     * @param name the style sheet file name
     */
    public StyleSheet(final String name) {
        this("", name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String path() {
        return this.pathProperty.get();
    }

    /**
     * Path property.
     * 
     * @return the string property
     */
    public StringProperty pathProperty() {
        return this.pathProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return this.nameProperty.get();
    }

    /**
     * Name property.
     * 
     * @return the string property
     */
    public StringProperty nameProperty() {
        return this.nameProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        append(sb, path());
        append(sb, name());

        return cleanString(sb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {
        if (parameters.length >= 1) {
            pathProperty().set(parameters[0]);
        }
        if (parameters.length == 2) {
            nameProperty().set(parameters[1]);
        }
    }

}
