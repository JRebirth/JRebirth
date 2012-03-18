package org.jrebirth.core.facade;

import org.jrebirth.core.service.Service;

/**
 * 
 * The interface <strong>ServiceReady</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 173 $ $Author: sbordes $
 * @since $Date: 2011-11-14 22:28:08 +0100 (lun., 14 nov. 2011) $
 */
public interface ServiceReady {

    /**
     * Return the service singleton or part of multiton.
     * 
     * @param clazz the service class to find
     * @param keyPart the unique key (in option)
     * 
     * @param <S> a sub class of service
     * 
     * @return a service instance
     */
    <S extends Service> S getService(final Class<S> clazz, final Object... keyPart);

}
