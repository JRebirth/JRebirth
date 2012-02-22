package org.jrebirth.core.command.impl;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.link.impl.AbstractReady;

/**
 * 
 * The class <strong>CommandImpl</strong>.
 * 
 * Base implementation of the command.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 254 $ $Author: sbordes $
 * @since $Date: 2011-12-18 22:59:18 +0100 (dim., 18 déc. 2011) $
 */
public class CommandImpl extends AbstractReady<Command> implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(final Wave wave) {
        // Must be overridden to do stuff
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Must be overridden to manage action handling by command
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.DESTROY_COMMAND, null, this.getClass());
        super.finalize();
    }

}
