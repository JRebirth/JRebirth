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

import org.jrebirth.core.resource.AbstractBaseParams;

/**
 * The interface <strong>FXML</strong>.
 * 
 * @author Sébastien Bordes
 */
public class FXML extends AbstractBaseParams implements FXMLParams {

    /** The absolute path used to retrieved fxml file and resource bundle. */
    private final String absolutePath;

    /** The absolute path used to retrieved resource bundle file if different of fxml one. */
    private final String absoluteBundlePath;

    /** The FXML file name (without .fxml extension). */
    private final String fxmlName;

    /** The FXML resource bundle name (without .properties extension). */
    private final String bundleName;

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
        this.absolutePath = fxmlPath;
        this.fxmlName = fxmlName;
        this.absoluteBundlePath = bundlePath;
        this.bundleName = bundleName;
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
        // Avoid NPE on path
        return this.absolutePath == null ? "" : this.absolutePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String fxmlName() {
        return this.fxmlName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String absoluteBundlePath() {
        return this.absoluteBundlePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String bundleName() {
        return this.bundleName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(absolutePath()).append(PARAMETER_SEPARATOR);
        sb.append(fxmlName()).append(PARAMETER_SEPARATOR);
        sb.append(absoluteBundlePath()).append(PARAMETER_SEPARATOR);
        sb.append(bundleName());

        return sb.toString();
    }

    /**
     * Parse the serialized fxml string to build a fresh instance.
     * 
     * @param serializedFXML the serialized string
     * 
     * @return a new fresh instance of {@link FXML}
     */
    public static FXML parseFXML(final String serializedFXML) {

        final String[] parameters = serializedFXML.split(PARAMETER_SEPARATOR);

        FXML fxml = null;

        switch (parameters.length) {
            case 3:
                fxml = new FXML(parameters[0], parameters[1]);
                break;
            case 4:
                fxml = new FXML(parameters[0], parameters[1], parameters[2], parameters[3]);
                break;
            case 1:
            default:
                fxml = new FXML(parameters[0]);
        }

        return fxml;
    }
}
