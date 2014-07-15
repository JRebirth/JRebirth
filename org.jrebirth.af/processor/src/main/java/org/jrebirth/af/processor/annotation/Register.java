package org.jrebirth.af.processor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Register {

    Class<?> value() default Class.class;
    
    RegistrationPriority priority() default RegistrationPriority.None;
    
}
