package org.jrebirth.af.core.resource;

import org.jrebirth.af.api.resource.ResourceItem;
import org.jrebirth.af.api.resource.ResourceParams;

/**
 * The class <strong>AbstractResourceItem</strong>.
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractResourceItem<I, P extends ResourceParams, R> implements ResourceItem<I, P, R> {

    /** The unique identifier of the font item. */
    private int uid;
    
	private Module module = null;

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
    
	@SuppressWarnings("unchecked")
	@Override
	public I module(Module module) {
		this.module = module;
    	return (I) this;
	}
    
	@Override
	public Module module() {
		return module;
	}

}
