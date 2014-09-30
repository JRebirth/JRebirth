package org.jrebirth.af.core.behavior.data;

import java.util.Collections;
import java.util.List;

import org.jrebirth.af.core.behavior.Behavior;

public class BasicBehaviorData extends AbstractBehaviorData {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Class<? extends Behavior<?>>> getCustomBehaviors() {
        return Collections.emptyList();
    }

}
