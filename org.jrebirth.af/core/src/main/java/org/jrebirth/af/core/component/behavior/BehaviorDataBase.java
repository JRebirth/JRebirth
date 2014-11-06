package org.jrebirth.af.core.component.behavior;

import java.util.Collections;
import java.util.List;

import org.jrebirth.af.api.component.behavior.Behavior;

public class BehaviorDataBase extends AbstractBehaviorData {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Class<? extends Behavior<?>>> getCustomBehaviors() {
        return Collections.emptyList();
    }

}
