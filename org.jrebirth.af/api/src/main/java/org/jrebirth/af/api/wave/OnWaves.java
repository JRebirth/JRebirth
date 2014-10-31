package org.jrebirth.af.api.wave;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>OnWaves</strong>.
 *
 * @author Sébastien Bordes
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OnWaves {

    /**
     * The list of {@link OnWave} annotations.
     *
     * There is no default value
     */
    OnWave[] value();
}
