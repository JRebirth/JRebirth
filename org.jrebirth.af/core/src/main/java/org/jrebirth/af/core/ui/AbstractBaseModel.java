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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.annotation.AutoRelease;
import org.jrebirth.af.api.ui.annotation.CreateViewIntoJAT;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.component.behavior.AbstractBehavioredComponent;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.util.ClassUtility;

/**
 *
 * The interface <strong>AbstractBaseModel</strong>.
 *
 * Base implementation of the model.
 *
 * @author Sébastien Bordes
 *
 * @param <M> the class type of the current model
 */
public abstract class AbstractBaseModel<M extends Model> extends AbstractBehavioredComponent<Model> implements Model {

    /** Flag used to determine if a view has been already displayed, useful to manage first time animation. */
    private boolean viewDisplayed;

    /** Force the creation of the View into JAT if set to true (useful when the view has got a WebView). */
    protected boolean createViewIntoJAT;

    protected boolean hasBeenAttached = false;

    /**
     * Default Constructor.
     */
    public AbstractBaseModel() {
        super();

        // Initialize the protected field with provided annotation (if present)
        final CreateViewIntoJAT cvij = this.getClass().getAnnotation(CreateViewIntoJAT.class);
        this.createViewIntoJAT = cvij == null ? false : cvij.value();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void ready() throws CoreException {

        // Initialize the current model
        initInternalModel();

        // Model and InnerModels are OK, let's prepare the view
        JRebirth.run(this.createViewIntoJAT ? RunType.JAT_SYNC : RunType.SAME, this::prepareView);

        // Allow to release the model if the root business object doesn't exist anymore
        attachParentListener();

        // Bind Object properties to view widget ones
        bindInternal();
    }

    /**
     * Prepare the view by constructing the root node and all its children.
     *
     * By default this method will be called into JTP but could be done into JAT synchronously by setting the field createViewIntoJAT
     */
    protected abstract void prepareView();

    /**
     * Initialize the model.
     *
     * @throws CoreException if the creation of the view fails
     */
    protected abstract void initInternalModel() throws CoreException;

    /**
     * Initialize method to implement for adding custom processes.
     *
     * This method is a hook to manage generic code before initializing current model.
     *
     * You must implement the {@link #initModel()} method to setup your model.
     */
    protected abstract void initModel();

    /**
     * Bind current object to view's widget.
     *
     * This method is a hook to manage generic code before binding model's object.
     *
     * You must implement the {@link #bind()} method to add your bindings.
     */
    protected abstract void bindInternal();

    /**
     * Bind method to implement for adding custom bindings.
     */
    protected abstract void bind();

    /**
     * Show the view.<br />
     * In example : start the show transition
     *
     * This method is a hook to manage generic code before initializing the view's node tree. It will call {@link #org.jrebirth.af.core.ui.View.start()} or
     * {@link #org.jrebirth.af.core.ui.View.reload()} method
     *
     * You must implement the {@link #showView()} method to setup your view.
     */
    protected final void showInternalView(final Wave wave) {

        // Call user code
        showView();

        // Sometimes view can be null
        if (view() != null) {
            if (this.viewDisplayed) {
                // Reload the view
                view().reload();
            } else {
                // Start the view for the first time
                view().start();
                this.viewDisplayed = true;
            }
        }

        // Propagate the show view to all Inner Model
        if (getInnerComponentMap().isPresent()) {
            getInnerComponentMap().get().values().stream()
                                  .filter(s -> s instanceof Model)
                                  .forEach((model) -> ((Model) model).doShowView(wave));
        }
    }

    /**
     * Perform custom user action <b>before</b> showing the view.
     */
    protected abstract void showView();

    /**
     * Hide the view.<br />
     * In example : start the hide transition
     *
     * Will call the {@link #org.jrebirth.af.core.ui.View.hide()} method
     */
    protected final void hideInternalView(final Wave wave) {

        // Call user code
        hideView();

        // Sometimes view can be null
        if (view() != null) {
            view().hide();
        }

        // Propagate the show view to all Inner Model
        if (getInnerComponentMap().isPresent()) {
            getInnerComponentMap().get().values().stream()
                                  .filter(s -> s instanceof Model)
                                  .forEach((model) -> ((Model) model).doHideView(wave));
        }
    }

    /**
     * Perform custom action <b>before</b> hiding the view.
     */
    protected abstract void hideView();

    /**
     * {@inheritDoc}
     */
    @Override
    public Node node() {
        return view() == null ? null : view().pane();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract void processWave(final Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.DESTROY_MODEL, null, this.getClass());
        super.finalize();
    }

    /**
     * Attach a custom listener that will release the mode when the rootNode is removed from its parent.
     */
    protected void attachParentListener() {

        final AutoRelease ar = ClassUtility.getLastClassAnnotation(this.getClass(), AutoRelease.class);

        // Only manage automatic release when the annotation exists with true value
        if (ar != null && ar.value() && node() != null) { // TODO check rootnode null when using NullView

            // Allow to release the model if the root business object doesn't exist anymore
            node().parentProperty().addListener(new ChangeListener<Node>() {

                @Override
                public void changed(final ObservableValue<? extends Node> observable, final Node oldValue, final Node newValue) {
                    if (newValue != null) {
                        AbstractBaseModel.this.hasBeenAttached = true;
                    }
                    if (newValue == null && AbstractBaseModel.this.hasBeenAttached) {
                        AbstractBaseModel.this.hasBeenAttached = false;
                        release();
                        node().parentProperty().removeListener(this);
                    }
                }

            });
        }
    }

}
