package org.jrebirth.core.facade;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.event.EventType;

/**
 * 
 * The class <strong>CommandFacade</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 164 $ $Author: sbordes $
 * @since $Date: 2011-11-07 18:19:18 +0100 (lun., 07 nov. 2011) $
 */
public class CommandFacade extends AbstractFacade<Command> {

    /**
     * Default Constructor.
     * 
     * @param globalFacade the global facade
     */
    public CommandFacade(final GlobalFacade globalFacade) {
        super(globalFacade);
        getGlobalFacade().trackEvent(EventType.CREATE_COMMAND_FACADE, globalFacade.getClass(), this.getClass());
    }
}
