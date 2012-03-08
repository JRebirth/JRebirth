package org.jrebirth.core.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.adapter.ActionAdapter;
import org.jrebirth.core.ui.adapter.DragAdapter;
import org.jrebirth.core.ui.adapter.KeyAdapter;
import org.jrebirth.core.ui.adapter.MouseAdapter;
import org.jrebirth.core.ui.adapter.WindowAdapter;
import org.jrebirth.core.ui.handler.ActionHandler;
import org.jrebirth.core.ui.handler.DragHandler;
import org.jrebirth.core.ui.handler.KeyHandler;
import org.jrebirth.core.ui.handler.MouseHandler;
import org.jrebirth.core.ui.handler.WindowHandler;

/**
 * The abstract class <strong>AbstractController</strong>.
 * 
 * Base implementation of the controller.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the model of the view controlled
 * @param <V> the class type of the view controlled
 */
public abstract class AbstractController<M extends Model, V extends View<M, ?, ?>> implements Controller<M, V> {

    /** The view component. */
    private final transient V view;

    /** The action handler instance to use for Action events. */
    private EventHandler<ActionEvent> actionHandler;

    /** The mouse handler instance to use for Mouse events. */
    private EventHandler<MouseEvent> mouseHandler;

    /** The key handler instance to use for Key events. */
    private EventHandler<KeyEvent> keyHandler;

    /** The drag handler instance to use for Drag events. */
    private EventHandler<DragEvent> dragHandler;

    /** The window handler instance to use for Windows events. */
    private EventHandler<WindowEvent> windowHandler;

    /**
     * Default Constructor.
     * 
     * @param view the controlled view
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public AbstractController(final V view) throws CoreException {
        // Attach the view
        this.view = view;

        // Track this controller creation
        getModel().getLocalFacade().getGlobalFacade().trackEvent(EventType.CREATE_CONTROLLER, this.getView().getClass(), this.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void activate() throws CoreException {
        // Initialize event adapters
        initializeEventAdapters();

        // Initialize event handlers
        initializeEventHandlers();
    }

    /**
     * Initialize event Adapters.
     * 
     * You must implement the customInitializeEventHandlers method to prepare your controller.
     * 
     * @throws CoreException if an error occurred while creating event adapters
     */
    protected final void initializeEventAdapters() throws CoreException {
        // Do generic stuff

        // Do custom stuff
        customInitializeEventAdapters();
    }

    /**
     * Custom method used to initialize event Adapters.
     * 
     * @throws CoreException if an error occurred while creating event adapters
     */
    protected abstract void customInitializeEventAdapters() throws CoreException;

    /**
     * Initialize event Handlers.
     * 
     * You must implement the customInitializeEventHandlers method to prepare your controller.
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    protected final void initializeEventHandlers() throws CoreException {
        // Do generic stuff

        // Do custom stuff
        customInitializeEventHandlers();
    }

    /**
     * Custom method used to initialize event handlers.
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    protected abstract void customInitializeEventHandlers() throws CoreException;

    /**
     * @return Returns the view.
     */
    @Override
    public final V getView() {
        return this.view;
    }

