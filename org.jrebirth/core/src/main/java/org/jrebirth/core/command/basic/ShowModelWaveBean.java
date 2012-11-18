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
package org.jrebirth.core.command.basic;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.WaveBean;

/**
 * The class <strong>ShowModelWaveBean</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ShowModelWaveBean implements WaveBean {

    /** The model class. */
    private Class<? extends Model> modelClass;

    /** The parent node. */
    private Pane parentNode;

    /** The created node. */
    private Node createdNode;

    /**
     * Gets the model class.
     * 
     * @return the model class
     */
    public Class<? extends Model> getModelClass() {
        return this.modelClass;
    }

    /**
     * Sets the model class.
     * 
     * @param modelClass the new model class
     */
    public void setModelClass(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }

    /**
     * Gets the parent node.
     * 
     * @return the parent node
     */
    public Pane getParentNode() {
        return this.parentNode;
    }

    /**
     * Sets the parent node.
     * 
     * @param parentNode the new parent node
     */
    public void setParentNode(final Pane parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * Gets the created node.
     * 
     * @return the created node
     */
    public Node getCreatedNode() {
        return this.createdNode;
    }

    /**
     * Sets the created node.
     * 
     * @param createdNode the new created node
     */
    public void setCreatedNode(final Node createdNode) {
        this.createdNode = createdNode;
    }
}
