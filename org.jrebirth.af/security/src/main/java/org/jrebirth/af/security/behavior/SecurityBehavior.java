package org.jrebirth.af.security.behavior;

import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.security.behavior.data.Securable;

@RegistrationPoint(exclusive = true)
public interface SecurityBehavior extends Behavior<Securable, Model> {

}
