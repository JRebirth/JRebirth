package org.jrebirth.core.facade.impl;

import org.jrebirth.core.event.EventType;
import org.jrebirth.core.facade.AbstractFacade;
import org.jrebirth.core.facade.GlobalFacade;
import org.jrebirth.core.ui.Model;

/**
 * 
 * The class <strong>UiFacade</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 164 $ $Author: sbordes $
 * @since $Date: 2011-11-07 18:19:18 +0100 (lun., 07 nov. 2011) $
 */
public class UiFacade extends AbstractFacade<Model> {

    /**
     * Default Constructor.
     * 
     * @param globalFacade the global facade
     */
    public UiFacade(final GlobalFacade globalFacade) {
        super(globalFacade);
        getGlobalFacade().trackEvent(EventType.CREATE_UI_FACADE, globalFacade.getClass(), this.getClass());
    }

}
