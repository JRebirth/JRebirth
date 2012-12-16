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

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.WaveBean;

/**
 * The class <strong>DisplayModelWaveBean</strong>.
 * 
 * @author Sébastien Bordes
 */
public class DisplayModelWaveBean implements WaveBean {

    /** The model class. */
    private Class<? extends Model> modelClass;

    /** The model key parts. */
    private List<Object> keyPart;

    /** The unique place holder, in example the centerProperty of a BorderPane. */
    private ObjectProperty<Node> uniquePlaceHolder;

    /** The Children list of a parent pane. */
    private ObservableList<Node> chidrenPlaceHolder;

    /** The created node. */
    private Node createdNode;

    /** The model instance created. */
    private Model model;

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
     * Gets the key part.
     * 
     * @return Returns the keyPart.
     */
    public List<Object> getKeyPart() {
        return this.keyPart;
    }

    /**
     * Sets the key part.
     * 
     * @param keyPart The keyPart to set.
     */
    public void setKeyPart(final List<Object> keyPart) {
        this.keyPart = keyPart;
    }

    /**
     * Gets the unique place holder.
     * 
     * @return Returns the uniquePlaceHolder.
     */
    public ObjectProperty<Node> getUniquePlaceHolder() {
        return this.uniquePlaceHolder;
    }

    /**
     * Sets the unique place holder.
     * 
     * @param uniquePlaceHolder The uniquePlaceHolder to set.
     */
    public void setUniquePlaceHolder(final ObjectProperty<Node> uniquePlaceHolder) {
        this.uniquePlaceHolder = uniquePlaceHolder;
    }

    /**
     * Gets the chidren place holder.
     * 
     * @return Returns the chidrenPlaceHolder.
     */
    public ObservableList<Node> getChidrenPlaceHolder() {
        return this.chidrenPlaceHolder;
    }

    /**
     * Sets the chidren place holder.
     * 
     * @param chidrenPlaceHolder The chidrenPlaceHolder to set.
     */
    public void setChidrenPlaceHolder(final ObservableList<Node> chidrenPlaceHolder) {
        this.chidrenPlaceHolder = chidrenPlaceHolder;
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

    /**
     * Gets the model.
     * 
     * @return the model
     */
    public Model getModel() {
        return this.model;
    }

    /**
     * Sets the model.
     * 
     * @param model the new model
     */
    public void setModel(final Model model) {
        this.model = model;
    }
}
