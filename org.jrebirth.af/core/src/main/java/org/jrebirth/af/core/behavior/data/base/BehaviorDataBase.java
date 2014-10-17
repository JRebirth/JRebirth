package org.jrebirth.af.core.behavior.data.base;

import java.util.Collections;
import java.util.List;

import org.jrebirth.af.core.behavior.Behavior;

public class BehaviorDataBase extends AbstractBehaviorData {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Class<? extends Behavior<?>>> getCustomBehaviors() {
        return Collections.emptyList();
    }

}
