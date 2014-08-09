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
package org.jrebirth.af.core.ui.fxml;

import javafx.fxml.Initializable;

import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.ui.View;

/**
 * The interface <strong>FXMLController</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <M> The model responsible of the view
 * @param <V> The view hosting the FXML component
 */
public interface FXMLController<M extends Model, V extends View<M, ?, ?>> extends Initializable {

    /**
     * Set the model that manage the view that load this FXML component.
     *
     * @param model the linked model
     */
    void setModel(final M model);

    /**
     * Return the linked model that manage the view.
     *
     * @return the linked model
     */
    M getModel();

    /**
     * Return the linked view that load this component.
     *
     * @return the linked view
     */
    V getView();

}
