package org.jrebirth.af.api.wave;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
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
@Repeatable(OnWaves.class)
@Inherited
@Documented
public @interface OnWave {

    /**
     * Define the Wave Type unique string.
     *
     * There is no default value
     */
    String value() default "";

}
