package org.jrebirth.af.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The class <strong>SkipAnnotation</strong>.
 *
 * @author Sébastien Bordes
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SkipAnnotation {

    /**
     * The value property manage skip annotation feature.
     *
     * When true (or empty) it deactivates annotation processing for the given component.
     *
     * When false, it doesn't skip annotation processing
     */
    boolean value() default true;
}
