package org.jrebirth.af.component.command;

import org.jrebirth.af.component.behavior.dockable.DockableBehavior;
import org.jrebirth.af.component.behavior.dockable.impl.DockableBehaviorImpl;
import org.jrebirth.af.core.command.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Wave;

public class StartUpCommand extends DefaultCommand {

    @Override
    protected void perform(final Wave wave) throws CommandException {

        getLocalFacade().getGlobalFacade().getComponentFactory().register(DockableBehavior.class, DockableBehaviorImpl.class);

    }

}
