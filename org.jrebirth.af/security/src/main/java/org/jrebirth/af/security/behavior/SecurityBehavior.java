package org.jrebirth.af.security.behavior;

import org.jrebirth.af.api.behavior.Behavior;
import org.jrebirth.af.processor.annotation.RegistrationPoint;
import org.jrebirth.af.security.behavior.data.Securable;

@RegistrationPoint(exclusive = true)
public interface SecurityBehavior extends Behavior<Securable> {

}
