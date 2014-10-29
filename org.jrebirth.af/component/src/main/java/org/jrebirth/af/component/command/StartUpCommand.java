package org.jrebirth.af.component.command;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.component.behavior.dockable.DockableBehavior;
import org.jrebirth.af.component.behavior.dockable.impl.DockableBehaviorImpl;
import org.jrebirth.af.core.command.single.internal.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;

public class StartUpCommand extends DefaultCommand {

    @Override
    protected void perform(final Wave wave) throws CommandException {

        getLocalFacade().getGlobalFacade().getComponentFactory().register(DockableBehavior.class, DockableBehaviorImpl.class);

    }

}
