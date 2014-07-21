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

import java.util.Arrays;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    
    /** The flag used to skip the fontsFolder prefix addition. */
    private BooleanProperty skipStylesFolderProperty = new SimpleBooleanProperty();

    /**
     * Default Constructor.
     * 
     * @param path the style sheet local path
     * 
     * @param name the style sheet name
     * @param skipStylesFolder skip fontsFolder usage
     */
    public StyleSheet(final String path, final String name, final boolean skipStylesFolder) {
        super();
        this.pathProperty.set(path);
        this.nameProperty.set(name);
        this.skipStylesFolderProperty.set(skipStylesFolder);
    }
    
    /**
     * Default Constructor.
     *
     * @param path the style sheet local path
     * @param name the style sheet name
     */
    public StyleSheet(final String path, final String name) {
        this(path, name, false);
    }

    /**
     * Default Constructor.
     *
     * @param name the style sheet file name
     */
    public StyleSheet(final String name) {
        this("", name, false);
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
     * Return the skipStylesFolder flag.
     * 
     * @return the skipStylesFolder flag
     */
    public boolean skipStylesFolder() {
        return this.skipStylesFolderProperty.get();
    }

    /**
     * Return the skipStylesFolderProperty property.
     * 
     * @return the skipStylesFolderProperty property
     */
    public BooleanProperty skipStylesFolderProperty() {
        return skipStylesFolderProperty;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {
        if (parameters.length >= 1) {
            pathProperty().set(parameters[0]);
        }
        if (parameters.length >= 2) {
            nameProperty().set(parameters[1]);
        }
        if (parameters.length == 3) {
            skipStylesFolderProperty().set(readBoolean(parameters[2]));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(path(), name(), skipStylesFolder());
    }

}
