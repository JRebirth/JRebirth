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
package org.jrebirth.af.api.ui.fxml;

import javafx.scene.Node;

/**
 * The class <strong>FXMLComponent</strong>.
 *
 * @author Sébastien Bordes
 */
public interface FXMLComponent {

    /**
     * @return Returns the node.
     */
    Node getNode();

    /**
     * Return the controller or null.
     *
     * @return Returns the controller.
     */
    FXMLController getController();

}
