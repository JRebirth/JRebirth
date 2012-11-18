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
package org.jrebirth.core.ui;

import javafx.scene.Node;

import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.facade.UniqueKey;

/**
 * The interface <strong>Model</strong>.
 * 
 * The contract for the model layer.
 * 
 * @author Sébastien Bordes
 */
public interface Model extends FacadeReady<Model> {

    /**
     * Return the view.
     * 
     * @return the view managed
     */
    View<?, ?, ?> getView();

    /**
     * Return the root node.
     * 
     * @return the root node of the managed view
     */
    Node getRootNode();

    /**
     * Return the root model (for inner model).
     * 
     * @return the root model or null
     */
    Model getRootModel();

    /**
     * Define the root model for an inner model.
     * 
     * @param rootModel The rootModel to set.
     */
    void setRootModel(Model rootModel);

    /**
     * Get an inner model.
     * 
     * If the model isn't registered create it
     * 
     * @param innerModel the enumeration entry that describe the inner model
     * @param innerModelKey key used to identify each innerModle of the same type
     * 
     * @return the inner model instance
     */
    Model getInnerModel(InnerModels innerModel, UniqueKey... innerModelKey);

}
