package org.jrebirth.af.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>Component</strong> is used to auto inject a JRebirth component.
 *
 * It currently supports only components that have a string key part.
 *
 * @author Sébastien Bordes
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LinkInnerComponent {

    /**
     * Define default string key part.
     *
     * If no value is defined a ClassKey will be used otherwise a MultitonKey will be used with a single key part
     *
     * There is no default value
     */
    String value() default "";

}
