package org.jrebirth.af.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>AfterInit</strong>.
 *
 * @author Sébastien Bordes
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AfterInit {

    /**
     * Define the execution order of all methods performed after initialization.
     *
     * There is no default value
     */
    MethodPriority value() default MethodPriority.NORMAL;

}
