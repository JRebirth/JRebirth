package org.jrebirth.core.facade;

import org.jrebirth.core.ui.Model;

/**
 * 
 * The interface <strong>ModelReady</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 175 $ $Author: sbordes $
 * @since $Date: 2011-11-15 12:10:55 +0100 (mar., 15 nov. 2011) $
 */
public interface ModelReady {

    /**
     * Return the model singleton or part of multiton.
     * 
     * @param clazz the model class to find
     * @param key the multiton key (in option)
     * 
     * @param <M> a sub class of Model
     * 
     * @return a service instance
     */
    <M extends Model> M getModel(final Class<M> clazz, final UniqueKey... key);

}
