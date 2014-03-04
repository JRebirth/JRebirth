package org.jrebirth.af.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>BeforeInit</strong>.
 * 
 * @author Sébastien Bordes
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BeforeInit {

    /**
     * Define the execution order of all methods performed before initialization.
     * 
     * There is no default value
     */
    MethodPriority value() default MethodPriority.NORMAL;

}
