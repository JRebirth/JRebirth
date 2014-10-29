package org.jrebirth.af.security.behavior.data;

import java.io.Serializable;

import org.jrebirth.af.api.behavior.annotation.BehaviorDataFor;
import org.jrebirth.af.core.behavior.data.BehaviorDataBase;
import org.jrebirth.af.security.behavior.SecurityBehavior;

@BehaviorDataFor(SecurityBehavior.class)
public class Securable extends BehaviorDataBase implements Serializable {
    
    
    /** The generated serial version uid. */
    private static final long serialVersionUID = -2309831247976039337L;
    
    /** . */
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
