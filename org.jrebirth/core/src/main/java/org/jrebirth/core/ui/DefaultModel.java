/**
 * Copyright JRebirth.org © 2011-2012 
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

import org.jrebirth.core.wave.Wave;

/**
 * 
 * The interface <strong>DefaultModel</strong>.
 * 
 * Default implementation of the model.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the current model
 * @param <V> the class type of the view managed by this model
 */
public class DefaultModel<M extends Model, V extends View<?, ?, ?>> extends AbstractModel<M, V> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do generic
    }

}
