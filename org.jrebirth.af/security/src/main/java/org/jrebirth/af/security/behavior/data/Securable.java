package org.jrebirth.af.security.behavior.data;

import java.io.Serializable;

import org.jrebirth.af.core.behavior.BehaviorData;
import org.jrebirth.af.core.behavior.BehaviorDataFor;
import org.jrebirth.af.security.behavior.SecurityBehavior;

@BehaviorDataFor(SecurityBehavior.class)
public class Securable implements Serializable, BehaviorData {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    private String name;


    public static Securable create() {
        return new Securable();
    }

    public String name() {
        return this.name;
    }

    public Securable name(final String name) {
        this.name = name;
        return this;
    }


}
