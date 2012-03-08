package org.jrebirth.core.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;

import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.facade.UniqueKey;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.util.ClassUtility;

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
public abstract class AbstractModel<M extends Model, V extends View<?, ?, ?>> extends AbstractWaveReady<Model> implements Model {

    /** The model object. */
    private transient UniqueKey modeObject;

    /** The dedicated view component. */
    private transient V view;

    /** The root model not null for inner model. */
    private Model rootModel;

    /** The map that store inner models loaded. */
    private final Map<InnerModels, Model> innerModelSingletonMap = new HashMap<>();

    /** The map that store inner models loaded. */
    private final Map<InnerModels, Map<UniqueKey, Model>> innerModelMultitonMap = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void ready() throws CoreException {

        // Initialize the current model
        initialize();

        // Initialize inner models (if any)
        initializeInnerModels();

        // Show the view
        getView().show();
    }

    /**
     * Initialize the model.
     * 
     * @throws CoreException if the creation of the view fails
     */
    protected final void initialize() throws CoreException {
        // Prepare the current view
        getView().prepare();

        // Do custom stuff
        customInitialize();
    }

    /**
     * Initialize method to implement for adding custom processes.
     */
    protected abstract void customInitialize();

    /**
     * Initialize the included models.
     */
    protected final void initializeInnerModels() {
        // Do generic stuff

        // Do custom stuff
        customInitializeInnerModels();
    }

    /**
     * Initialize method for inner models to implement for adding custom processes.
     */
    protected abstract void customInitializeInnerModels();

    /**
     * @return Returns the modelObject.
     */
    public UniqueKey getModelObject() {
        return this.modeObject;
    }

    /**
     * @param modelObject The modelObject to set.
     */
    public void setModelObject(final UniqueKey modelObject) {
        this.modeObject = modelObject;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws CoreException
     */
    @Override
    public final V getView() {
        if (this.view == null) {
            createView();
        }
        return this.view;
    }

    /**
     * Create the view it was null.
     */
    private void createView() {
        // Build the current view by reflection
        try {
            this.view = (V) ClassUtility.buildGenericType(this.getClass(), 1, this);
        } catch (final CoreException e) {
            throw new CoreRuntimeException("Failure while building the view for model " + getClass(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRootNode() {
        return getView().getRootNode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getRootModel() {
        return this.rootModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRootModel(final Model rootModel) {
        this.rootModel = rootModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Model getInnerModel(final InnerModels innerModel, final UniqueKey... innerModelKey) {

        // The model to return
        Model model;

        UniqueKey key = null; // TODO Check priority
        if (innerModelKey != null && innerModelKey.length == 1) {
            key = innerModelKey[0];
        } else if (innerModel.getKey() != null) {
            key = innerModel.getKey();
        }

        if (key == null) {
            // Check if the inner model is registered
            if (!this.innerModelSingletonMap.containsKey(innerModel)) {

                // retrieve and attache the inner model into the dedicated map
                this.innerModelSingletonMap.put(innerModel, getLocalFacade().retrieve(innerModel.getModelClass()));
                // Link the current root model
                this.innerModelSingletonMap.get(innerModel).setRootModel(this);
            }

            // Return the registered inner model
            model = this.innerModelSingletonMap.get(innerModel);

        } else {

            // For Multiton Components
            // Check if the MultitonKey map exists for this component class
            if (!this.innerModelMultitonMap.containsKey(innerModel)) {
                this.innerModelMultitonMap.put(innerModel, new HashMap<UniqueKey, Model>());
            }
            // Check if the class of the object is already stored into the multitonKey map
            if (!this.innerModelMultitonMap.get(innerModel).containsKey(key)) {

                // Store the component into the multitonKey map
                this.innerModelMultitonMap.get(innerModel).put(key, getLocalFacade().retrieve(innerModel.getModelClass(), key));

                // Link the current root model
                this.innerModelMultitonMap.get(innerModel).get(key).setRootModel(this);
            }
            model = this.innerModelMultitonMap.get(innerModel).get(key);
        }
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract void processAction(final Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.DESTROY_MODEL, null, this.getClass());
        super.finalize();
    }

}
