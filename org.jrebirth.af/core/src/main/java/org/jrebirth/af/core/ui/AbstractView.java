/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.Controller;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.NullController;
import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.api.ui.annotation.AutoHandler;
import org.jrebirth.af.api.ui.annotation.AutoHandler.CallbackObject;
import org.jrebirth.af.api.ui.annotation.OnFinished;
import org.jrebirth.af.api.ui.annotation.RootNodeClass;
import org.jrebirth.af.api.ui.annotation.RootNodeId;
import org.jrebirth.af.api.ui.annotation.type.EnumEventType;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.handler.AnnotationEventHandler;
import org.jrebirth.af.core.util.ClassUtility;

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
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractView.class);

    /** The base name of all JRebirth Annotation. */
    private static final String BASE_ANNOTATION_NAME = "org.jrebirth.af.api.ui.annotation.On";

    /** The view model. */
    private final transient M model;

    /** The view controller. */
    private transient C controller;

    /** The Pane that hold the view's root node. */
    private transient Pane viewPane;

    /** The root node of this view. */
    private transient N rootNode;

    /** The error node used if an error occurred. */
    private transient Pane errorNode;

    /** The callback object to use for annotation event handler. */
    private Object callbackObject;

    /**
     * Default Constructor.
     *
     * @param model the dedicated view model
     */
    public AbstractView(final M model) {

        // Attach the view model
        this.model = model;

        // Track this view creation
        model().localFacade().globalFacade().trackEvent(JRebirthEventType.CREATE_VIEW, model().getClass(), this.getClass());

        try {
            // Build the root node of the view
            this.rootNode = buildRootNode();

            this.viewPane = new StackPane() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public String getTypeSelector() {
                    return model().getClass().getSimpleName().replace("Model", "");
                }

            };

            this.viewPane.getChildren().add(this.rootNode);

            // Manage components controller
            this.controller = buildController();

        } catch (final CoreException ce) {

            this.controller = null;
            this.viewPane = null;
            this.rootNode = null;

            LOGGER.log(UIMessages.CREATION_FAILURE, ce, this.getClass().getName());
            // Don't send any RuntimeException but display an error node
            buildErrorNode(ce);
        }
    }

    /**
     * Build the errorNode to display the error taht occured.
     *
     * @param ce the CoreException to display
     */
    private void buildErrorNode(final CoreException ce) {
        final TextArea ta = new TextArea(ce.getMessage());
        this.errorNode = new Pane();
        this.errorNode.getChildren().add(ta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() throws CoreException {

        // Initialize view components
        initInternalView();

        // Activate the controller to listen all components (this+children)
        if (controller() != null) {
            controller().activate();
        }

        // Process class annotation
        processViewAnnotation();

        // Process field annotation to attach event handler
        processFields();

        // Boot the view, ie to load data
        bootView();

    }

    /**
     * Process view annotation.
     *
     * This will define if callback action will the view itself or its dedicated controller
     */
    private void processViewAnnotation() {

        // Find the AutoHandler annotation if any because it's optional
        final AutoHandler ah = ClassUtility.getLastClassAnnotation(this.getClass(), AutoHandler.class);

        // Use the annotation value to define the callbackObject : View or Controller
        // When controller is null always use the view as callbackObject
        if (ah != null && ah.value() == CallbackObject.View || controller() == null) {
            this.callbackObject = this;
        } else {
            // by default use the controller object as callback object
            this.callbackObject = this.controller();
        }

        // Find the RootNodeId annotation
        final RootNodeId rni = ClassUtility.getLastClassAnnotation(this.getClass(), RootNodeId.class);
        if (rni != null) {
            node().setId(rni.value().isEmpty() ? this.getClass().getSimpleName() : rni.value());
        }

        // Find the RootNodeClass annotation
        final RootNodeClass rnc = ClassUtility.getLastClassAnnotation(this.getClass(), RootNodeClass.class);
        if (rnc != null && rnc.value().length > 0) {
            for (final String styleClass : rnc.value()) {
                if (styleClass != null && !styleClass.isEmpty()) {
                    node().getStyleClass().add(styleClass);
                }
            }
        }

        // Process Event Handler Annotation
        // For each View class annotation we will attach an event handler to the root node
        for (final Annotation a : this.getClass().getAnnotations()) {
            // Manage only JRebirth OnXxxxx annotations
            if (a.annotationType().getName().startsWith(BASE_ANNOTATION_NAME)) {
                try {
                    // Process the annotation if the node is not null
                    if (node() != null && controller() instanceof AbstractController) {
                        addHandler(node(), a);
                    }
                } catch (IllegalArgumentException | CoreException e) {
                    LOGGER.log(UIMessages.VIEW_ANNO_PROCESSING_FAILURE, e, this.getClass().getName());
                }
            }
        }

    }

    /**
     * Process all fields' annotations to auto-link them with event handler.
     *
     * @throws CoreException if annotation processing fails
     */
    private void processFields() throws CoreException {

        final Class<?> currentClass = this.getClass();

        // Parse view properties
        for (final Field f : currentClass.getDeclaredFields()) {

            // Only Node and Animation properties are eligible
            if (Node.class.isAssignableFrom(f.getType()) || Animation.class.isAssignableFrom(f.getType())) {

                // If a property was private, it must set to accessible = false after processing action
                boolean needToHide = false;
                // For private properties, set them accessible temporary
                if (!f.canAccess(this)) {
                    f.setAccessible(true);
                    needToHide = true;
                }
                // Process all existing annotation for the current field
                processAnnotations(f);

                // Reset the property visibility
                if (needToHide && f.canAccess(this)) {
                    f.setAccessible(false);
                }
            }
        }
    }

    /**
     * Process all OnXxxx Annotation to attach event handler on this field.
     *
     * @param property the field to analyze
     *
     * @throws CoreException if annotation processing fails
     */
    private void processAnnotations(final Field property) throws CoreException {
        // For each field annotation we will attach an event handler
        for (final Annotation a : property.getAnnotations()) {

            if (EventTarget.class.isAssignableFrom(property.getType())) {

                // Manage only JRebirth OnXxxxx annotations
                if (a.annotationType().getName().startsWith(BASE_ANNOTATION_NAME)) {

                    try {
                        // Retrieve the property value
                        final EventTarget target = (EventTarget) property.get(this);

                        // Process the annotation if the node is not null
                        if (target != null && controller() instanceof AbstractController) {
                            addHandler(target, a);
                        }

                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        LOGGER.log(UIMessages.NODE_ANNO_PROCESSING_FAILURE, e, this.getClass().getName(), property.getName());
                    }
                }

                // Manage only JRebirth OnFinished annotations
            } else if (Animation.class.isAssignableFrom(property.getType())
                    && OnFinished.class.getName().equals(a.annotationType().getName())) {

                try {
                    // Retrieve the property value
                    final Animation animation = (Animation) property.get(this);

                    // Process the annotation if the node is not null
                    if (animation != null && controller() instanceof AbstractController) {
                        addHandler(animation, a);
                    }

                } catch (IllegalArgumentException | IllegalAccessException e) {
                    LOGGER.log(UIMessages.ANIM_ANNO_PROCESSING_FAILURE, e, this.getClass().getName(), property.getName());
                }

            }

        }
    }

    /**
     * Add an event handler on the given node according to annotation OnXxxxx.
     *
     * @param target the graphical node, must be not null (is a subtype of EventTarget)
     * @param annotation the OnXxxx annotation
     *
     * @throws CoreException if an error occurred while linking the event handler
     */
    private void addHandler(final EventTarget target, final Annotation annotation) throws CoreException {

        // Build the auto event handler for this annotation
        final AnnotationEventHandler<Event> aeh = new AnnotationEventHandler<>(this.callbackObject, annotation);

        for (final EnumEventType eet : (EnumEventType[]) ClassUtility.getAnnotationAttribute(annotation, "value")) {
            if (target instanceof Node) {
                ((Node) target).addEventHandler(eet.eventType(), aeh);
            } else if (target instanceof MenuItem) {

                if (eet.eventType() == ActionEvent.ACTION) {
                    ((MenuItem) target).addEventHandler(ActionEvent.ACTION, new AnnotationEventHandler<>(this.callbackObject, annotation));
                } else {
                    ((MenuItem) target).setOnMenuValidation(aeh);
                }
            } else if (target instanceof Window) {

                ((Window) target).addEventHandler(eet.eventType(), aeh);
            }
        }

    }

    /**
     * Add an event handler on the given animation according to annotation OnFinished.
     *
     * @param animation the animation, must be not null
     * @param annotation the OnXxxx annotation (only OnFinished is supported)
     *
     * @throws CoreException if an error occurred while linking the event handler
     */
    private void addHandler(final Animation animation, final Annotation annotation) throws CoreException {

        // Build the auto event handler for this annotation
        final AnnotationEventHandler<ActionEvent> aeh = new AnnotationEventHandler<>(this.callbackObject, annotation);
        // Only on event type
        animation.setOnFinished(aeh);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void start();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void reload();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void hide();

    /**
     * Build the root node.
     *
     * @return the root node of the view
     *
     * @throws CoreException if introspection fails
     */
    @SuppressWarnings("unchecked")
    protected N buildRootNode() throws CoreException {

        // Build the node by reflection without any parameter or excluded class
        return (N) ClassUtility.buildGenericType(this.getClass(), Node.class);
    }

    /**
     * Build the view controller.
     *
     * @throws CoreException if introspection fails
     */
    @SuppressWarnings("unchecked")
    protected C buildController() throws CoreException {

        // Build the controller by reflection excluding NullController
        return (C) ClassUtility.findAndBuildGenericType(this.getClass(), Controller.class, NullController.class, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final M model() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final C controller() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final N node() {
        return this.rootNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Pane pane() {
        return this.viewPane;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Pane errorNode() {
        return this.errorNode;
    }

    /**
     * Initialize the view.
     *
     * This method is a hook to manage generic code before initializing the view's node tree.
     *
     * You must implement the {@link #initView()} method to setup your view.
     */
    protected final void initInternalView() {
        // Do some generic stuff to set up the view

        // Call the user method used to set up the graphical tree of nodes
        initView();
    }

    /**
     * Custom method used to initialize components.
     *
     * This method must be overridden by user to create its own graphical tree
     */
    protected abstract void initView();

    /**
     * Initialize the view.
     *
     * This method is a hook to manage generic code before initializing the view's node tree.
     *
     * You must implement the {@link #initView()} method to setup your view.
     */
    protected final void bootInternalView() {
        // Do some generic stuff to set up the view

        // Call the user method used to set up the graphical tree of nodes
        JRebirth.runIntoJAT(this::bootView);
    }

    /**
     * Custom method used to initialize components.
     *
     * This method must be overridden by user to create its own graphical tree
     */
    protected abstract void bootView();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        model().localFacade().globalFacade().trackEvent(JRebirthEventType.DESTROY_VIEW, null, this.getClass());
        super.finalize();
    }

}
