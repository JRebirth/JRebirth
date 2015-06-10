package org.jrebirth.af.core.component.basic;

import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.core.key.Key;

/**
 * The class <strong>InnerModelBase</strong>.
 *
 * @author Sébastien Bordes
 * 
 * @param <C> the type of the component used
 */
public final class InnerComponentEntry<C extends Component<?>> implements InnerComponentBase<C> {

    /** The generator of unique id. */
    // private static int idGenerator;

    /** The unique identifier of the wave type. */
    private int uid;

    /** The unique key of the inner model. */
    private final UniqueKey<C> modelKey;

    /**
     * Default constructor.
     *
     * @param componentClass the model class
     * @param keyPart the list of model keys
     */
    InnerComponentEntry(final Class<C> componentClass, final Object... keyPart) {

        this.modelKey = Key.create(componentClass, keyPart);
    }

    // /**
    // * Build an InnerModel.
    // *
    // * @param modelClass the model class
    // * @param keyPart the list of model keys
    // *
    // * @return a new fresh InnerModel type object
    // */
    // public static InnerModelEntry build(final Class<? extends Model> modelClass, final Object... keyPart) {
    // final InnerModelEntry innerModel = new InnerModelEntry(modelClass, keyPart);
    //
    // // Ensure that the uid will be unique at runtime
    // synchronized (WaveType.class) {
    // innerModel.setUid(++idGenerator);
    // }
    // return innerModel;
    // }

    /**
     * {@inheritDoc}
     */
    // @Override
    @Override
    public UniqueKey<C> getKey() {
        return this.modelKey;
    }

    /**
     * Gets the uid.
     *
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     *
     * @param uid The uid to set.
     */
    public void setUid(final int uid) {
        this.uid = uid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object innerModel) {
        return innerModel instanceof InnerComponentEntry && getUid() == ((InnerComponentEntry<?>) innerModel).getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUid();
    }

}
