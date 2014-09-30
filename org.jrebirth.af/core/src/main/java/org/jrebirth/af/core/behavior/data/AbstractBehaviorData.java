package org.jrebirth.af.core.behavior.data;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.core.behavior.Behavior;

public abstract class AbstractBehaviorData implements BehaviorData {

    private List<Class<? extends Behavior<?>>> behaviors;

    public AbstractBehaviorData() {
        super();
        
        // Manage Behavior added by method overriding
        List<? extends Class<? extends Behavior<?>>> list = getCustomBehaviors();
        if(!list.isEmpty()){
            getBehaviors().addAll(list);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class<? extends Behavior<?>>> getBehaviors() {

        if (behaviors == null) {
            behaviors = new ArrayList<Class<? extends Behavior<?>>>();
        }

        for (final BehaviorDataFor annotation : getClass().getAnnotationsByType(BehaviorDataFor.class)) {
            behaviors.add(annotation.value());
        }

        return behaviors;
    }

    
    protected abstract List<? extends Class<? extends Behavior<?>>> getCustomBehaviors();
}
