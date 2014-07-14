package org.jrebirth.af.core.ui;

import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.wave.WaveTypeBase;

/**
 * The class <strong>InnerModelBase</strong>.
 *
 * @author Sébastien Bordes
 */
public final class InnerModelBase implements InnerModel {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the wave type. */
    private int uid;

    /** The unique key of the inner model. */
    private final UniqueKey<? extends Model> modelKey;

    /**
     * Default constructor.
     *
     * @param modelClass the model class
     * @param keyPart the list of model keys
     */
    private InnerModelBase(final Class<? extends Model> modelClass, final Object... keyPart) {

        this.modelKey = UniqueKey.key(modelClass, keyPart);
    }

    /**
     * Build an InnerModel.
     *
     * @param modelClass the model class
     * @param keyPart the list of model keys
     *
     * @return a new fresh InnerModel type object
     */
    public static InnerModelBase build(final Class<? extends Model> modelClass, final Object... keyPart) {
        final InnerModelBase innerModel = new InnerModelBase(modelClass, keyPart);

        // Ensure that the uid will be unique at runtime
        synchronized (WaveTypeBase.class) {
            innerModel.setUid(++idGenerator);
        }
        return innerModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<? extends Model> getKey() {
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
        return innerModel instanceof InnerModelBase && getUid() == ((InnerModelBase) innerModel).getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUid();
    }

}
