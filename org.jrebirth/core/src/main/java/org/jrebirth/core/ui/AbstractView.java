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

import java.io.InputStream;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.EventType;
import org.jrebirth.core.util.ClassUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The class <strong>AbstractView</strong>.
 * 
 * Base implementation of the view.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> The class type of the model of the view
 * @param <N> Any object that is a JavaFx2 Node
 * @param <C> The class type of the controller of the view
 */
public abstract class AbstractView<M extends Model, N extends Node, C extends Controller<?, ?>> implements View<M, N, C> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractView.class);

    /** The view model. */
    private final transient M model;

    /** The view controller. */
    private transient C controller;

    /** The root node of this view. */
    private transient N rootNode;

    /** The error node used if an error occurred. */
    private transient Pane errorNode;

    /**
     * Default Constructor.
     * 
     * @param model the dedicated view model
     */
    public AbstractView(final M model) {

        // Attach the view model
        this.model = model;

        // Track this view creation
        getModel().getLocalFacade().getGlobalFacade().trackEvent(EventType.CREATE_VIEW, getModel().getClass(), this.getClass());

        try {
            // Build the root node of the view
            buildRootNode();

            // Manage components controller
            buildController();

        } catch (final CoreException ce) {
            this.controller = null;
            this.rootNode = null;

            LOGGER.error(this.getClass().getName() + " creation has failed ", ce);
            buildErrorNode(ce);
        }
    }

    /**
     * Build the erroNode to display the error taht occured.
     * 
     * @param ce the CoreException to display
     */
    private void buildErrorNode(final CoreException ce) {
        final TextArea ta = TextAreaBuilder.create()
                .text(ce.getMessage())
                .build();
        this.errorNode = PaneBuilder.create().children(ta).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doPrepare() throws CoreException {

        // Initialize view components
        initializeComponents();

        // Activate the controller to listen all components (this+children)
        getController().activate();

        // Allow to release the model if the root business object doesn't exist anymore
        getRootNode().parentProperty().addListener(new ChangeListener<Node>() {

            @Override
            public void changed(final ObservableValue<? extends Node> observable, final Node oldValue, final Node newValue) {
                if (newValue == null) {
                    getModel().release();
                }
            }

        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void doStart();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void doReload();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void doHide();

    /**
     * Build the root node.
     * 
     * @throws CoreException if introspection fails
     */
    @SuppressWarnings("unchecked")
    protected void buildRootNode() throws CoreException {
        this.rootNode = (N) ClassUtility.buildGenericType(this.getClass(), 1);
    }

    /**
     * Build the view controller.
     * 
     * @throws CoreException if introspection fails
     */
    @SuppressWarnings("unchecked")
    protected void buildController() throws CoreException {

        if (!NullController.class.equals(ClassUtility.getGenericClass(this.getClass(), 2))) {
            // Build the controller by introspection
            this.controller = (C) ClassUtility.buildGenericType(this.getClass(), 2, this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final M getModel() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final C getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final N getRootNode() {
        return this.rootNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Pane getErrorNode() {
        return this.errorNode;
    }

    /**
     * Initialize the view.
     * 
     * You must implement the customInitializeComponents method to prepare your view.
     */
    protected final void initializeComponents() {

        customInitializeComponents();
    }

    /**
     * Custom method used to initialize components.
     */
    protected abstract void customInitializeComponents();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getModel().getLocalFacade().getGlobalFacade().trackEvent(EventType.DESTROY_VIEW, null, this.getClass());
        super.finalize();
    }

    /**
     * Load an image.
     * 
     * @param resourceName the name of the image, path must be separated by '/'
     * @return the image loaded
     */
    public Image loadImage(final String resourceName) {
        Image image = null;
        final InputStream imageInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
        if (imageInputStream != null) {
            image = new Image(imageInputStream);
        }
        if (image == null) {
            LOGGER.error("Image : " + resourceName + " not found !");
        }
        return image;
    }
}
