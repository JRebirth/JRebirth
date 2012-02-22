package org.jrebirth.core.facade;

/**
 * The interface <strong>GlobalReady</strong>.
 * 
 * This interface let the object to have access to the global facade.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 79 $ $Author: sbordes $
 * @since $Date: 2011-10-18 22:16:30 +0200 (Tue, 18 Oct 2011) $
 */
public interface GlobalReady {

    /**
     * Return the global facade used to manage singleton.
     * 
     * @return the global facade
     */
    GlobalFacade getGlobalFacade();

}
