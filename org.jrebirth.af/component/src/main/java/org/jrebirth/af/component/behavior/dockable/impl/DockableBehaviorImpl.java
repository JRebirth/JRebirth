package org.jrebirth.af.component.behavior.dockable.impl;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.module.RegistrationPriority;
import org.jrebirth.af.component.behavior.dockable.DockableBehavior;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.core.component.behavior.AbstractModelBehavior;

@Register(value = DockableBehavior.class, priority = RegistrationPriority.Normal)
public class DockableBehaviorImpl extends AbstractModelBehavior<Dockable> implements DockableBehavior {

    /**
     * {@inheritDoc}
     */
    @Override
    public String modelName() {
        return getData().name();
    }

    @Override
    public void initBehavior() {
        // Nothing to init
    }

    @Override
    protected void initInnerComponents() {
    }

}
