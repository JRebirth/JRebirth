package org.jrebirth.af.core.behavior.data;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.behavior.Behavior;
import org.jrebirth.af.api.behavior.annotation.BehaviorDataFor;
import org.jrebirth.af.api.behavior.data.BehaviorData;

public abstract class AbstractBehaviorData implements BehaviorData {

    private List<Class<? extends Behavior<?>>> behaviors;

    public AbstractBehaviorData() {
        super();

        // Manage Behavior added by method overriding
        final List<? extends Class<? extends Behavior<?>>> list = getCustomBehaviors();
        if (!list.isEmpty()) {
            getBehaviors().addAll(list);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class<? extends Behavior<?>>> getBehaviors() {

        if (this.behaviors == null) {
            this.behaviors = new ArrayList<Class<? extends Behavior<?>>>();
        }

        for (final BehaviorDataFor annotation : getClass().getAnnotationsByType(BehaviorDataFor.class)) {
            this.behaviors.add(annotation.value());
        }

        return this.behaviors;
    }

    /**
     *
     * @return
     */
    protected abstract List<? extends Class<? extends Behavior<?>>> getCustomBehaviors();
}
