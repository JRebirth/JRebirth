package org.jrebirth.af.core.resource;

/**
 * The class <strong>AbstractResourceItem</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractResourceItem<R, I, P extends ResourceParams, B> implements ResourceItem<R, I, P, B> {

    /** The unique identifier of the font item. */
    private int uid;

    /**
     * Gets the uid.
     * 
     * @return Returns the uid.
     */
    public int uid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     * 
     * @param uid The uid to set.
     */
    @SuppressWarnings("unchecked")
    public I uid(final int uid) {
        this.uid = uid;
        return (I) this;
    }

}
