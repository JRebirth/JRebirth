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

import java.util.Arrays;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import org.jrebirth.core.command.CommandWaveBuilder;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.WaveBase;

/**
 * The class <strong>ShowModelWaveBuilder</strong>. is used to build a new Show Model Wave.
 * 
 * @author Sébastien Bordes
 */
public final class ShowModelWaveBuilder extends CommandWaveBuilder<ShowModelWaveBuilder, DisplayModelWaveBean> {

    /** The unique key of the model to show. */
    private UniqueKey<? extends Model> showModelKey;

    /** The unique place holder, in example the centerProperty of a BorderPane. */
    private ObjectProperty<Node> uniquePlaceHolder;

    /** The Children list of a parent pane. */
    private ObservableList<Node> chidrenPlaceHolder;

    /** The key part. */
    private Object[] keyPart;

    /**
     * Private constructor.
     */
    private ShowModelWaveBuilder() {
        super(ShowModelCommand.class, DisplayModelWaveBean.class);
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

        if (hasBit(0)) {
            getWaveBean(paramWave).setUniquePlaceHolder(this.uniquePlaceHolder);
        }
        if (hasBit(1)) {
            getWaveBean(paramWave).setChidrenPlaceHolder(this.chidrenPlaceHolder);
        }
        if (hasBit(2)) {
            getWaveBean(paramWave).setShowModelKey(this.showModelKey);
        }
        if (hasBit(3)) {
            getWaveBean(paramWave).setKeyPart(Arrays.asList(this.keyPart));
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
        addBit(0);
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
        addBit(1);
        return this;
    }

    /**
     * Define the unique key of the model to show.
     * 
     * @param showwModelKey the unique key of the model to show
     * 
     * @return the builder
     */
    public ShowModelWaveBuilder showModelKey(final UniqueKey<? extends Model> showModelKey) {
        this.showModelKey = showModelKey;
        addBit(2);
        return this;
    }

    /**
     * Part of unique model key.
     * 
     * @param keyPart keyPart for the model class
     * 
     * @return the builder
     */
    public ShowModelWaveBuilder keyPart(final Object... keyPart) {
        this.keyPart = keyPart.clone();
        addBit(3);
        return this;
    }

}
