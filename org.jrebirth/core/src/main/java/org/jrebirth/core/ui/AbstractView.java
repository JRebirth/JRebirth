package org.jrebirth.core.ui;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.text.TextBuilder;

import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.ui.fxml.AbstractFXMLController;
import org.jrebirth.core.ui.fxml.FXMLComponent;
import org.jrebirth.core.ui.fxml.FXMLController;
import org.jrebirth.core.util.ClassUtility;

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

    /** The view model. */
    private final transient M model;

    /** The view controller. */
    private final transient C controller;

    /** The root node of this view. */
    private final transient N rootNode;

    /**
     * Default Constructor.
     * 
     * @param model the dedicated view model
     * 
     * @throws CoreException if the controller or the node creation has failed
     */
    public AbstractView(final M model) throws CoreException {

        // Attach the view model
        this.model = model;

        // Track this view creation
        getModel().getLocalFacade().getGlobalFacade().trackEvent(EventType.CREATE_VIEW, getModel().getClass(), this.getClass());

        // Build the root node of the view
        this.rootNode = buildRootNode();

        // Manage components controller
        this.controller = buildController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() throws CoreException {

        // Initialize view components
        initializeComponents();

        // Activate the controller to listen all components (this+children)
        getController().activate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void show();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void hide();

    /**
     * Build the root node.
     * 
     * @return the dedicated root node
     * 
     * @throws CoreException if introspection fails
     */
    @SuppressWarnings("unchecked")
    private N buildRootNode() throws CoreException {
        return (N) ClassUtility.buildGenericType(this.getClass(), 1);
    }

    /**
     * Build the view controller.
     * 
     * @return the dedicated view controller
     * 
     * @throws CoreException if introspection fails
     */
    @SuppressWarnings("unchecked")
    private C buildController() throws CoreException {
        // Build the controller by introspection
        return (C) ClassUtility.buildGenericType(this.getClass(), 2, this);
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
     * Load a FXML component without resource bundle.
     * 
     * @param fxmlPath the fxml string path
     * 
     * @return a FXMLComponent object taht wrap a fxml node with its controller
     */
    protected FXMLComponent loadFXML(final String fxmlPath) {
        return loadFXML(fxmlPath, null);
    }

    /**
     * Load a FXML component.
     * 
     * @param fxmlPath the fxml string path
     * @param bundlePath the bundle string path
     * 
     * @return a FXMLComponent object taht wrap a fxml node with its controller
     */
    protected FXMLComponent loadFXML(final String fxmlPath, final String bundlePath) {

        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Thread.currentThread().getContextClassLoader().getResource(fxmlPath));

        if (bundlePath != null) {
            fxmlLoader.setResources(ResourceBundle.getBundle(bundlePath));
        }

        Node node = null;
        boolean error = false;
        try {
            error = fxmlLoader.getLocation() == null;
            if (error) {
                node = TextBuilder.create().text("FXML Error : " + fxmlPath).build();
            } else {
                node = (Node) fxmlLoader.load(fxmlLoader.getLocation().openStream());
            }

        } catch (final IOException e) {
            throw new CoreRuntimeException("The FXML node doesn't exist : " + fxmlPath, e);
        }

        if (!error && !(fxmlLoader.getController() instanceof AbstractFXMLController)) {
            throw new CoreRuntimeException("The FXML controller must extends the FXMLController class : " + fxmlLoader.getController().getClass().getCanonicalName());
        }

        final FXMLController fxmlController = (FXMLController) fxmlLoader.getController();
        fxmlController.setView(this);

        return new FXMLComponent(node, fxmlController);
    }

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
     * @param resourceName the name of the image
     * @return the image loaded
     */
    protected Image loadImage(final String resourceName) {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName));
    }

}
