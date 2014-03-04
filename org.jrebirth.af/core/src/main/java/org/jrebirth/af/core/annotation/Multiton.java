package org.jrebirth.af.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>Multiton</strong> is used to auto inject a multiton component.
 * 
 * It currently supports only components that have a string key part.
 * 
 * @author Sébastien Bordes
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Multiton {

    /**
     * Define default string key part.
     * 
     * There is no default value
     */
    String value() default "";

}
