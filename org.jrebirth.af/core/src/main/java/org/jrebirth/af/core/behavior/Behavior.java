package org.jrebirth.af.core.behavior;

public interface Behavior<BB extends BehaviorBean> {

    default BB getBean() {
        return null;
    }

}
