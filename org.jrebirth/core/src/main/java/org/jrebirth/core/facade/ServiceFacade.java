package org.jrebirth.core.facade;

import org.jrebirth.core.event.EventType;
import org.jrebirth.core.service.Service;

/**
 * 
 * The class <strong>ServiceFacade</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 164 $ $Author: sbordes $
 * @since $Date: 2011-11-07 18:19:18 +0100 (lun., 07 nov. 2011) $
 */
public class ServiceFacade extends AbstractFacade<Service> {

    /**
     * Default Constructor.
     * 
     * @param globalFacade the global facade
     */
    public ServiceFacade(final GlobalFacade globalFacade) {
        super(globalFacade);
        getGlobalFacade().trackEvent(EventType.CREATE_SERVICE_FACADE, globalFacade.getClass(), this.getClass());
    }

}
