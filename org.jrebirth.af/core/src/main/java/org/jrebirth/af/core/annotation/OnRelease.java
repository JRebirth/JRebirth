package org.jrebirth.af.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>OnRelease</strong>.
 * 
 * @author Sébastien Bordes
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OnRelease {

    /**
     * Define the execution order of all methods performed after release.
     * 
     * The default level is MethodPriority.NORMAL
     */
    MethodPriority value() default MethodPriority.NORMAL;

}
