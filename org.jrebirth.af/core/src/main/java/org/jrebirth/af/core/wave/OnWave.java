package org.jrebirth.af.core.wave;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>OnWave</strong>.
 * 
 * @author Sébastien Bordes
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OnWave {

    /**
     * Define the Wave Type unique string.
     * 
     * There is no default value
     */
    String value() default "";

    /**
     * Define the list of Wave Type unique string.
     * 
     * There is no default value
     */
    String[] types() default { };

}
