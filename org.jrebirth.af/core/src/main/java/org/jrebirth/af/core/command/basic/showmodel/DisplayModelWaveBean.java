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
package org.jrebirth.af.core.command.basic.showmodel;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.core.ui.object.ModelConfig;

/**
 * The class <strong>DisplayModelWaveBean</strong>.
 *
 * @author Sébastien Bordes
 */
public class DisplayModelWaveBean implements WaveBean {

    /** The show model key. */
    private UniqueKey<? extends Model> showModelKey;

    /** The show model data. */
    private ModelConfig<? extends Model, ?> showModelData;

    /** The hide model key. */
    private UniqueKey<? extends Model> hideModelKey;

    /** The model key parts. */
    private List<Object> keyPart;

    /** The unique place holder, in example the centerProperty of a BorderPane. */
    private ObjectProperty<Node> uniquePlaceHolder;

    /** The Children list of a parent pane. */
    private ObservableList<Node> childrenPlaceHolder;

    /** Flag that indicates if the child node must be added at the end (true) or at the beginning (false). */
    private boolean appendChild = true;

    /** The model instance created to show. */
    private Model showModel;

    /** The model instance to hide (ie: with an animation). */
    private Model hideModel;

    /**
     * Build a new instance of {@link DisplayModelWaveBean}.
     *
     * @return a fresh instance
     */
    public static DisplayModelWaveBean create() {
        return new DisplayModelWaveBean();
    }

    /**
     * Hide the default constructor but allow subclassing.
     */
    public DisplayModelWaveBean() {
        super();
    }

    /**
     * Gets the show model key.
     *
     * @return the show model key
     */
    public UniqueKey<? extends Model> showModelKey() {
        return this.showModelKey;
    }

    /**
     * Sets the show model key.
     *
     * @param showModelKey the new show model key
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean showModelKey(final UniqueKey<? extends Model> showModelKey) {
        this.showModelKey = showModelKey;
        return this;
    }

    /**
     * Gets the show model data.
     *
     * @return the show model data
     */
    public ModelConfig<? extends Model, ?> showModelData() {
        return this.showModelData;
    }

    /**
     * Sets the show model data.
     *
     * @param showModelData the new show model data
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean showModelData(final ModelConfig<? extends Model, ?> showModelData) {
        this.showModelData = showModelData;
        return this;
    }

    /**
     * Gets the hide model key.
     *
     * @return the hide model key
     */
    public UniqueKey<? extends Model> hideModelKey() {
        return this.hideModelKey;
    }

    /**
     * Sets the hide model key.
     *
     * @param hideModelKey the new hide model key
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean hideModelKey(final UniqueKey<? extends Model> hideModelKey) {
        this.hideModelKey = hideModelKey;
        return this;
    }

    /**
     * Gets the key part.
     *
     * @return Returns the keyPart.
     */
    public List<Object> keyPart() {
        if (this.keyPart == null) {
            this.keyPart = new ArrayList<>();
        }
        return this.keyPart;
    }

    /**
     * Sets the key part.
     *
     * @param keyPart The keyPart to set.
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean keyPart(final List<Object> keyPart) {
        this.keyPart = keyPart;
        return this;
    }

    /**
     * Gets the unique place holder.
     *
     * @return Returns the uniquePlaceHolder.
     */
    public ObjectProperty<Node> uniquePlaceHolder() {
        return this.uniquePlaceHolder;
    }

    /**
     * Sets the unique place holder.
     *
     * @param uniquePlaceHolder The uniquePlaceHolder to set.
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean uniquePlaceHolder(final ObjectProperty<Node> uniquePlaceHolder) {
        this.uniquePlaceHolder = uniquePlaceHolder;
        return this;
    }

    /**
     * Gets the children place holder.
     *
     * @return Returns the childrenPlaceHolder.
     */
    public ObservableList<Node> childrenPlaceHolder() {
        return this.childrenPlaceHolder;
    }

    /**
     * Sets the children place holder.
     *
     * @param childrenPlaceHolder The childrenPlaceHolder to set.
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean childrenPlaceHolder(final ObservableList<Node> childrenPlaceHolder) {
        this.childrenPlaceHolder = childrenPlaceHolder;
        return this;
    }

    /**
     * Checks if is flag that indicates if the child node must be added at the end (true) or at the beginning (false).
     *
     * @return Returns the appendChild.
     */
    public boolean appendChild() {
        return this.appendChild;
    }

    /**
     * Sets the flag that indicates if the child node must be added at the end (true) or at the beginning (false).
     *
     * @param appendChild The appendChild to set.
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean appendChild(final boolean appendChild) {
        this.appendChild = appendChild;
        return this;
    }

    /**
     * Gets the created node.
     *
     * @return the created node
     */
    public Model showModel() {
        return this.showModel;
    }

    /**
     * Sets the created node.
     *
     * @param model the new created node
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean showModel(final Model model) {
        this.showModel = model;
        return this;
    }

    /**
     * Gets the model instance to hide (ie: with an animation).
     *
     * @return the model instance to hide (ie: with an animation)
     */
    public Model hideModel() {
        return this.hideModel;
    }

    /**
     * Sets the model instance to hide (ie: with an animation).
     *
     * @param hideModel the new model instance to hide (ie: with an animation)
     *
     * @return the DisplayModeWavebean instance being configured
     */
    public DisplayModelWaveBean hideModel(final Model hideModel) {
        this.hideModel = hideModel;
        return this;
    }
}
