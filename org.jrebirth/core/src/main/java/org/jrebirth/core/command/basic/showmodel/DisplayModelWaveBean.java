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
package org.jrebirth.core.command.basic.showmodel;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.WaveBean;

/**
 * The class <strong>DisplayModelWaveBean</strong>.
 * 
 * @author Sébastien Bordes
 */
public class DisplayModelWaveBean implements WaveBean {

    /** The show model key. */
    private UniqueKey showModelKey;

    /** The hide model key. */
    private UniqueKey hideModelKey;

    /** The model key parts. */
    private List<Object> keyPart;

    /** The unique place holder, in example the centerProperty of a BorderPane. */
    private ObjectProperty<Node> uniquePlaceHolder;

    /** The Children list of a parent pane. */
    private ObservableList<Node> chidrenPlaceHolder;

    /** Flag that indicates if the child node must be added at the end (true) or at the beginning (false). */
    private boolean appendChild = true;

    /** The model instance created to show. */
    private Model showModel;

    /** The model instance to hide (ie: with an animation). */
    private Model hideModel;

    /**
     * Gets the show model key.
     * 
     * @return the show model key
     */
    public UniqueKey getShowModelKey() {
        return this.showModelKey;
    }

    /**
     * Sets the show model key.
     * 
     * @param showModelKey the new show model key
     */
    public void setShowModelKey(final UniqueKey showModelKey) {
        this.showModelKey = showModelKey;
    }

    /**
     * Gets the hide model key.
     * 
     * @return the hide model key
     */
    public UniqueKey getHideModelKey() {
        return hideModelKey;
    }

    /**
     * Sets the hide model key.
     * 
     * @param hideModelKey the new hide model key
     */
    public void setHideModelKey(UniqueKey hideModelKey) {
        this.hideModelKey = hideModelKey;
    }

    /**
     * Gets the key part.
     * 
     * @return Returns the keyPart.
     */
    public List<Object> getKeyPart() {
        if (this.keyPart == null) {
            this.keyPart = new ArrayList<>();
        }
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
     * Sets the children place holder.
     * 
     * @param chidrenPlaceHolder The chidrenPlaceHolder to set.
     */
    public void setChidrenPlaceHolder(final ObservableList<Node> chidrenPlaceHolder) {
        this.chidrenPlaceHolder = chidrenPlaceHolder;
    }

    /**
     * Checks if is flag that indicates if the child node must be added at the end (true) or at the beginning (false).
     * 
     * @return Returns the appendChild.
     */
    public boolean isAppendChild() {
        return this.appendChild;
    }

    /**
     * Sets the flag that indicates if the child node must be added at the end (true) or at the beginning (false).
     * 
     * @param appendChild The appendChild to set.
     */
    public void setAppendChild(final boolean appendChild) {
        this.appendChild = appendChild;
    }

    /**
     * Gets the created node.
     * 
     * @return the created node
     */
    public Model getShowModel() {
        return this.showModel;
    }

    /**
     * Sets the created node.
     * 
     * @param model the new created node
     */
    public void setShowModel(final Model model) {
        this.showModel = model;
    }

    /**
     * Gets the model instance to hide (ie: with an animation).
     * 
     * @return the model instance to hide (ie: with an animation)
     */
    public Model getHideModel() {
        return hideModel;
    }

    /**
     * Sets the model instance to hide (ie: with an animation).
     * 
     * @param hideModel the new model instance to hide (ie: with an animation)
     */
    public void setHideModel(Model hideModel) {
        this.hideModel = hideModel;
    }
}
