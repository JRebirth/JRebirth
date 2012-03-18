package org.jrebirth.core.facade;

import org.jrebirth.core.exception.CoreException;

/**
 * The interface <strong>FacadeReady</strong>.
 * 
 * This interface let the object to be managed into its facade type.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 177 $ $Author: sbordes $
 * @since $Date: 2011-11-15 12:37:04 +0100 (mar., 15 nov. 2011) $
 * 
 * @param <R> A type that implements FacadeReady
 */
public interface FacadeReady<R extends FacadeReady<R>> {

    /**
     * Launch the initialization of the component.
     * 
     * @throws CoreException if the initialization fails
     */
    void ready() throws CoreException;

    /**
     * Return the local facade used to manage singleton.
     * 
     * @return the local facade
     */
    Facade<R> getLocalFacade();

    /**
     * Attach the local facade for this object type.
     * 
     * @param localFacade the local facade to set
     */
    void setLocalFacade(Facade<R> localFacade);

    /**
     * @return Returns the key.
     */
    UniqueKey getKey();

    /**
     * @param key The key to set.
     */
    void setKey(UniqueKey key);

    /**
     * Release the component by deleting this key used by the WeakHashMap.
     */
    void release();

}
