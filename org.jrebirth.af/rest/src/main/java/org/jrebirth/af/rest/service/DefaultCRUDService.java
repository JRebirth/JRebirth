package org.jrebirth.af.rest.service;

public class DefaultCRUDService<O extends Object> extends AbstractCRUDRestService<O> {
	
	private Class<O> clazz;

	/**
	 * @param clazz
	 */
	public DefaultCRUDService(Class<O> clazz) {
		super();
		this.clazz = clazz;
	}

}
