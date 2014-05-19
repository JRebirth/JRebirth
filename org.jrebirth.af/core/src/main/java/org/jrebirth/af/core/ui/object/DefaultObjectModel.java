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
package org.jrebirth.af.core.ui.object;

import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.ui.View;
import org.jrebirth.af.core.wave.Wave;

/**
 *
 * The interface <strong>DefaultObjectModel</strong>.
 *
 * Default implementation of the model.
 *
 * @author Sébastien Bordes
 *
 * @param <M> the class type of the current model
 * @param <V> the class type of the view managed by this model
 * @param <O> the class type of the bindable object
 */
public class DefaultObjectModel<M extends Model, V extends View<?, ?, ?>, O extends Object> extends AbstractObjectModel<M, V, O> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerModels() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do yet

    }

}
