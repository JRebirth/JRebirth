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
package org.jrebirth.af.core.ui;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.NullView;
import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.api.ui.annotation.AutoRelease;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.util.ClassUtility;

/**
 *
 * The interface <strong>AbstractModel</strong>.
 *
 * Base implementation of the model.
 *
 * @author Sébastien Bordes
 *
 * @param <M> the class type of the current model
 * @param <V> the class type of the view managed by this model
 */
@AutoRelease
public abstract class AbstractModel<M extends Model, V extends View<?, ?, ?>> extends AbstractBaseModel<M> {

    /** The dedicated view component. */
    private transient V view;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareView() {
        try {
            if (getView() != null) {
                getView().prepare();
            }
        } catch (final CoreException ce) {
            throw new CoreRuntimeException(ce);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInternalModel() throws CoreException {

        // Do generic stuff
        // listen(JRebirthWaves.SHOW_VIEW_WT);
        // listen(JRebirthWaves.HIDE_VIEW_WT);

        // Do custom stuff
        initModel();
    }

    /**
     * Perform the show view action triggered by a wave.
     *
     * Method handler for Wave JRebirthWaves.SHOW_VIEW
     *
     * @param wave the wave that trigger the action
     */
    @Override
    public final void doShowView(final Wave wave) {
        showInternalView(wave);
    }

    /**
     * Perform the hide view action triggered by a wave.
     *
     * Method handler for Wave JRebirthWaves.HIDE_VIEW
     *
     * @param wave the wave that trigger the action
     */
    @Override
    public final void doHideView(final Wave wave) {
        hideInternalView(wave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bindInternal() {

        // Manage auto-bound mechanism
        // ...

        // Do custom binding stuff
        bind();
    }

    /**
     * {@inheritDoc}
     *
     * @throws CoreException
     */
    @Override
    public final V getView() {
        if (this.view == null) {
            buildView();
        }
        return this.view;
    }

    /**
     * Create the view it was null.
     */
    @SuppressWarnings("unchecked")
    protected void buildView() {

        final Class<?> viewClass = ClassUtility.findGenericClass(this.getClass(), View.class);

        if (viewClass != null && !NullView.class.equals(viewClass)) {
            // Build the current view by reflection
            try {
                this.view = (V) ClassUtility.buildGenericType(this.getClass(), View.class, this);
            } catch (final CoreException e) {
                throw new CoreRuntimeException("Failure while building the view for model " + getClass(), e);
            }
        }
    }
}
