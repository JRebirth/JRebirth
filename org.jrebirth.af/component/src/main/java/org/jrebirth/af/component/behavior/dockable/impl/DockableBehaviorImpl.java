package org.jrebirth.af.component.behavior.dockable.impl;

import org.jrebirth.af.component.behavior.dockable.DockableBehavior;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.core.behavior.base.AbstractModelBehavior;
import org.jrebirth.af.processor.annotation.Register;
import org.jrebirth.af.processor.annotation.RegistrationPriority;

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

    protected void initInnerComponents() {
    }

}
