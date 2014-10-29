package org.jrebirth.af.core.behavior.data;

import java.util.Collections;
import java.util.List;

import org.jrebirth.af.api.behavior.Behavior;

public class BehaviorDataBase extends AbstractBehaviorData {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Class<? extends Behavior<?>>> getCustomBehaviors() {
        return Collections.emptyList();
    }

}
