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

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.CommandWaveBuilder;
import org.jrebirth.core.wave.WaveBase;

/**
 * The class <strong>ShowModelWaveBuilder</strong>. is used to build a new Show Model Wave.
 * 
 * @author Sébastien Bordes
 */
public final class ShowModelWaveBuilder extends CommandWaveBuilder<ShowModelWaveBuilder, ShowModelWaveBean> {

    /** The field used to store the property mask. */
    private int setMask;

    /** The model class to show. */
    private Class<? extends Model> modelClass;

    /** The unique place holder, in example the centerProperty of a BorderPane. */
    private ObjectProperty<Node> uniquePlaceHolder;

    /** The Children list of a parent pane. */
    private ObservableList<Node> chidrenPlaceHolder;

    /** The created node. */
    private Node createdNode;

    /**
     * Private constructor.
     */
    private ShowModelWaveBuilder() {
        super(ShowModelCommand.class, ShowModelWaveBean.class);
    }

    /**
     * Static method to build a default builder.
     * 
     * @return a new fresh ShowModelWaveBuilder instance
     */
    public static ShowModelWaveBuilder create() {
        return new ShowModelWaveBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(final WaveBase paramWave) {
        super.applyTo(paramWave);

        final int i = this.setMask;
        if ((i & 0x1) != 0) {
            getWaveBean(paramWave).setUniquePlaceHolder(this.uniquePlaceHolder);
        }
        if ((i & 0x2) != 0) {
            getWaveBean(paramWave).setChidrenPlaceHolder(this.chidrenPlaceHolder);
        }
        if ((i & 0x4) != 0) {
            getWaveBean(paramWave).setModelClass(this.modelClass);
        }
        if ((i & 0x8) != 0) {
            getWaveBean(paramWave).setCreatedNode(this.createdNode);
        }
    }

    /**
     * Define the unique place holder.
     * 
     * @param uniquePlaceHolder the property that handle the view's node
     * 
     * @return the builder
     */
    public ShowModelWaveBuilder uniquePlaceHolder(final ObjectProperty<Node> uniquePlaceHolder) {
        this.uniquePlaceHolder = uniquePlaceHolder;
        this.setMask |= 1;
        return this;
    }

    /**
     * Define the children list observable list.
     * 
     * @param chidrenPlaceHolder the children list of the parent pane that will handle the view's node
     * 
     * @return the builder
     */
    public ShowModelWaveBuilder childrenPlaceHolder(final ObservableList<Node> chidrenPlaceHolder) {
        this.chidrenPlaceHolder = chidrenPlaceHolder;
        this.setMask |= 2;
        return this;
    }

    /**
     * Define the model class to shown.
     * 
     * @param modelClass the model class to shown
     * 
     * @return the builder
     */
    public ShowModelWaveBuilder modelClass(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
        this.setMask |= 2;
        return this;
    }

    /**
     * Define the created node.
     * 
     * @param createdNode the node created
     * 
     * @return the builder
     */
    public ShowModelWaveBuilder createdNode(final Node createdNode) {
        this.createdNode = createdNode;
        this.setMask |= 4;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveBase build() {
        final WaveBase localWave = new WaveBase();
        applyTo(localWave);
        return localWave;
    }

}
