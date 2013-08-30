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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.jrebirth.core.resource.AbstractBaseParams;
import org.jrebirth.core.resource.Resources;

/**
 * The interface <strong>FXML</strong>.
 * 
 * @author Sébastien Bordes
 */
public class FXML extends AbstractBaseParams implements FXMLParams {

    /** The absolute path used to retrieved fxml file and resource bundle. */
    private final StringProperty absolutePath = new SimpleStringProperty(""); // Avoid NPE for absolute path

    /** The absolute path used to retrieved resource bundle file if different of fxml one. */
    private final StringProperty absoluteBundlePath = new SimpleStringProperty();

    /** The FXML file name (without .fxml extension). */
    private final StringProperty fxmlName = new SimpleStringProperty();

    /** The FXML resource bundle name (without .properties extension). */
    private final StringProperty bundleName = new SimpleStringProperty();

    /**
     * Default Constructor.
     * 
     * @param fxmlPath the fxml file absolute path
     * @param fxmlName the fxml file name
     * @param bundlePath the resource bundle absolute path
     * @param bundleName the resource bundle file name
     */
    public FXML(final String fxmlPath, final String fxmlName, final String bundlePath, final String bundleName) {
        super();
        this.absolutePath.set(fxmlPath);
        this.fxmlName.set(fxmlName);
        this.absoluteBundlePath.set(bundlePath);
        this.bundleName.set(bundleName);
    }

    /**
     * Default Constructor.
     * 
     * @param globalPath the path iused to laod fxml and resource bundle files
     * @param globalName the fxml and resource bundle name (without suffix)
     */
    public FXML(final String globalPath, final String globalName) {
        this(globalPath, globalName, globalPath, globalName);
    }

    /**
     * Default Constructor.
     * 
     * @param name the fxml and resource bundle file name
     */
    public FXML(final String name) {
        this("", name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String absolutePath() {
        return this.absolutePath.get();
    }

    /**
     * Absolute path property.
     * 
     * @return the string property
     */
    public StringProperty absolutePathProperty() {
        return this.absolutePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String fxmlName() {
        return this.fxmlName.get();
    }

    /**
     * Fxml name property.
     * 
     * @return the string property
     */
    public StringProperty fxmlNameProperty() {
        return this.fxmlName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String absoluteBundlePath() {
        return this.absoluteBundlePath.get();
    }

    /**
     * Absolute bundle path property.
     * 
     * @return the string property
     */
    public StringProperty absoluteBundlePathProperty() {
        return this.absoluteBundlePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String bundleName() {
        return this.bundleName.get();
    }

    /**
     * Bundle name property.
     * 
     * @return the string property
     */
    public StringProperty bundleNameProperty() {
        return this.bundleName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFxmlPath() {
        final StringBuilder sb = new StringBuilder();
        if (!absolutePath().isEmpty()) {
            sb.append(absolutePath()).append(Resources.PATH_SEP);
        }
        sb.append(fxmlName());
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBundlePath() {
        final StringBuilder sb = new StringBuilder();
        if (!bundleName().isEmpty()) {
            if (!absoluteBundlePath().isEmpty()) {
                sb.append(absoluteBundlePath()).append(Resources.PATH_SEP);
            }
            sb.append(bundleName());
        } else {
            // Get the fxml path to build the bundle one
            sb.append(getFxmlPath());
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        append(sb, absolutePath());
        append(sb, fxmlName());
        append(sb, absoluteBundlePath());
        append(sb, bundleName());

        return cleanString(sb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {

        switch (parameters.length) {
            case 4:
                absolutePathProperty().set(parameters[0]);
                fxmlNameProperty().set(parameters[1]);
                absoluteBundlePathProperty().set(parameters[2]);
                bundleNameProperty().set(parameters[3]);
                break;

            case 3:
                absolutePathProperty().set(parameters[0]);
                fxmlNameProperty().set(parameters[1]);
                break;

            case 1:
            default:
                fxmlNameProperty().set(parameters[0]);
        }
    }

}