    /**
     * @return Returns the model.
     */
    @Override
    public final M getModel() {
        return getView().getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRootNode() {
        return getView().getRootNode();
    }

    /**
     * If a contract is broken throw an exception to warn developers.
     * 
     * @param adapterClass the class that represent the contract to comply with
     * 
     * @throws CoreException to stop ui initialization
     */
    private void throwBrokenContract(final Class<?> adapterClass) throws CoreException {
        throw new CoreException(this.getClass().getName() + " must implement " + adapterClass.getName() + " interface");
    }

    /**
     * Build an instance of ActionHandler using a custom ActionAdapter.
     * 
     * @param actionAdapter the custom adapter to used to manage action events
     */
    protected final void buildActionHandler(final ActionAdapter actionAdapter) {
        this.actionHandler = new ActionHandler(actionAdapter);
    }

    /**
     * Return a ActionEvent Handler.
     * 
     * @return the action event handler
     * 
     * @throws CoreException an exception if the current class doesn't implement the ActionAdapter interface.
     */
    protected final EventHandler<ActionEvent> getActionHandler() throws CoreException {
        // Check if the handler has been created or not
        if (this.actionHandler == null) {
            // Build the mouse handler instance
            if (this instanceof ActionAdapter) {
                this.actionHandler = new ActionHandler((ActionAdapter) this);
            } else {
                throwBrokenContract(ActionAdapter.class);
            }
        }
        return this.actionHandler;
    }

    /**
     * Build an instance of MouseHandler using a custom MouseAdapter.
     * 
     * @param mouseAdapter the custom adapter to used to manage mouse events
     */
    protected final void buildMouseHandler(final MouseAdapter mouseAdapter) {
        this.mouseHandler = new MouseHandler(mouseAdapter);
    }

    /**
     * Return a MouseEvent Handler.
     * 
     * @return the mouse event handler
     * 
     * @throws CoreException an exception if the current class doesn't implement the MouseAdapter interface.
     */
    protected final EventHandler<MouseEvent> getMouseHandler() throws CoreException {
        // Check if the handler has been created or not
        if (this.mouseHandler == null) {
            // Build the mouse handler instance
            if (this instanceof MouseAdapter) {
                this.mouseHandler = new MouseHandler((MouseAdapter) this);
            } else {
                throwBrokenContract(MouseAdapter.class);
            }
        }
        return this.mouseHandler;
    }

    /**
     * Build an instance of KeyHandler using a custom KeyAdapter.
     * 
     * @param keyAdapter the custom adapter to used to manage key events
     */
    protected final void buildKeyHandler(final KeyAdapter keyAdapter) {
        keyAdapter.setController(this);
        this.keyHandler = new KeyHandler(keyAdapter);
    }

    /**
     * Return a KeyEvent Handler.
     * 
     * @return the key event handler
     * 
     * @throws CoreException an exception if the current class doesn't implement the KeyAdapter interface.
     */
    protected final EventHandler<KeyEvent> getKeyHandler() throws CoreException {
        // Check if the handler has been created or not
        if (this.keyHandler == null) {
            // Build the mouse handler instance
            if (this instanceof KeyAdapter) {
                this.keyHandler = new KeyHandler((KeyAdapter) this);
            } else {
                throwBrokenContract(KeyAdapter.class);
            }
        }
        return this.keyHandler;
    }

    /**
     * Build an instance of DragHandler using a custom DragAdapter.
     * 
     * @param dragAdapter the custom adapter to used to manage drag events
     */
    protected final void buildDragHandler(final DragAdapter dragAdapter) {
        this.dragHandler = new DragHandler(dragAdapter);
    }

    /**
     * Return a DragEvent Handler.
     * 
     * @return the drag event handler
     * 
     * @throws CoreException an exception if the current class doesn't implements the DragAdapter interface.
     */
    protected final EventHandler<DragEvent> getDragHandler() throws CoreException {
        // Check if the handler has been created or not
        if (this.dragHandler == null) {
            // Build the mouse handler instance
            if (this instanceof DragAdapter) {
                this.dragHandler = new DragHandler((DragAdapter) this);
            } else {
                throwBrokenContract(DragAdapter.class);
            }
        }
        return this.dragHandler;
    }

    /**
     * Build an instance of WindowHandler using a custom WindowAdapter.
     * 
     * @param windowAdapter the custom adapter to used to manage window events
     */
    protected final void buildWindowHandler(final WindowAdapter windowAdapter) {
        this.windowHandler = new WindowHandler(windowAdapter);
    }

    /**
     * Return a WindowEvent Handler.
     * 
     * @return the window event handler
     * 
     * @throws CoreException an exception if the current class doesn't implements the WindowAdapter interface.
     */
    protected final EventHandler<WindowEvent> getWindowHandler() throws CoreException {
        // Check if the handler has been created or not
        if (this.windowHandler == null) {
            // Build the mouse handler instance
            if (this instanceof WindowAdapter) {
                this.windowHandler = new WindowHandler((WindowAdapter) this);
            } else {
                throwBrokenContract(WindowAdapter.class);
            }
        }
        return this.windowHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getModel().getLocalFacade().getGlobalFacade().trackEvent(EventType.DESTROY_CONTROLLER, null, this.getClass());
        super.finalize();
    }
}
