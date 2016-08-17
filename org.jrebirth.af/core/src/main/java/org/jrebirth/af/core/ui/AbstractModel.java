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

import java.util.List;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.NullView;
import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.object.ModelConfig;
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
public abstract class AbstractModel<M extends Model, V extends View<?, ?, ?>> extends AbstractBaseModel<M> {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractModel.class);

    /** The dedicated view component. */
    private transient V view;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareView() {
        try {
            if (view() != null) {
                view().prepare();
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

        if (key().value() instanceof ModelConfig<?>) {
            applyStyle((ModelConfig<?>) key().value());
        }

        if (key().value() instanceof List<?>) {
            for (final Object data : (List<?>) key().value()) {
                if (data instanceof ModelConfig<?>) {
                    applyStyle((ModelConfig<?>) key().value());
                }
            }
        }

        for (final Object data : key().optionalData()) {
            if (data instanceof ModelConfig<?>) {
                applyStyle((ModelConfig<?>) data);
            }
        }

        // Do generic stuff
        // listen(JRebirthWaves.SHOW_VIEW_WT);
        // listen(JRebirthWaves.HIDE_VIEW_WT);

        // Do custom stuff
        initModel();
    }

    /**
     * TODO To complete.
     *
     * @param mc
     */
    protected void applyStyle(final ModelConfig<?> mc) {
        if (!ModelConfig.UNDETERMINED.equals(mc.id())) {
            node().setId(mc.id());
        }
        if (!ModelConfig.UNDETERMINED.equals(mc.style())) {
            node().setStyle(mc.style());
        }
        if (!ModelConfig.UNDETERMINED.equals(mc.styleClass())) {
            node().getStyleClass().addAll(mc.styleClass().split(" "));
        }
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
    public final V view() {
        if (this.view == null) {
            try {
                this.view = buildView();
            } catch (final CoreException ce) {
                LOGGER.log(UIMessages.CREATION_FAILURE, ce, this.getClass().getName());
                throw new CoreRuntimeException(ce);
            }
        }
        return this.view;
    }

    /**
     * Create the view it was null.
     * 
     * @throws CoreException when han't been built correctly
     */
    @SuppressWarnings("unchecked")
    protected V buildView() throws CoreException {

        return (V) ClassUtility.findAndBuildGenericType(this.getClass(), View.class, NullView.class, this);
    }

}
