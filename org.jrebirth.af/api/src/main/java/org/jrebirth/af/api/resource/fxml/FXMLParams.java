/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.api.resource.fxml;

import org.jrebirth.af.api.resource.ResourceParams;

/**
 * The class <strong>FXMLParams</strong>.
 *
 * @author Sébastien Bordes
 */
public interface FXMLParams extends ResourceParams {

    /**
     * Gets the absolute path for fxml (and for resource bundle if bundle path is null).
     *
     * @return the absolute path
     */
    String absolutePath();

    /**
     * Gets the FXML file name (and resource bundle file name if bundle name is null).
     *
     * @return the fxml file name
     */
    String fxmlName();

    /**
     * Gets the absolute path for resource bundle.
     *
     * @return the absolute path for resource bundle
     */
    String absoluteBundlePath();

    /**
     * Gets the resource bundle file name (without suffix).
     *
     * @return the resource bundle file name
     */
    String bundleName();

    /**
     * Return the full path of the fxml file without .fxml extension.
     *
     * @return the full fxml path
     */
    String getFxmlPath();

    /**
     * Return the full path of the resource bundle file without .properties extension.
     *
     * @return the full resource bundle path
     */
    String getBundlePath();
}
